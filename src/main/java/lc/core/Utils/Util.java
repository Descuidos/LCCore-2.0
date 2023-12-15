package lc.core.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;


import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.FireworkEffect.Type;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@SuppressWarnings({"unused", "RedundantIfStatement", "ResultOfMethodCallIgnored"})
public class Util {
	
	public static String color(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static void message(CommandSender sender, String msg) {
		sender.sendMessage(color(msg));
	}

	public static void sendTitle(Player player, int fadein, int stay, int fadeout, String title, String subtitle) {
		PlayerConnection pConn = ((CraftPlayer) player).getHandle().playerConnection;
		PacketPlayOutTitle pTitleInfo = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadein, stay, fadeout);
		pConn.sendPacket(pTitleInfo);
		if (subtitle != null) {
			IChatBaseComponent iComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + color(subtitle) + "\"}");
			PacketPlayOutTitle pSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, iComp);
			pConn.sendPacket(pSubtitle);
		}
		if (title != null) {
			title = title.replaceAll("%player%", player.getDisplayName());
			IChatBaseComponent iComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + color(title) + "\"}");
			PacketPlayOutTitle pTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, iComp);
			pConn.sendPacket(pTitle);
		}
	}
	
	public static String getCenteredMessage(String message){
		int CENTER_PX = 154;
		int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;
       
        for(char c : message.toCharArray()){
                if(c == '§'){
                        previousCode = true;
                }else if(previousCode){
                        previousCode = false;
                        if(c == 'l' || c == 'L'){
                                isBold = true;
                        }else isBold = false;
                }else{
                        DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                        messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                        messagePxSize++;
                }
        }
       
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
                sb.append(" ");
                compensated += spaceLength;
        }
        return (sb + message);
     }
	
	public static void sendParticles(World world, String type, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float data, int amount) {
		EnumParticle particle = EnumParticle.valueOf(type);
		PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(particle, false, x, y, z, offsetX, offsetY, offsetZ, data, amount, 1);
		for (Player player: world.getPlayers()) {
			CraftPlayer start = (CraftPlayer) player; //Replace player with your player.
			EntityPlayer target = start.getHandle();
			PlayerConnection connect = target.playerConnection;
			connect.sendPacket(particles);
		}
	}
	
	public static FireworkEffect getFireworkEffect(Color one, Color two, Color three, Color four, Color five, Type type) {
		return FireworkEffect.builder().flicker(false).withColor(one, two, three, four).withFade(five).with(type).trail(true).build();
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void copyFolder(File src, File dest) throws IOException {
		deleteDir(dest);
    	if(src.isDirectory()){
    		if(!dest.exists()) {
    		   dest.mkdir();
    		}
    		String[] files = src.list();

            assert files != null;
            for (String file : files) {
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
 
    	} else{
    		InputStream in = Files.newInputStream(src.toPath());
    	        OutputStream out = Files.newOutputStream(dest.toPath());
    	        out.flush();
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	}
    	
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
            assert children != null;
            for (String child : children) {
                deleteDir(new File(dir, child));
            }
		}
		dir.delete();
	}
	
	public static void logToFile(String message, Plugin p, String filename) {
        try
        {
            File dataFolder = p.getDataFolder();
            if(!dataFolder.exists())
            {
                dataFolder.mkdir();
            }
 
            File saveTo = new File(p.getDataFolder(), filename);
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }
 
 
            FileWriter fw = new FileWriter(saveTo, true);
 
            PrintWriter pw = new PrintWriter(fw);
 
            pw.println(message);
 
            pw.flush();
 
            pw.close();
 
        } catch (IOException ex) {
			Bukkit.getLogger().severe("No se ha podido logear el mensaje: "+message+" en el archivo: "+filename);
 
        }
 
    }
	
    public static String TimeToString(long time) {
        long hours = time / 3600L;
        long minutes = (time - hours * 3600L) / 60L;
        long seconds = time - hours * 3600L - minutes * 60L;
        return String.format("%02d" + ":"+ "%02d", minutes, seconds).replace("-", "");
    }
	   
}
