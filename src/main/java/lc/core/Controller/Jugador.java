package lc.core.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

@SuppressWarnings("unused")
public class Jugador {
	private static final HashMap<String, Jugador> Jugadores = new HashMap<>();
	private final String name;
	private Player player;
	private boolean onlineMode = false;
	private boolean onlineModeVerify = false;
	private String skin;
	private boolean saveSQL = false;
	private int lcoins = 0;
	private Ranks rank = Ranks.DEFAULT;
	private long rankduration = 0;
	private int rankpoints = 0;
	private ChatColor NameTagColor = ChatColor.YELLOW;
	private boolean hiderank = false;
	private boolean Opciones_SVS_Enable_Players = false;
	private boolean Opciones_SVS_Enable_Chat = true;
	private boolean Opciones_SVS_Enable_Stacker = true;
	private boolean Opciones_SVS_Enable_MiniLobby = true;
	private String HG_Kit = "default";
	private int HG_Stats_Partidas_ganadas = 0;
	private int HG_Stats_Partidas_jugadas = 0;
	private int HG_Stats_topkillstreak = 0;
	private int HG_Stats_deaths = 0;
	private int HG_Stats_kills = 0;
	private String HG_Trail_Effect = "normal";
	private String HG_Place_Color = "normal";
	//clasic hg
	private String CHG_Kit = "default";
	private boolean CHG_Winner = false;
	private int CHG_Stats_Partidas_ganadas = 0;
	private int CHG_Stats_Partidas_jugadas = 0;
	private int CHG_Stats_topkillstreak = 0;
	private int CHG_Stats_deaths = 0;
	private int CHG_Stats_kills = 0;
	private String CHG_Trail_Effect = "normal";
	private String CHG_Place_Color = "normal";
	private ArrayList<String> HG_OWN_KITS;
	private ArrayList<String> CHG_OWN_KITS;
	private String KitPVP_Kit = "default";
	private int KitPVP_Stats_topkillstreak = 0;
	private int KitPVP_Stats_deaths = 0;
	private int KitPVP_Stats_kills = 0;
	private int POTPVP_Stats_Partidas_ganadas = 0;
	private int POTPVP_Stats_Partidas_jugadas = 0;
	private int POTPVP_Stats_topkillstreak = 0;
	private int POTPVP_Stats_deaths = 0;
	private int POTPVP_Stats_kills = 0;
	private String POTPVP_Kit = "default";
	private String SKYW_Kit = "default";
	private ArrayList<String> SKYW_OWN_KITS;
	private String SKYW_Trail_Effect = "normal";
	private String SKYW_Glass_Color = "normal";
	private int SKYW_Stats_Partidas_ganadas = 0;
	private int SKYW_Stats_Partidas_jugadas = 0;
	private int SKYW_Stats_topkillstreak = 0;
	private int SKYW_Stats_deaths = 0;
	private int SKYW_Stats_kills = 0;
	private String BEDW_Kit = "default";
	private ArrayList<String> BEDW_OWN_KITS;
	private String BEDW_Trail_Effect = "normal";
	private String BEDW_Glass_Color = "normal";
	private int BEDW_Stats_Partidas_ganadas = 0;
	private int BEDW_Stats_Partidas_jugadas = 0;
	private int BEDW_Stats_topkillstreak = 0;
	private int BEDW_Stats_deaths = 0;
	private int BEDW_Stats_kills = 0;
	private String PVPG_Kit = "default";
	private int PVPG_Stats_Partidas_ganadas = 0;
	private int PVPG_Stats_Partidas_jugadas = 0;
	private int PVPG_Stats_topkillstreak = 0;
	private int PVPG_Stats_deaths = 0;
	private int PVPG_Stats_kills = 0;
	private int PVPG_Stats_cores_leakeds = 0;
	private int PVPG_Stats_wools_placed = 0;
	private int PVPG_Stats_monuments_destroyed = 0;

	private boolean PlayerAuth_loaded = false;
	private boolean PlayerAuth_logged = false;
	private boolean PlayerAuth_registred = false;
	private String PlayerAuth_password = "";
	private String PlayerAuth_last_ip = "";
	private String Rank_CHG = "";
	private long PlayerAuth_lastlogin = 0;
	private Integer totalFame = 0;

	public static Jugador getJugador(String pl) {
		if(Jugadores.containsKey(pl.toLowerCase()))
			return Jugadores.get(pl.toLowerCase());
		
		return new Jugador(pl);
	}
	
	public static Jugador getJugador(Player pl) {
		if(Jugadores.containsKey(pl.getName().toLowerCase()))
			return Jugadores.get(pl.getName().toLowerCase());
		
		return new Jugador(pl);
	}
	
	public static void removeJugador(String pl) {
		Jugadores.remove(pl.toLowerCase());
	}
	
	public Jugador(String s) {
		player = null;
		name = s.toLowerCase();
		
		Jugadores.put(s.toLowerCase(), this);
	}
	
	public Jugador(Player p) {
		player = p;
		name = p.getName().toLowerCase();
		Jugadores.put(p.getName().toLowerCase(), this);
	}
	
	public String getPlayerName() {
		return name;
	}
	
	public Player getBukkitPlayer() {
		return player;
	}
	
	public void setBukkitPlayer(Player bplayer) {
		player = bplayer;
	}
	
	public boolean isOnlineMode() {
		//is premium
		return onlineMode;
	}

	public void setOnlineMode(boolean onlineMode) {
		this.onlineMode = onlineMode;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public int getLcoins() {
		return lcoins;
	}

	public void addLcoins(int lcoins) {
		this.lcoins += lcoins;
	}
	
	public void removeLcoins(int lcoins) {
		this.lcoins -= lcoins;
	}
	
	public boolean hascoins(int lcoins) {
		return this.lcoins >= lcoins;
	}
	
	public void setLcoins(int lcoins) {
		this.lcoins = lcoins;
	}

	public Ranks getRank() {
		return rank;
	}
	
	public boolean is_Premium() {
		return (rank == Ranks.PREMIUM);
	}
	
	public boolean is_RUBY()
	{
		return (rank == Ranks.RUBY || rank == Ranks.MOD || rank == Ranks.AYUDANTE || rank == Ranks.ADMIN || rank == Ranks.OWNER);
		
	}
	public boolean is_VIP() {
		return (rank == Ranks.VIP || rank == Ranks.SVIP || rank == Ranks.RUBY||rank == Ranks.ELITE || rank == Ranks.YOUTUBER || rank == Ranks.MOD || rank == Ranks.AYUDANTE || rank == Ranks.BUILDER || rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_SVIP() {
		return (rank == Ranks.SVIP || rank == Ranks.ELITE ||rank==Ranks.RUBY ||rank == Ranks.YOUTUBER || rank == Ranks.MOD || rank == Ranks.AYUDANTE || rank == Ranks.BUILDER || rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_ELITE() {
		return (rank == Ranks.ELITE || rank == Ranks.RUBY||rank == Ranks.YOUTUBER || rank == Ranks.MOD || rank == Ranks.AYUDANTE || rank == Ranks.BUILDER || rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_YOUTUBER() {
		return (rank == Ranks.YOUTUBER || rank == Ranks.MOD || rank == Ranks.AYUDANTE || rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_AYUDANTE() {
		return (rank == Ranks.AYUDANTE || rank == Ranks.MOD || rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_DEFAULT() {
		return (rank == Ranks.DEFAULT);
	}
	
	public boolean is_MODERADOR() {
		return (rank == Ranks.MOD || rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_Admin() {
		return (rank == Ranks.ADMIN || rank == Ranks.OWNER);
	}
	
	public boolean is_Owner() {
		return (rank == Ranks.OWNER);
	}
	
	public boolean is_BUILDER() {
		return (rank == Ranks.BUILDER);
	}
	
	public boolean is_VETERANO() {
		return (rank == Ranks.VETERANO);
	}

	public void setRank(Ranks rango) {
		rank = rango;
	}

	public long getRankduration() {
		return rankduration;
	}
	
	public boolean isHideRank() {
		return hiderank;
	}
	
	public void setHideRank(boolean bol) {
		hiderank = bol;
	}
	
	public void checkRankduration() {
		if(is_DEFAULT() || is_Admin() || is_Owner() || is_AYUDANTE() || is_YOUTUBER() || is_MODERADOR())
			return;
		
		if(System.currentTimeMillis() >= rankduration) {
			rankduration = 0;
			if(isOnlineMode()) {
				setRank(Ranks.PREMIUM);
				Database.savePlayerRank(this);
			} else if(is_Premium() && !isOnlineMode()) {
				setRank(Ranks.PREMIUM);
				Database.savePlayerRank(this);
			} else {
				setRank(Ranks.DEFAULT);
				if(rankpoints > 0) {
					Database.savePlayerRank(this);
				} else {
					Database.deletePlayerRango(this);
				}
			}
		}
	}
	
	public void addRankduration(long rankduration) {
		this.rankduration += rankduration;
	}
	
	public void removeRankduration(long rankduration) {
		this.rankduration -= rankduration;
	}

	public void setRankduration(long rankduration) {
		if(rankduration <= 0)
			this.rankduration = 0;
		else
			this.rankduration = (System.currentTimeMillis() + rankduration);
	}

	public void setRankdurationSQL(long rankduration) {
		this.rankduration = rankduration;
	}
	
	public int getRankpoints() {
		return rankpoints;
	}

	public void addRankpoints(int rankpoints) {
		this.rankpoints += rankpoints;
	}
	
	public void removeRankpoints(int rankpoints) {
		this.rankpoints -= rankpoints;
	}
	
	public void setRankpoints(int rankpoints) {
		this.rankpoints = rankpoints;
	}

	public String getHG_Kit() {
		return HG_Kit;
	}

	public void setHG_Kit(String hG_Kit) {
		this.HG_Kit = hG_Kit;
	}

	public int getHG_Stats_Partidas_ganadas() {
		return HG_Stats_Partidas_ganadas;
	}

	public void addHG_Stats_Partidas_ganadas(int hG_Stats_Partidas_ganadas) {
		this.HG_Stats_Partidas_ganadas += hG_Stats_Partidas_ganadas;
	}
	
	public void setHG_Stats_Partidas_ganadas(int hG_Stats_Partidas_ganadas) {
		this.HG_Stats_Partidas_ganadas = hG_Stats_Partidas_ganadas;
	}

	public int getHG_Stats_Partidas_jugadas() {
		return HG_Stats_Partidas_jugadas;
	}
	
	public void addHG_Stats_Partidas_jugadas(int hG_Stats_Partidas_jugadas) {
		this.HG_Stats_Partidas_jugadas += hG_Stats_Partidas_jugadas;
	}

	public void setHG_Stats_Partidas_jugadas(int hG_Stats_Partidas_jugadas) {
		this.HG_Stats_Partidas_jugadas = hG_Stats_Partidas_jugadas;
	}
	public void setHG_Rank(String HG_rank) {
		this.Rank_CHG = HG_rank;
	}
	public int getHG_Stats_Level() {
		int lvl = HG_Stats_kills / 100;
		lvl = lvl + (HG_Stats_Partidas_ganadas / 2);
		
		return lvl;
	}

	public int getHG_Stats_topkillstreak() {
		return HG_Stats_topkillstreak;
	}
	
	public void addHG_Stats_topkillstreak(int hG_Stats_topkillstreak) {
		this.HG_Stats_topkillstreak += hG_Stats_topkillstreak;
	}
	
	public void setHG_Stats_topkillstreak(int hG_Stats_topkillstreak) {
		this.HG_Stats_topkillstreak = hG_Stats_topkillstreak;
	}

	public int getHG_Stats_deaths() {
		return HG_Stats_deaths;
	}

	public void addHG_Stats_deaths(int hG_Stats_deaths) {
		this.HG_Stats_deaths += hG_Stats_deaths;
	}
	
	public void setHG_Stats_deaths(int hG_Stats_deaths) {
		this.HG_Stats_deaths = hG_Stats_deaths;
	}

	public int getHG_Stats_kills() {
		return HG_Stats_kills;
	}
	
	public void addHG_Stats_kills(int hG_Stats_kills) {
		this.HG_Stats_kills += hG_Stats_kills;
	}
	
	public void setHG_Stats_kills(int hG_Stats_kills) {
		this.HG_Stats_kills = hG_Stats_kills;
	}

	public int getHG_Stats_kdr() {
		int kdr;
		
		if(getHG_Stats_deaths() <= 0)
			kdr = getHG_Stats_kills();
		else
			kdr = getHG_Stats_kills() / getHG_Stats_deaths();
		
		return kdr;
	}
	
	public String getCHG_Kit() {
		return CHG_Kit;
	}

	public void setCHG_Kit(String CHG_Kit) {
		this.CHG_Kit = CHG_Kit;
	}
	
	public boolean isCHG_Winner() {
		return CHG_Winner;
	}

	public void setCHG_Winner(boolean cHG_Winner) {
		CHG_Winner = cHG_Winner;
	}
	

	public int getCHG_Stats_Partidas_ganadas() {
		return CHG_Stats_Partidas_ganadas;
	}

	public void addCHG_Stats_Partidas_ganadas(int CHG_Stats_Partidas_ganadas) {
		this.CHG_Stats_Partidas_ganadas += CHG_Stats_Partidas_ganadas;
	}
	
	public void setCHG_Stats_Partidas_ganadas(int CHG_Stats_Partidas_ganadas) {
		this.CHG_Stats_Partidas_ganadas = CHG_Stats_Partidas_ganadas;
	}

	public int getCHG_Stats_Partidas_jugadas() {
		return CHG_Stats_Partidas_jugadas;
	}
	
	public void addCHG_Stats_Partidas_jugadas(int CHG_Stats_Partidas_jugadas) {
		this.CHG_Stats_Partidas_jugadas += CHG_Stats_Partidas_jugadas;
	}

	public void setCHG_Stats_Partidas_jugadas(int CHG_Stats_Partidas_jugadas) {
		this.CHG_Stats_Partidas_jugadas = CHG_Stats_Partidas_jugadas;
	}

	public int getCHG_Stats_Level() {
		int lvl = CHG_Stats_kills / 100;
		lvl = lvl + (CHG_Stats_Partidas_ganadas / 2);
		
		return lvl;
	}

	public int getCHG_Stats_topkillstreak() {
		return CHG_Stats_topkillstreak;
	}
	
	public void addCHG_Stats_topkillstreak(int CHG_Stats_topkillstreak) {
		this.CHG_Stats_topkillstreak += CHG_Stats_topkillstreak;
	}
	
	public void setCHG_Stats_topkillstreak(int CHG_Stats_topkillstreak) {
		this.CHG_Stats_topkillstreak = CHG_Stats_topkillstreak;
	}

	public int getCHG_Stats_deaths() {
		return CHG_Stats_deaths;
	}

	public void addCHG_Stats_deaths(int CHG_Stats_deaths) {
		this.CHG_Stats_deaths += CHG_Stats_deaths;
	}
	
	public void setCHG_Stats_deaths(int CHG_Stats_deaths) {
		this.CHG_Stats_deaths = CHG_Stats_deaths;
	}

	public int getCHG_Stats_kills() {
		return CHG_Stats_kills;
	}
	
	public void addCHG_Stats_kills(int CHG_Stats_kills) {
		this.CHG_Stats_kills += CHG_Stats_kills;
	}
	
	public void setCHG_Stats_kills(int CHG_Stats_kills) {
		this.CHG_Stats_kills = CHG_Stats_kills;
	}

	public int getCHG_Stats_kdr() {
		int kdr;
		
		if(getCHG_Stats_deaths() <= 0)
			kdr = getCHG_Stats_kills();
		else
			kdr = getCHG_Stats_kills() / getCHG_Stats_deaths();
		
		return kdr;
	}
	
	public String getKitPVP_Kit() {
		return KitPVP_Kit;
	}

	public void setKitPVP_Kit(String KitPVP_Kit) {
		this.KitPVP_Kit = KitPVP_Kit;
	}
	
	public int getKitPVP_Stats_Level() {
        return KitPVP_Stats_kills / 150;
	}
	
	public int getKitPVP_Stats_topkillstreak() {
		return KitPVP_Stats_topkillstreak;
	}
	
	public void addKitPVP_Stats_topkillstreak(int KitPVP_Stats_topkillstreak) {
		this.KitPVP_Stats_topkillstreak += KitPVP_Stats_topkillstreak;
	}
	
	public void setKitPVP_Stats_topkillstreak(int KitPVP_Stats_topkillstreak) {
		this.KitPVP_Stats_topkillstreak = KitPVP_Stats_topkillstreak;
	}

	public int getKitPVP_Stats_deaths() {
		return KitPVP_Stats_deaths;
	}

	public void addKitPVP_Stats_deaths(int KitPVP_Stats_deaths) {
		this.KitPVP_Stats_deaths += KitPVP_Stats_deaths;
	}
	
	public void setKitPVP_Stats_deaths(int KitPVP_Stats_deaths) {
		this.KitPVP_Stats_deaths = KitPVP_Stats_deaths;
	}

	public int getKitPVP_Stats_kills() {
		return KitPVP_Stats_kills;
	}
	
	public void addKitPVP_Stats_kills(int KitPVP_Stats_kills) {
		this.KitPVP_Stats_kills += KitPVP_Stats_kills;
	}
	
	public void setKitPVP_Stats_kills(int KitPVP_Stats_kills) {
		this.KitPVP_Stats_kills = KitPVP_Stats_kills;
	}

	public int getKitPVP_Stats_kdr() {
		int kdr;
		
		if(getKitPVP_Stats_deaths() <= 0)
			kdr = getKitPVP_Stats_kills();
		else
			kdr = getKitPVP_Stats_kills() / getKitPVP_Stats_deaths();
		
		return kdr;
	}
	
	public String getPOTPVP_Kit() {
		return POTPVP_Kit;
	}

	public void setPOTPVP_Kit(String POTPVP_Kit) {
		this.POTPVP_Kit = POTPVP_Kit;
	}

	public int getPOTPVP_Stats_Partidas_ganadas() {
		return POTPVP_Stats_Partidas_ganadas;
	}

	public void addPOTPVP_Stats_Partidas_ganadas(int POTPVP_Stats_Partidas_ganadas) {
		this.POTPVP_Stats_Partidas_ganadas += POTPVP_Stats_Partidas_ganadas;
	}
	
	public void setPOTPVP_Stats_Partidas_ganadas(int POTPVP_Stats_Partidas_ganadas) {
		this.POTPVP_Stats_Partidas_ganadas = POTPVP_Stats_Partidas_ganadas;
	}

	public int getPOTPVP_Stats_Partidas_jugadas() {
		return POTPVP_Stats_Partidas_jugadas;
	}
	
	public void addPOTPVP_Stats_Partidas_jugadas(int POTPVP_Stats_Partidas_jugadas) {
		this.POTPVP_Stats_Partidas_jugadas += POTPVP_Stats_Partidas_jugadas;
	}

	public void setPOTPVP_Stats_Partidas_jugadas(int POTPVP_Stats_Partidas_jugadas) {
		this.POTPVP_Stats_Partidas_jugadas = POTPVP_Stats_Partidas_jugadas;
	}

	public int getPOTPVP_Stats_Level() {

        return POTPVP_Stats_kills / 100;
	}
	
	public int getPOTPVP_Stats_topkillstreak() {
		return POTPVP_Stats_topkillstreak;
	}
	
	public void addPOTPVP_Stats_topkillstreak(int POTPVP_Stats_topkillstreak) {
		this.POTPVP_Stats_topkillstreak += POTPVP_Stats_topkillstreak;
	}
	
	public void setPOTPVP_Stats_topkillstreak(int POTPVP_Stats_topkillstreak) {
		this.POTPVP_Stats_topkillstreak = POTPVP_Stats_topkillstreak;
	}

	public int getPOTPVP_Stats_deaths() {
		return POTPVP_Stats_deaths;
	}

	public void addPOTPVP_Stats_deaths(int POTPVP_Stats_deaths) {
		this.POTPVP_Stats_deaths += POTPVP_Stats_deaths;
	}
	
	public void setPOTPVP_Stats_deaths(int POTPVP_Stats_deaths) {
		this.POTPVP_Stats_deaths = POTPVP_Stats_deaths;
	}

	public int getPOTPVP_Stats_kills() {
		return POTPVP_Stats_kills;
	}
	
	public void addPOTPVP_Stats_kills(int POTPVP_Stats_kills) {
		this.POTPVP_Stats_kills += POTPVP_Stats_kills;
	}
	
	public void setPOTPVP_Stats_kills(int POTPVP_Stats_kills) {
		this.POTPVP_Stats_kills = POTPVP_Stats_kills;
	}

	public int getPOTPVP_Stats_kdr() {
		int kdr;
		
		if(getPOTPVP_Stats_deaths() <= 0)
			kdr = getPOTPVP_Stats_kills();
		else
			kdr = getPOTPVP_Stats_kills() / getPOTPVP_Stats_deaths();
		
		return kdr;
	}
	
	public String getSKYW_Kit() {
		return SKYW_Kit;
	}

	public void setSKYW_Kit(String SKYW_Kit) {
		this.SKYW_Kit = SKYW_Kit;
	}

	public int getSKYW_Stats_Partidas_ganadas() {
		return SKYW_Stats_Partidas_ganadas;
	}

	public void addSKYW_Stats_Partidas_ganadas(int SKYW_Stats_Partidas_ganadas) {
		this.SKYW_Stats_Partidas_ganadas += SKYW_Stats_Partidas_ganadas;
	}
	
	public void setSKYW_Stats_Partidas_ganadas(int SKYW_Stats_Partidas_ganadas) {
		this.SKYW_Stats_Partidas_ganadas = SKYW_Stats_Partidas_ganadas;
	}

	public int getSKYW_Stats_Partidas_jugadas() {
		return SKYW_Stats_Partidas_jugadas;
	}
	
	public void addSKYW_Stats_Partidas_jugadas(int SKYW_Stats_Partidas_jugadas) {
		this.SKYW_Stats_Partidas_jugadas += SKYW_Stats_Partidas_jugadas;
	}

	public void setSKYW_Stats_Partidas_jugadas(int SKYW_Stats_Partidas_jugadas) {
		this.SKYW_Stats_Partidas_jugadas = SKYW_Stats_Partidas_jugadas;
	}

	public int getSKYW_Stats_Level() {
		int lvl = SKYW_Stats_kills / 100;
		lvl = lvl + (CHG_Stats_Partidas_ganadas / 2);
		
		return lvl;
	}
	
	public int getSKYW_Stats_topkillstreak() {
		return SKYW_Stats_topkillstreak;
	}
	
	public void addSKYW_Stats_topkillstreak(int SKYW_Stats_topkillstreak) {
		this.SKYW_Stats_topkillstreak += SKYW_Stats_topkillstreak;
	}
	
	public void setSKYW_Stats_topkillstreak(int SKYW_Stats_topkillstreak) {
		this.SKYW_Stats_topkillstreak = SKYW_Stats_topkillstreak;
	}

	public int getSKYW_Stats_deaths() {
		return SKYW_Stats_deaths;
	}

	public void addSKYW_Stats_deaths(int SKYW_Stats_deaths) {
		this.SKYW_Stats_deaths += SKYW_Stats_deaths;
	}
	
	public void setSKYW_Stats_deaths(int SKYW_Stats_deaths) {
		this.SKYW_Stats_deaths = SKYW_Stats_deaths;
	}

	public int getSKYW_Stats_kills() {
		return SKYW_Stats_kills;
	}
	
	public void addSKYW_Stats_kills(int SKYW_Stats_kills) {
		this.SKYW_Stats_kills += SKYW_Stats_kills;
	}
	
	public void setSKYW_Stats_kills(int SKYW_Stats_kills) {
		this.SKYW_Stats_kills = SKYW_Stats_kills;
	}

	public int getSKYW_Stats_kdr() {
		int kdr;
		
		if(getSKYW_Stats_deaths() <= 0)
			kdr = getSKYW_Stats_kills();
		else
			kdr = getSKYW_Stats_kills() / getSKYW_Stats_deaths();
		
		return kdr;
	}
	
	public String getBEDW_Kit() {
		return BEDW_Kit;
	}

	public void setBEDW_Kit(String BEDW_Kit) {
		this.BEDW_Kit = BEDW_Kit;
	}

	public int getBEDW_Stats_Partidas_ganadas() {
		return BEDW_Stats_Partidas_ganadas;
	}

	public void addBEDW_Stats_Partidas_ganadas(int BEDW_Stats_Partidas_ganadas) {
		this.BEDW_Stats_Partidas_ganadas += BEDW_Stats_Partidas_ganadas;
	}
	
	public void setBEDW_Stats_Partidas_ganadas(int BEDW_Stats_Partidas_ganadas) {
		this.BEDW_Stats_Partidas_ganadas = BEDW_Stats_Partidas_ganadas;
	}

	public int getBEDW_Stats_Partidas_jugadas() {
		return BEDW_Stats_Partidas_jugadas;
	}
	
	public void addBEDW_Stats_Partidas_jugadas(int BEDW_Stats_Partidas_jugadas) {
		this.BEDW_Stats_Partidas_jugadas += BEDW_Stats_Partidas_jugadas;
	}

	public void setBEDW_Stats_Partidas_jugadas(int BEDW_Stats_Partidas_jugadas) {
		this.BEDW_Stats_Partidas_jugadas = BEDW_Stats_Partidas_jugadas;
	}

	public int getBEDW_Stats_Level() {
		int lvl = BEDW_Stats_kills / 100;
		lvl = lvl + (BEDW_Stats_Partidas_ganadas / 2);
		
		return lvl;
	}

	public int getBEDW_Stats_topkillstreak() {
		return BEDW_Stats_topkillstreak;
	}
	
	public void addBEDW_Stats_topkillstreak(int BEDW_Stats_topkillstreak) {
		this.BEDW_Stats_topkillstreak += BEDW_Stats_topkillstreak;
	}
	
	public void setBEDW_Stats_topkillstreak(int BEDW_Stats_topkillstreak) {
		this.BEDW_Stats_topkillstreak = BEDW_Stats_topkillstreak;
	}

	public int getBEDW_Stats_deaths() {
		return BEDW_Stats_deaths;
	}

	public void addBEDW_Stats_deaths(int BEDW_Stats_deaths) {
		this.BEDW_Stats_deaths += BEDW_Stats_deaths;
	}
	
	public void setBEDW_Stats_deaths(int BEDW_Stats_deaths) {
		this.BEDW_Stats_deaths = BEDW_Stats_deaths;
	}

	public int getBEDW_Stats_kills() {
		return BEDW_Stats_kills;
	}
	
	public void addBEDW_Stats_kills(int BEDW_Stats_kills) {
		this.BEDW_Stats_kills += BEDW_Stats_kills;
	}
	
	public void setBEDW_Stats_kills(int BEDW_Stats_kills) {
		this.BEDW_Stats_kills = BEDW_Stats_kills;
	}

	public int getBEDW_Stats_kdr() {
		int kdr;
		
		if(getBEDW_Stats_deaths() <= 0)
			kdr = getBEDW_Stats_kills();
		else
			kdr = getBEDW_Stats_kills() / getBEDW_Stats_deaths();
		
		return kdr;
	}
	
	public String getBEDW_Trail_Effect() {
		return BEDW_Trail_Effect;
	}

	public void setBEDW_Trail_Effect(String BEDW_Trail_Effect) {
		this.BEDW_Trail_Effect = BEDW_Trail_Effect;
	}

	public String getBEDW_Glass_Color() {
		return BEDW_Glass_Color;
	}

	public void setBEDW_Glass_Color(String BEDW_Glass_Color) {
		this.BEDW_Glass_Color = BEDW_Glass_Color;
	}

	public ArrayList<String> getBEDW_OWN_KITS() {
		if(BEDW_OWN_KITS == null) {
			BEDW_OWN_KITS = Lists.newArrayList();
		}
		return BEDW_OWN_KITS;
	}
	
	public void addBEDW_OWN_KITS(String BEDW_OWN_KITS) {
		this.getBEDW_OWN_KITS().add(BEDW_OWN_KITS);
	}
	
	public void BuyKit_SV_BEDW(String kitname) {
		Database.BuyKit_SV_BEDWARS(this, kitname);
		getBEDW_OWN_KITS().add(kitname);
	}
	
	public String getPVPG_Kit() {
		return PVPG_Kit;
	}

	public void setPVPG_Kit(String PVPG_Kit) {
		this.PVPG_Kit = PVPG_Kit;
	}

	public int getPVPG_Stats_Partidas_ganadas() {
		return PVPG_Stats_Partidas_ganadas;
	}

	public void addPVPG_Stats_Partidas_ganadas(int PVPG_Stats_Partidas_ganadas) {
		this.PVPG_Stats_Partidas_ganadas += PVPG_Stats_Partidas_ganadas;
	}
	
	public void setPVPG_Stats_Partidas_ganadas(int PVPG_Stats_Partidas_ganadas) {
		this.PVPG_Stats_Partidas_ganadas = PVPG_Stats_Partidas_ganadas;
	}

	public int getPVPG_Stats_Partidas_jugadas() {
		return PVPG_Stats_Partidas_jugadas;
	}
	
	public void addPVPG_Stats_Partidas_jugadas(int PVPG_Stats_Partidas_jugadas) {
		this.PVPG_Stats_Partidas_jugadas += PVPG_Stats_Partidas_jugadas;
	}

	public void setPVPG_Stats_Partidas_jugadas(int PVPG_Stats_Partidas_jugadas) {
		this.PVPG_Stats_Partidas_jugadas = PVPG_Stats_Partidas_jugadas;
	}

	public int getPVPG_Stats_Level() {
		int lvl = PVPG_Stats_kills / 100;
		lvl = lvl + (PVPG_Stats_Partidas_ganadas / 2);
		
		return lvl;
	}
	
	public int getPVPG_Stats_topkillstreak() {
		return PVPG_Stats_topkillstreak;
	}
	
	public void addPVPG_Stats_topkillstreak(int PVPG_Stats_topkillstreak) {
		this.PVPG_Stats_topkillstreak += PVPG_Stats_topkillstreak;
	}
	
	public void setPVPG_Stats_topkillstreak(int PVPG_Stats_topkillstreak) {
		this.PVPG_Stats_topkillstreak = PVPG_Stats_topkillstreak;
	}

	public int getPVPG_Stats_deaths() {
		return PVPG_Stats_deaths;
	}

	public void addPVPG_Stats_deaths(int PVPG_Stats_deaths) {
		this.PVPG_Stats_deaths += PVPG_Stats_deaths;
	}
	
	public void setPVPG_Stats_deaths(int PVPG_Stats_deaths) {
		this.PVPG_Stats_deaths = PVPG_Stats_deaths;
	}

	public int getPVPG_Stats_kills() {
		return PVPG_Stats_kills;
	}
	
	public void addPVPG_Stats_kills(int PVPG_Stats_kills) {
		this.PVPG_Stats_kills += PVPG_Stats_kills;
	}
	
	public void setPVPG_Stats_kills(int PVPG_Stats_kills) {
		this.PVPG_Stats_kills = PVPG_Stats_kills;
	}

	public int getPVPG_Stats_kdr() {
		int kdr;
		
		if(getPVPG_Stats_deaths() <= 0)
			kdr = getPVPG_Stats_kills();
		else
			kdr = getPVPG_Stats_kills() / getPVPG_Stats_deaths();
		
		return kdr;
	}
	
	public int getPVPG_Stats_cores_leakeds() {
		return PVPG_Stats_cores_leakeds;
	}
	
	public void addPVPG_Stats_cores_leakeds(int PVPG_Stats_cores_leakeds) {
		this.PVPG_Stats_cores_leakeds += PVPG_Stats_cores_leakeds;
	}
	
	public void setPVPG_Stats_cores_leakeds(int PVPG_Stats_cores_leakeds) {
		this.PVPG_Stats_cores_leakeds = PVPG_Stats_cores_leakeds;
	}
	
		public int getPVPG_Stats_wools_placed() {
		return PVPG_Stats_wools_placed;
	}
	
	public void addPVPG_Stats_wools_placed(int PVPG_Stats_wools_placed) {
		this.PVPG_Stats_wools_placed += PVPG_Stats_wools_placed;
	}
	
	public void setPVPG_Stats_wools_placed(int PVPG_Stats_wools_placed) {
		this.PVPG_Stats_wools_placed = PVPG_Stats_wools_placed;
	}
	
	public int getPVPG_Stats_monuments_destroyed() {
		return PVPG_Stats_monuments_destroyed;
	}
	
	public void addPVPG_Stats_monuments_destroyed(int PVPG_Stats_monuments_destroyed) {
		this.PVPG_Stats_monuments_destroyed += PVPG_Stats_monuments_destroyed;
	}
	
	public void setPVPG_Stats_monuments_destroyed(int PVPG_Stats_monuments_destroyed) {
		this.PVPG_Stats_monuments_destroyed = PVPG_Stats_monuments_destroyed;
	}

	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	public boolean SaveSQL() {
		return saveSQL;
	}

	public void setSaveSQL(boolean saveSQL) {
		this.saveSQL = saveSQL;
	}

	public boolean isOpciones_SVS_Enable_Players() {
		return Opciones_SVS_Enable_Players;
	}

	public void setOpciones_SVS_Enable_Players(boolean opciones_SVS_Enable_Players) {
		Opciones_SVS_Enable_Players = opciones_SVS_Enable_Players;
	}

	public boolean isOpciones_SVS_Enable_Chat() {
		return Opciones_SVS_Enable_Chat;
	}

	public void setOpciones_SVS_Enable_Chat(boolean opciones_SVS_Enable_Chat) {
		Opciones_SVS_Enable_Chat = opciones_SVS_Enable_Chat;
	}

	public boolean isOpciones_SVS_Enable_Stacker() {
		return Opciones_SVS_Enable_Stacker;
	}

	public void setOpciones_SVS_Enable_Stacker(boolean opciones_SVS_Enable_Stacker) {
		Opciones_SVS_Enable_Stacker = opciones_SVS_Enable_Stacker;
	}
	
	public boolean isOpciones_SVS_Enabled_Minilobby() {
		return Opciones_SVS_Enable_MiniLobby;
	}

	public void setOpciones_SVS_Enabled_Minilobby(boolean opciones_SVS_minilobby) {
		Opciones_SVS_Enable_MiniLobby = opciones_SVS_minilobby;
	}

	public String getSKYW_Trail_Effect() {
		return SKYW_Trail_Effect;
	}

	public void setSKYW_Trail_Effect(String sKYW_Trail_Effect) {
		SKYW_Trail_Effect = sKYW_Trail_Effect;
	}

	public String getSKYW_Glass_Color() {
		return SKYW_Glass_Color;
	}

	public void setSKYW_Glass_Color(String sKYW_Glass_Color) {
		SKYW_Glass_Color = sKYW_Glass_Color;
	}

	public ArrayList<String> getSKYW_OWN_KITS() {
		if(SKYW_OWN_KITS == null) {
			SKYW_OWN_KITS = Lists.newArrayList();
		}
		return SKYW_OWN_KITS;
	}
	
	public void addSKYW_OWN_KITS(String sKYW_OWN_KITS) {
		getSKYW_OWN_KITS().add(sKYW_OWN_KITS);
	}
	
	public void BuyKit_SV_SKYW(String kitname) {
		Database.BuyKit_SV_SKYW(this, kitname);
		getSKYW_OWN_KITS().add(kitname);
	}
	
	public ArrayList<String> getHG_OWN_KITS() {
		if(HG_OWN_KITS == null) {
			HG_OWN_KITS = Lists.newArrayList();
		}
		return HG_OWN_KITS;
	}
	
	public void addHG_OWN_KITS(String sKYW_OWN_KITS) {
		getHG_OWN_KITS().add(sKYW_OWN_KITS);
	}
	
	public void BuyKit_SV_HG(String kitname) {
		Database.BuyKit_SV_HG(this, kitname);
		getHG_OWN_KITS().add(kitname);
	}

	public String getHG_Trail_Effect() {
		return HG_Trail_Effect;
	}

	public void setHG_Trail_Effect(String hG_Trail_Effect) {
		HG_Trail_Effect = hG_Trail_Effect;
	}

	public String getHG_Place_Color() {
		return HG_Place_Color;
	}

	public void setHG_Place_Color(String hG_Place_Color) {
		HG_Place_Color = hG_Place_Color;
	}

	
	public ArrayList<String> getCHG_OWN_KITS() {
		if(CHG_OWN_KITS == null) {
			CHG_OWN_KITS = Lists.newArrayList();
		}
		return CHG_OWN_KITS;
	}
	
	public void addCHG_OWN_KITS(String sKYW_OWN_KITS) {
		getCHG_OWN_KITS().add(sKYW_OWN_KITS);
	}
	
	public void BuyKit_SV_CHG(String kitname) {
		Database.BuyKit_SV_CHG(this, kitname);
		getCHG_OWN_KITS().add(kitname);
	}

	public String getCHG_Trail_Effect() {
		return CHG_Trail_Effect;
	}

	public void setCHG_Trail_Effect(String CHG_Trail_Effect) {
		this.CHG_Trail_Effect = CHG_Trail_Effect;
	}

	public String getCHG_Place_Color() {
		return CHG_Place_Color;
	}

	public void setCHG_Place_Color(String CHG_Place_Color) {
		this.CHG_Place_Color = CHG_Place_Color;
	}
	
	public boolean isOnlineModeVerify() {
		return onlineModeVerify;
	}

	public void setOnlineModeVerify(boolean onlineModeVerify) {
		this.onlineModeVerify = onlineModeVerify;
	}
	
	public boolean isPlayerAuth_logged() {
		return PlayerAuth_logged;
	}

	public void setPlayerAuth_logged(boolean playerAuth_logged) {
		PlayerAuth_logged = playerAuth_logged;
	}

	public String getPlayerAuth_password() {
		return PlayerAuth_password;
	}

	public void setPlayerAuth_password(String playerAuth_password) {
		PlayerAuth_password = playerAuth_password;
	}

	public long getPlayerAuth_lastlogin() {
		return PlayerAuth_lastlogin;
	}

	public void setPlayerAuth_lastlogin(long playerAuth_lastlogin) {
		PlayerAuth_lastlogin = playerAuth_lastlogin;
	}

	public boolean isPlayerAuth_registred() {
		return PlayerAuth_registred;
	}

	public void setPlayerAuth_registred(boolean playerAuth_registred) {
		PlayerAuth_registred = playerAuth_registred;
	}

	public boolean isPlayerAuth_loaded() {
		return PlayerAuth_loaded;
	}

	public void setPlayerAuth_loaded(boolean playerAuth_loaded) {
		PlayerAuth_loaded = playerAuth_loaded;
	}

	public String getPlayerAuth_last_ip() {
		return PlayerAuth_last_ip;
	}

	public void setPlayerAuth_last_ip(String playerAuth_last_ip) {
		PlayerAuth_last_ip = playerAuth_last_ip;
	}
	
	public ChatColor getNameTagColor() {
		return NameTagColor;
	}

	public void setNameTagColor(ChatColor nameTagColor) {
		NameTagColor = nameTagColor;
	}
	public Integer getTotalFame(){
		return totalFame;
	}
	public void setTotalFame(Integer a){
		this.totalFame = a;
	}
	public String getHG_Rank() {
		// TODO Auto-generated method stub
		return Rank_CHG;
	}

}