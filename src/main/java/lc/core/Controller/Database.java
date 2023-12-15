package lc.core.Controller;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.LinkedHashMap;

import lc.core.LcCore;
import lc.core.json.JSONException;
import lc.core.json.JSONObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


@SuppressWarnings({"CallToPrintStackTrace", "unused", "CatchMayIgnoreException", "StringBufferReplaceableByString", "StringConcatenationInsideStringBufferAppend", "ExtractMethodRecommender", "Convert2Lambda"})
public class Database {
	public static String host; //MYSQL.minelc.com
	public static int port;
	public static String dbname;
	public static String user;
	public static String pass;
	public static Connection connection = null;
	public static ConfigManager config;

	public static void setupDatabaseData(){
		config = new ConfigManager();
		host = config.getConfig().getString("MySQL.host");
		port = Integer.parseInt(config.getConfig().getString("MySQL.port"));
		dbname = config.getConfig().getString("MySQL.db");
		user = config.getConfig().getString("MySQL.user");
		pass = config.getConfig().getString("MySQL.pass");

	}

	public static void openConnection() throws Exception {
		try {
			InetAddress address = InetAddress.getByName(host);
			host = address.getHostAddress();
			System.out.print("Usando host: "+host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname + "?autoReconnect=true", user, pass);
		
		String update_string1 = "CREATE TABLE IF NOT EXISTS PlayerInfo (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `FirstSeen` DATETIME, `LastSeen` DATETIME, `Premium` BOOLEAN, `PremiumVerify` BOOLEAN, `LastIP` VARCHAR(16), `Skin` VARCHAR(24), `TimePlayed` INTEGER, PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string2 = "CREATE TABLE IF NOT EXISTS LCoins (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `LCoins` INTEGER, PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string3 = "CREATE TABLE IF NOT EXISTS Rango (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `Rank` VARCHAR(24), `Puntos` INTEGER, `Duration` BIGINT, `TabColor` VARCHAR(16), `NameTagColor` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string4 = "CREATE TABLE IF NOT EXISTS Opciones_SVS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `opc_enablePlayers` BOOLEAN, `opc_enableChat` BOOLEAN, `opc_enableStacker` BOOLEAN, `opc_glassColor` INTEGER, `opc_Effect` VARCHAR(16), `opc_arrowEffect` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string5 = "CREATE TABLE IF NOT EXISTS SV_HG (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `stats_partidas_jugadas` INTEGER, `stats_partidas_ganadas` INTEGER, `Kit` VARCHAR(16), `placecolor` VARCHAR(16), `traileffect` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string6 = "CREATE TABLE IF NOT EXISTS SV_KITPVP (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `Kit` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string7 = "CREATE TABLE IF NOT EXISTS SV_POTPVP (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `stats_partidas_jugadas` INTEGER, `stats_partidas_ganadas` INTEGER, `Kit` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string8 = "CREATE TABLE IF NOT EXISTS SV_SKYWARS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `stats_partidas_jugadas` INTEGER, `stats_partidas_ganadas` INTEGER, `Kit` VARCHAR(16), `glasscolor` VARCHAR(16), `traileffect` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string9 = "CREATE TABLE IF NOT EXISTS SV_PVPGAMES (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `stats_partidas_jugadas` INTEGER, `stats_partidas_ganadas` INTEGER, `stats_monuments_destroyed` INTEGER, `stats_wools_placed` INTEGER, `stats_cores_leakeds` INTEGER, `Kit` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string10 = "CREATE TABLE IF NOT EXISTS BanPlayers (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `By` VARCHAR(24), `Reason` VARCHAR(32), `IP` VARCHAR(16), `Time` INTEGER)";
		String update_string11 = "CREATE TABLE IF NOT EXISTS BanIP (`ID` INTEGER AUTO_INCREMENT UNIQUE, `IP` VARCHAR(24) UNIQUE, `By` VARCHAR(24), `Reason` VARCHAR(32), `Time` INTEGER)";
		String update_string12 = "CREATE TABLE IF NOT EXISTS Mutes (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `By` VARCHAR(24), `Reason` VARCHAR(32), `IP` VARCHAR(16), `Time` INTEGER)";
		String update_string13 = "CREATE TABLE IF NOT EXISTS SV_SKYWARS_KITS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24), `kit` VARCHAR(16))";
		String update_string14 = "CREATE TABLE IF NOT EXISTS SV_HG_KITS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24), `kit` VARCHAR(16))";
		String update_string15 = "CREATE TABLE IF NOT EXISTS SV_BEDWARS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `stats_partidas_jugadas` INTEGER, `stats_partidas_ganadas` INTEGER, `Kit` VARCHAR(16), `glasscolor` VARCHAR(16), `traileffect` VARCHAR(16), PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string16 = "CREATE TABLE IF NOT EXISTS SV_BEDWARS_KITS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24), `kit` VARCHAR(16))";
		String update_string17 = "CREATE TABLE IF NOT EXISTS SV_CHG (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24) UNIQUE, `stats_kills` INTEGER, `stats_deaths` INTEGER, `stats_topkillstreak` INTEGER, `stats_level` INTEGER, `stats_partidas_jugadas` INTEGER, `stats_partidas_ganadas` INTEGER, `Kit` VARCHAR(16), `placecolor` VARCHAR(16), `traileffect` VARCHAR(16), `winner` BOOLEAN, PRIMARY KEY (`ID`), KEY (`Player`))";
		String update_string18 = "CREATE TABLE IF NOT EXISTS SV_CHG_KITS (`ID` INTEGER AUTO_INCREMENT UNIQUE, `Player` VARCHAR(24), `kit` VARCHAR(16))";
		
		Statement update = connection.createStatement();
		update.execute(update_string1);
		update.execute(update_string2);
		update.execute(update_string3);
		update.execute(update_string4);
		update.execute(update_string5);
		update.execute(update_string6);
		update.execute(update_string7);
		update.execute(update_string8);
		update.execute(update_string9);
		update.execute(update_string10);
		update.execute(update_string11);
		update.execute(update_string12);
		update.execute(update_string13);
		update.execute(update_string14);
		update.execute(update_string15);
		update.execute(update_string16);
		update.execute(update_string17);
		update.execute(update_string18);
		update.close(); 
		}
	
	public static void reconnect() {
		setupDatabaseData();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname + "?autoReconnect=true", user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Boolean isMojanPremium(String user){
		try{
			JSONObject json = readJsonFromUrl("https://api.mojang.com/users/profiles/minecraft/" + user);
			return true;
		}catch (Exception e){
			return false;
		}
	}
	public static void setPremium(final String name){
		try{
			PreparedStatement statement =  connection.prepareStatement("SELECT * FROM `" + "Premium" + "` WHERE `Player` = ?;");
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			result.next();
			if(!playerexits(name)){
				PreparedStatement insert = connection
						.prepareStatement("INSERT INTO `" + "Premium" + "` (Player, Premium) VALUES (?,?);");
				insert.setString(1, name);
				insert.setInt(2, 1);
				insert.executeUpdate();

			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}


	public static void createPlayerInfo(final String name){
		try{
			PreparedStatement statement =  connection.prepareStatement("SELECT * FROM `" + "Premium" + "` WHERE `Player` = ?;");
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			result.next();
			if(!playerexits(name)){
				PreparedStatement insert = connection
						.prepareStatement("INSERT INTO `" + "Premium" + "` (Player, Premium) VALUES (?,?);");
				insert.setString(1, name);
				insert.setInt(2, 0);
				insert.executeUpdate();

			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}



	public static Boolean playerexits(String name){
		try{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + "Premium"
					+ "` WHERE `Player` = ?;");
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				return true;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public static void createPremiumTable() {
		try{
			String sqlCreate = "CREATE TABLE IF NOT EXISTS " + "Premium"
					+ " (Player VARCHAR(25) UNIQUE,"
					+ "Premium INTEGER);  ";

			Statement stmt = connection.createStatement();
			stmt.execute(sqlCreate);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
	}



	public static void CheckConnection() {
		//try connections ISCLOSED
		try {
			if(connection == null || connection.isClosed()) {
				reconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reconnect();
		}
	}
	
	private static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
				} catch (final Exception ignored) {
					ignored.printStackTrace();
					}
			}
	}
	
	private static void close(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
				} catch (final Exception ignored) {
					ignored.printStackTrace();
					CheckConnection();
				}
			} else {
				CheckConnection();
			}
	}
	
	public static void loadPlayerAuth_SYNC(final Jugador jug) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM `PlayerAuth` ");
			queryBuilder.append("WHERE `Player` = ? ");
			queryBuilder.append("LIMIT 1;");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null && resultSet.next()) {
				String password = resultSet.getString("Password");
				jug.setPlayerAuth_password(password == null ? "":password);
				jug.setPlayerAuth_lastlogin(resultSet.getLong("LastLogin"));
				jug.setPlayerAuth_registred(true);
				jug.setPlayerAuth_last_ip(resultSet.getString("ip"));
				}
			jug.setPlayerAuth_loaded(true);
			} catch (Exception sqlException) {
				sqlException.printStackTrace(); CheckConnection(); CheckConnection();
				} finally {
					if (resultSet != null) {
						try {
							resultSet.close();
							} catch (final SQLException ignored) {
								ignored.printStackTrace();
								}
						}
					if (preparedStatement != null) {
						try {
							preparedStatement.close();
							} catch (final SQLException ignored) {
								ignored.printStackTrace();
							}
						}
					}
	}
	
	public static LinkedHashMap<String, Integer> getTop(int limit, String toptype, String table) {
		LinkedHashMap<String, Integer> topScore = new LinkedHashMap<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			queryBuilder.append("`Player`, "+toptype+" ");
			queryBuilder.append("FROM ");
			queryBuilder.append("`"+table+"` ");
			queryBuilder.append("ORDER BY "+toptype+" DESC LIMIT ?;");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setInt(1, limit);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet != null && resultSet.next()) {
				topScore.put(resultSet.getString("Player"), resultSet.getInt(toptype));
			}
		} catch (final Exception Exception) {
				Exception.printStackTrace();
		} finally {
			close(resultSet);
			close(preparedStatement);
		}
		
		return topScore;
		}
	
	public static void loadPlayerBungee_SYNC(final Jugador jug) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM `Premium` ");
			queryBuilder.append("WHERE `Player` = ? ");
			queryBuilder.append("LIMIT 1;");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null && resultSet.next()) {
                jug.setOnlineMode(resultSet.getInt("Premium") == 1);

			}
			} catch (Exception sqlException) {
				sqlException.printStackTrace(); CheckConnection();
				} finally {
					if (resultSet != null) {
						try {
							resultSet.close();
							} catch (final SQLException ignored) {
								ignored.printStackTrace();
								}
						}
					if (preparedStatement != null) {
						try {
							preparedStatement.close();
							} catch (final SQLException ignored) {
								ignored.printStackTrace();
							}
						}
					}
	}
	
	public static void loadPlayerCoins_SYNC(final Jugador jug) {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `LCoins` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setLcoins(resultSet.getInt("LCoins"));
					} else {
						createPlayerLCoins(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
		}
	
	public static void loadPlayerCoins_ASYNC(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `LCoins` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setLcoins(resultSet.getInt("LCoins"));
					} else {
						createPlayerLCoins(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerRank_ASYNC(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `Rango` ");
					queryBuilder.append("WHERE `Player` = ?;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setRank(Ranks.valueOf(resultSet.getString("Rank")));
						jug.setRankpoints(resultSet.getInt("Puntos"));
						jug.setRankdurationSQL(resultSet.getLong("Duration"));
						jug.setNameTagColor(ChatColor.valueOf(resultSet.getString("NameTagColor")));
						jug.setHideRank(resultSet.getBoolean("HideRank"));
						}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
	}
	
	public static void loadPlayerRank_SYNC(final Jugador jug) {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `Rango` ");
					queryBuilder.append("WHERE `Player` = ?;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setRank(Ranks.valueOf(resultSet.getString("Rank")));
						jug.setRankpoints(resultSet.getInt("Puntos"));
						jug.setRankdurationSQL(resultSet.getLong("Duration"));
						jug.setNameTagColor(ChatColor.valueOf(resultSet.getString("NameTagColor")));
						jug.setHideRank(resultSet.getBoolean("HideRank"));
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
		}
	
	public static void loadPlayerOpciones_SVS_SYNC(final Jugador jug) {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `Opciones_SVS` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setOpciones_SVS_Enable_Players(resultSet.getBoolean("opc_enablePlayers"));
						jug.setOpciones_SVS_Enable_Chat(resultSet.getBoolean("opc_enableChat"));
						jug.setOpciones_SVS_Enable_Stacker(resultSet.getBoolean("opc_enableStacker"));
						jug.setOpciones_SVS_Enabled_Minilobby(resultSet.getBoolean("opc_minilobby"));
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
	}
	
	public static void loadPlayerOpciones_SVS_ASYNC(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `Opciones_SVS` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setOpciones_SVS_Enable_Players(resultSet.getBoolean("opc_enablePlayers"));
						jug.setOpciones_SVS_Enable_Chat(resultSet.getBoolean("opc_enableChat"));
						jug.setOpciones_SVS_Enable_Stacker(resultSet.getBoolean("opc_enableStacker"));
						jug.setOpciones_SVS_Enabled_Minilobby(resultSet.getBoolean("opc_minilobby"));
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
	}
	
	public static void loadPlayerSV_HG_SYNC(final Jugador jug) {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_HG` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setHG_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setHG_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setHG_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setHG_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
						jug.setHG_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
						jug.setHG_Trail_Effect(resultSet.getString("traileffect"));
						jug.setHG_Place_Color(resultSet.getString("placecolor"));
						jug.setHG_Kit(resultSet.getString("Kit"));
					} else {
						createPlayerSV_HG(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
		}
	
	public static void loadPlayerSV_CHG_SYNC(final Jugador jug) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM `SV_CHG` ");
			queryBuilder.append("WHERE `Player` = ? ");
			queryBuilder.append("LIMIT 1;");
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet != null && resultSet.next()) {
				jug.setCHG_Stats_kills(resultSet.getInt("stats_kills"));
				jug.setCHG_Stats_deaths(resultSet.getInt("stats_deaths"));
				jug.setCHG_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
				jug.setCHG_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
				jug.setCHG_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
				jug.setCHG_Trail_Effect(resultSet.getString("traileffect"));
				jug.setCHG_Place_Color(resultSet.getString("placecolor"));
				jug.setCHG_Winner(resultSet.getBoolean("winner"));
				jug.setCHG_Kit(resultSet.getString("Kit"));
				jug.setHG_Rank(resultSet.getString("ranks"));
				jug.setTotalFame(resultSet.getInt("fame"));
				} else {
					createPlayerSV_CHG(jug);
				}
			jug.setSaveSQL(true);
			} catch (Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(resultSet);
					close(preparedStatement);
				}
}
	
	public static void loadPlayerSV_HG_ASYNC(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_HG` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setHG_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setHG_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setHG_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setHG_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
						jug.setHG_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
						jug.setHG_Trail_Effect(resultSet.getString("traileffect"));
						jug.setHG_Place_Color(resultSet.getString("placecolor"));
						jug.setHG_Kit(resultSet.getString("Kit"));
						jug.setHG_Rank(resultSet.getString("ranks"));
					} else {
						createPlayerSV_HG(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_KITPVP_SYNC(final Jugador jug) {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_KITPVP` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setKitPVP_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setKitPVP_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setKitPVP_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setKitPVP_Kit(resultSet.getString("Kit"));
					} else {
						createPlayerSV_KITPVP(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
		}
	
	public static void loadPlayerSV_KITPVP_ASYNC(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_KITPVP` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setKitPVP_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setKitPVP_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setKitPVP_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setKitPVP_Kit(resultSet.getString("Kit"));
					} else {
						createPlayerSV_KITPVP(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_POTPVP_SYNC(final Jugador jug) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM `SV_POTPVP` ");
			queryBuilder.append("WHERE `Player` = ? ");
			queryBuilder.append("LIMIT 1;");
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet != null && resultSet.next()) {
				jug.setPOTPVP_Stats_kills(resultSet.getInt("stats_kills"));
				jug.setPOTPVP_Stats_deaths(resultSet.getInt("stats_deaths"));
				jug.setPOTPVP_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
				jug.setPOTPVP_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
				jug.setPOTPVP_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
				jug.setPOTPVP_Kit(resultSet.getString("Kit"));
			} else {
				createPlayerSV_POTPVP(jug);
			}
			jug.setSaveSQL(true);
			} catch (Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(resultSet);
					close(preparedStatement);
				}
	}
	
	public static void savePlayerSV_POTPVP(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_POTPVP` SET ");
					queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
					queryBuilder.append("`stats_level` = ?, `stats_partidas_jugadas` = ?, `stats_partidas_ganadas` = ?, `Kit` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getPOTPVP_Stats_kills());
					preparedStatement.setInt(2, jug.getPOTPVP_Stats_deaths());
					preparedStatement.setInt(3, jug.getPOTPVP_Stats_topkillstreak());
					preparedStatement.setInt(4, jug.getPOTPVP_Stats_Level());
					preparedStatement.setInt(5, jug.getPOTPVP_Stats_Partidas_jugadas());
					preparedStatement.setInt(6, jug.getPOTPVP_Stats_Partidas_ganadas());
					preparedStatement.setString(7, jug.getPOTPVP_Kit());
					preparedStatement.setString(8, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_BEDWARS_SYNC(final Jugador jug) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM `SV_BEDWARS` ");
			queryBuilder.append("WHERE `Player` = ? ");
			queryBuilder.append("LIMIT 1;");
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet != null && resultSet.next()) {
				jug.setBEDW_Stats_kills(resultSet.getInt("stats_kills"));
				jug.setBEDW_Stats_deaths(resultSet.getInt("stats_deaths"));
				jug.setBEDW_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
				jug.setBEDW_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
				jug.setBEDW_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
				jug.setBEDW_Kit(resultSet.getString("Kit"));
				jug.setBEDW_Trail_Effect(resultSet.getString("traileffect"));
				jug.setBEDW_Glass_Color(resultSet.getString("glasscolor"));
			} else {
				createPlayerSV_BEDWARS(jug);
			}
			jug.setSaveSQL(true);
			} catch (Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(resultSet);
					close(preparedStatement);
				}
}
	
	public static void loadPlayerSV_SKYWARS_SYNC(final Jugador jug) {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_SKYWARS` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setSKYW_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setSKYW_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setSKYW_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setSKYW_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
						jug.setSKYW_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
						jug.setSKYW_Kit(resultSet.getString("Kit"));
						jug.setSKYW_Trail_Effect(resultSet.getString("traileffect"));
						jug.setSKYW_Glass_Color(resultSet.getString("glasscolor"));
					} else {
						createPlayerSV_SKYW(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
		}
	
	public static void loadPlayerSV_SKYWARS_ASYNC(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_SKYWARS` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setSKYW_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setSKYW_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setSKYW_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setSKYW_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
						jug.setSKYW_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
						jug.setSKYW_Kit(resultSet.getString("Kit"));
						jug.setSKYW_Trail_Effect(resultSet.getString("traileffect"));
						jug.setSKYW_Glass_Color(resultSet.getString("glasscolor"));
					} else {
						createPlayerSV_SKYW(jug);
					}
					jug.setSaveSQL(true);
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_BEDWARS_KITS(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_BEDWARS_KITS` ");
					queryBuilder.append("WHERE `Player` = ?;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null) {
						while (resultSet.next()) {
							jug.addBEDW_OWN_KITS(resultSet.getString("kit"));							
						}
						jug.addBEDW_OWN_KITS("Default");
						}
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_SKYWARS_KITS(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_SKYWARS_KITS` ");
					queryBuilder.append("WHERE `Player` = ?;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null) {
						while (resultSet.next()) {
							jug.addSKYW_OWN_KITS(resultSet.getString("kit"));							
						}
						}
					jug.addSKYW_OWN_KITS("Default");
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_HG_KITS(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_HG_KITS` ");
					queryBuilder.append("WHERE `Player` = ?;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null) {
						while (resultSet.next()) {
							jug.addHG_OWN_KITS(resultSet.getString("kit"));							
						}
						}
					jug.addHG_OWN_KITS("Default");
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_CHG_KITS(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_CHG_KITS` ");
					queryBuilder.append("WHERE `Player` = ?;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null) {
						while (resultSet.next()) {
							jug.addCHG_OWN_KITS(resultSet.getString("kit"));							
						}
						jug.addCHG_OWN_KITS("Default");
						}
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_PVPGAMES(final Jugador jug) {
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("SELECT * ");
					queryBuilder.append("FROM `SV_PVPGAMES` ");
					queryBuilder.append("WHERE `Player` = ? ");
					queryBuilder.append("LIMIT 1;");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet != null && resultSet.next()) {
						jug.setPVPG_Stats_kills(resultSet.getInt("stats_kills"));
						jug.setPVPG_Stats_deaths(resultSet.getInt("stats_deaths"));
						jug.setPVPG_Stats_cores_leakeds(resultSet.getInt("stats_cores_leakeds"));
						jug.setPVPG_Stats_monuments_destroyed(resultSet.getInt("stats_monuments_destroyed"));
						jug.setPVPG_Stats_wools_placed(resultSet.getInt("stats_wools_placed"));
						jug.setPVPG_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
						jug.setPVPG_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
						jug.setPVPG_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
						jug.setPVPG_Kit(resultSet.getString("Kit"));
					} else {
						createPlayerSV_PVPGAMES(jug);
					}
					} catch (Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(resultSet);
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void loadPlayerSV_PVPGAMES_SYNC(final Jugador jug) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM `SV_PVPGAMES` ");
			queryBuilder.append("WHERE `Player` = ? ");
			queryBuilder.append("LIMIT 1;");
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet != null && resultSet.next()) {
				jug.setPVPG_Stats_kills(resultSet.getInt("stats_kills"));
				jug.setPVPG_Stats_deaths(resultSet.getInt("stats_deaths"));
				jug.setPVPG_Stats_cores_leakeds(resultSet.getInt("stats_cores_leakeds"));
				jug.setPVPG_Stats_monuments_destroyed(resultSet.getInt("stats_monuments_destroyed"));
				jug.setPVPG_Stats_wools_placed(resultSet.getInt("stats_wools_placed"));
				jug.setPVPG_Stats_topkillstreak(resultSet.getInt("stats_topkillstreak"));
				jug.setPVPG_Stats_Partidas_jugadas(resultSet.getInt("stats_partidas_jugadas"));
				jug.setPVPG_Stats_Partidas_ganadas(resultSet.getInt("stats_partidas_ganadas"));
				jug.setPVPG_Kit(resultSet.getString("Kit"));
			} else {
				createPlayerSV_PVPGAMES(jug);
			}
			} catch (Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(resultSet);
					close(preparedStatement);
				}
		}
	
	public static void savePlayerBungee(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `Premium` SET ");
					queryBuilder.append("`Premium` = ? ");
					queryBuilder.append("WHERE `Player` = ?; ");
					preparedStatement = connection.prepareStatement(queryBuilder.toString());

					if(jug.isOnlineMode()){
						preparedStatement.setInt(1, 1);
					} else{
						preparedStatement.setInt(1, 0);
					}
					preparedStatement.setString(2, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}

	public static void savePlayerCoins(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `LCoins` SET ");
					queryBuilder.append("`LCoins` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getLcoins());
					preparedStatement.setString(2, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void saveCHGRank(final Jugador jug) {
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `LCoins` SET ");
					queryBuilder.append("`LCoins` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getLcoins());
					preparedStatement.setString(2, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
	}

	public static void saveCHGFame(final Jugador jug) {


		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_CHG` SET ");
					queryBuilder.append("`fame` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");

					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getTotalFame());
					preparedStatement.setString(2, jug.getPlayerName());
					preparedStatement.executeUpdate();
				} catch (final Exception Exception) {
					Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
			}
		});
	}


	
	public static void savePlayerRank(final Jugador jug) {

		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					
					queryBuilder.append("INSERT INTO `Rango` ");
					queryBuilder.append("(`ID`, `Player`, `Rank`, `Puntos`, `Duration`, `NameTagColor`, `HideRank`) ");
					queryBuilder.append("VALUES ");
					queryBuilder.append("(NULL, ?, ?, ?, ?, ?, 0) ");
					queryBuilder.append("ON DUPLICATE KEY UPDATE ");
					queryBuilder.append("`Rank` = ?, `Puntos` = ?, ");
					queryBuilder.append("`Duration` = ?, `NameTagColor` = ?, `HideRank` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					preparedStatement.setString(2, jug.getRank().name());
					preparedStatement.setInt(3, jug.getRankpoints());
					preparedStatement.setLong(4, jug.getRankduration());
					preparedStatement.setString(5, jug.getNameTagColor().name());
					//parte 2 update
					preparedStatement.setString(6, jug.getRank().name());
					preparedStatement.setInt(7, jug.getRankpoints());
					preparedStatement.setLong(8, jug.getRankduration());
					preparedStatement.setString(9, jug.getNameTagColor().name());
					preparedStatement.setBoolean(10, jug.isHideRank());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerRankSYNC(final Jugador jug) {

		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("INSERT INTO `Rango` ");
			queryBuilder.append("(`ID`, `Player`, `Rank`, `Puntos`, `Duration`, `NameTagColor`, `HideRank`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, ?, ?, ?, ?, 0) ");
			queryBuilder.append("ON DUPLICATE KEY UPDATE ");
			queryBuilder.append("`Rank` = ?, `Puntos` = ?, ");
			queryBuilder.append("`Duration` = ?, `NameTagColor` = ?, `HideRank` = ?;");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.setString(2, jug.getRank().name());
			preparedStatement.setInt(3, jug.getRankpoints());
			preparedStatement.setLong(4, jug.getRankduration());
			preparedStatement.setString(5, jug.getNameTagColor().name());
			//parte 2 update
			preparedStatement.setString(6, jug.getRank().name());
			preparedStatement.setInt(7, jug.getRankpoints());
			preparedStatement.setLong(8, jug.getRankduration());
			preparedStatement.setString(9, jug.getNameTagColor().name());
			preparedStatement.setBoolean(10, jug.isHideRank());
			preparedStatement.executeUpdate();
			} catch (final Exception Exception) {
				Exception.printStackTrace();
			} finally {
				close(preparedStatement);
			}
		}
	
	public static void savePlayerOpciones_SVS(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("INSERT INTO `Opciones_SVS` ");
					queryBuilder.append("(`ID`, `Player`, `opc_enablePlayers`, `opc_enableChat`, `opc_enableStacker`, `opc_minilobby`) VALUES");
					queryBuilder.append("(NULL, ?, ?, ?, ?, ?) ");
					queryBuilder.append("ON DUPLICATE KEY UPDATE ");
					queryBuilder.append("`opc_enablePlayers` = ?, `opc_enableChat` = ?, ");
					queryBuilder.append("`opc_enableStacker` = ?, `opc_minilobby` = ?;");					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setString(1, jug.getPlayerName());
					preparedStatement.setBoolean(2, jug.isOpciones_SVS_Enable_Players());
					preparedStatement.setBoolean(3, jug.isOpciones_SVS_Enable_Chat());
					preparedStatement.setBoolean(4, jug.isOpciones_SVS_Enable_Stacker());
					preparedStatement.setBoolean(5, jug.isOpciones_SVS_Enabled_Minilobby());
					preparedStatement.setBoolean(6, jug.isOpciones_SVS_Enable_Players());
					preparedStatement.setBoolean(7, jug.isOpciones_SVS_Enable_Chat());
					preparedStatement.setBoolean(8, jug.isOpciones_SVS_Enable_Stacker());
					preparedStatement.setBoolean(9, jug.isOpciones_SVS_Enabled_Minilobby());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerSV_HG(final Jugador jug) {

		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_HG` SET ");
					queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
					queryBuilder.append("`stats_level` = ?, `stats_partidas_jugadas` = ?, `stats_partidas_ganadas` = ?, `Kit` = ?, `placecolor` = ?, `traileffect` = ?, `Ranks` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getHG_Stats_kills());
					preparedStatement.setInt(2, jug.getHG_Stats_deaths());
					preparedStatement.setInt(3, jug.getHG_Stats_topkillstreak());
					preparedStatement.setInt(4, jug.getHG_Stats_Level());
					preparedStatement.setInt(5, jug.getHG_Stats_Partidas_jugadas());
					preparedStatement.setInt(6, jug.getHG_Stats_Partidas_ganadas());
					preparedStatement.setString(7, jug.getHG_Kit());
					preparedStatement.setString(8, jug.getHG_Place_Color());
					preparedStatement.setString(9, jug.getHG_Trail_Effect());
					preparedStatement.setString(10, jug.getHG_Rank());
					preparedStatement.setString(11, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerSV_CHG(final Jugador jug) {

		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_CHG` SET ");
					queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
					queryBuilder.append("`stats_level` = ?, `stats_partidas_jugadas` = ?, `stats_partidas_ganadas` = ?, `Kit` = ?, `placecolor` = ?, `traileffect` = ?, `winner` = ?,`ranks` = ?");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getCHG_Stats_kills());
					preparedStatement.setInt(2, jug.getCHG_Stats_deaths());
					preparedStatement.setInt(3, jug.getCHG_Stats_topkillstreak());
					preparedStatement.setInt(4, jug.getCHG_Stats_Level());
					preparedStatement.setInt(5, jug.getCHG_Stats_Partidas_jugadas());
					preparedStatement.setInt(6, jug.getCHG_Stats_Partidas_ganadas());
					preparedStatement.setString(7, jug.getCHG_Kit());
					preparedStatement.setString(8, jug.getCHG_Place_Color());
					preparedStatement.setString(9, jug.getCHG_Trail_Effect());
					preparedStatement.setBoolean(10, jug.isCHG_Winner());
					preparedStatement.setString(11, jug.getHG_Rank());
					preparedStatement.setString(12, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerSV_KITPVP(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_KITPVP` SET ");
					queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
					queryBuilder.append("`stats_level` = ?, `Kit` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getKitPVP_Stats_kills());
					preparedStatement.setInt(2, jug.getKitPVP_Stats_deaths());
					preparedStatement.setInt(3, jug.getKitPVP_Stats_topkillstreak());
					preparedStatement.setInt(4, jug.getKitPVP_Stats_Level());
					preparedStatement.setString(5, jug.getKitPVP_Kit());
					preparedStatement.setString(6, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerSV_SKYWARS(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_SKYWARS` SET ");
					queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
					queryBuilder.append("`stats_level` = ?, `stats_partidas_jugadas` = ?, `stats_partidas_ganadas` = ?, `Kit` = ?, `glasscolor` = ?, `traileffect` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getSKYW_Stats_kills());
					preparedStatement.setInt(2, jug.getSKYW_Stats_deaths());
					preparedStatement.setInt(3, jug.getSKYW_Stats_topkillstreak());
					preparedStatement.setInt(4, jug.getSKYW_Stats_Level());
					preparedStatement.setInt(5, jug.getSKYW_Stats_Partidas_jugadas());
					preparedStatement.setInt(6, jug.getSKYW_Stats_Partidas_ganadas());
					preparedStatement.setString(7, jug.getSKYW_Kit());
					preparedStatement.setString(8, jug.getSKYW_Glass_Color());
					preparedStatement.setString(9, jug.getSKYW_Trail_Effect());
					preparedStatement.setString(10, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerSV_BEDWARS(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				PreparedStatement preparedStatement = null;
				try {
					StringBuilder queryBuilder = new StringBuilder();
					queryBuilder.append("UPDATE `SV_BEDWARS` SET ");
					queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
					queryBuilder.append("`stats_level` = ?, `stats_partidas_jugadas` = ?, `stats_partidas_ganadas` = ?, `Kit` = ?, `glasscolor` = ?, `traileffect` = ? ");
					queryBuilder.append("WHERE `Player` = ?;");
					
					preparedStatement = connection.prepareStatement(queryBuilder.toString());
					preparedStatement.setInt(1, jug.getBEDW_Stats_kills());
					preparedStatement.setInt(2, jug.getBEDW_Stats_deaths());
					preparedStatement.setInt(3, jug.getBEDW_Stats_topkillstreak());
					preparedStatement.setInt(4, jug.getBEDW_Stats_Level());
					preparedStatement.setInt(5, jug.getBEDW_Stats_Partidas_jugadas());
					preparedStatement.setInt(6, jug.getBEDW_Stats_Partidas_ganadas());
					preparedStatement.setString(7, jug.getBEDW_Kit());
					preparedStatement.setString(8, jug.getBEDW_Glass_Color());
					preparedStatement.setString(9, jug.getBEDW_Trail_Effect());
					preparedStatement.setString(10, jug.getPlayerName());
					preparedStatement.executeUpdate();
					} catch (final Exception Exception) {
						Exception.printStackTrace();
						} finally {
							close(preparedStatement);
						}
				}
			});
		}
	
	public static void savePlayerSV_PVPGAMES(final Jugador jug) {
		if(!jug.SaveSQL()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(LcCore.getInstance(), () -> {
            PreparedStatement preparedStatement = null;
            try {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("UPDATE `SV_PVPGAMES` SET ");
                queryBuilder.append("`stats_kills` = ?, `stats_deaths` = ?, `stats_topkillstreak` = ?, ");
                queryBuilder.append("`stats_level` = ?, `stats_partidas_jugadas` = ?, `stats_partidas_ganadas` = ?, ");
                queryBuilder.append("`stats_monuments_destroyed` = ?, `stats_wools_placed` = ?, `stats_cores_leakeds` = ?, `Kit` = ? ");
                queryBuilder.append("WHERE `Player` = ?;");

                preparedStatement = connection.prepareStatement(queryBuilder.toString());
                preparedStatement.setInt(1, jug.getPVPG_Stats_kills());
                preparedStatement.setInt(2, jug.getPVPG_Stats_deaths());
                preparedStatement.setInt(3, jug.getPVPG_Stats_topkillstreak());
                preparedStatement.setInt(4, jug.getPVPG_Stats_Level());
                preparedStatement.setInt(5, jug.getPVPG_Stats_Partidas_jugadas());
                preparedStatement.setInt(6, jug.getPVPG_Stats_Partidas_ganadas());
                preparedStatement.setInt(7, jug.getPVPG_Stats_monuments_destroyed());
                preparedStatement.setInt(8, jug.getPVPG_Stats_wools_placed());
                preparedStatement.setInt(9, jug.getPVPG_Stats_cores_leakeds());
                preparedStatement.setString(10, jug.getPVPG_Kit());
                preparedStatement.setString(11, jug.getPlayerName());
                preparedStatement.executeUpdate();
                } catch (final Exception Exception) {
                    Exception.printStackTrace();
                    } finally {
                        close(preparedStatement);
                    }
            });
		}
	public static void createPlayerLCoins(Jugador jug) {
		PreparedStatement preparedStatement = null;
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `LCoins` ");
			queryBuilder.append("(`ID`, `Player`, `LCoins`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0);");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerRango(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `Rango` ");
			queryBuilder.append("(`ID`, `Player`, `Rank`, `Puntos`, `Duration`, `NameTagColor`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 'DEFAULT', 0, 0, 'YELLOW');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void deletePlayerRango(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("DELETE FROM `Rango` ");
			queryBuilder.append("WHERE `Player` = ?;");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
	}
	public static void createPlayerSV_HG(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_HG` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `stats_partidas_jugadas`, `stats_partidas_ganadas`, `Kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 0, 0, 'default');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerSV_CHG(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_CHG` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `stats_partidas_jugadas`, `stats_partidas_ganadas`, `Kit` , `winner`, `ranks`, `fame`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 0, 0, 'default', 0, 'Nuevo', 0);");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerSV_KITPVP(Jugador jug) {
		PreparedStatement preparedStatement = null;
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_KITPVP` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `Kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 'default');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerSV_SKYW(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_SKYWARS` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `stats_partidas_jugadas`, `stats_partidas_ganadas`, `Kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 0, 0, 'default');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerSV_BEDWARS(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_BEDWARS` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `stats_partidas_jugadas`, `stats_partidas_ganadas`, `Kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 0, 0, 'default');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerSV_POTPVP(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_POTPVP` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `stats_partidas_jugadas`, `stats_partidas_ganadas`, `Kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 0, 0, 'default');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void BuyKit_SV_BEDWARS(Jugador jug, String kitname) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_BEDWARS_KITS` ");
			queryBuilder.append("(`ID`, `Player`, `kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, ?);");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.setString(2, kitname);
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void BuyKit_SV_SKYW(Jugador jug, String kitname) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_SKYWARS_KITS` ");
			queryBuilder.append("(`ID`, `Player`, `kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, ?);");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.setString(2, kitname);
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void BuyKit_SV_HG(Jugador jug, String kitname) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_HG_KITS` ");
			queryBuilder.append("(`ID`, `Player`, `kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, ?);");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.setString(2, kitname);
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void BuyKit_SV_CHG(Jugador jug, String kitname) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_CHG_KITS` ");
			queryBuilder.append("(`ID`, `Player`, `kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, ?);");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.setString(2, kitname);
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void createPlayerSV_PVPGAMES(Jugador jug) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO `SV_PVPGAMES` ");
			queryBuilder.append("(`ID`, `Player`, `stats_kills`, `stats_deaths`, `stats_topkillstreak`, `stats_level`, `stats_partidas_jugadas`, `stats_partidas_ganadas`, `stats_monuments_destroyed`, `stats_wools_placed`, `stats_cores_leakeds`, `Kit`) ");
			queryBuilder.append("VALUES ");
			queryBuilder.append("(NULL, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'default');");
			
			preparedStatement = connection.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, jug.getPlayerName());
			preparedStatement.executeUpdate();
			jug.setSaveSQL(true);
			} catch (final Exception Exception) {
				Exception.printStackTrace();
				} finally {
					close(preparedStatement);
				}
		}
	
	public static void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
				connection = null;
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
	}
}