package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class Database {
	private static final String URL="./DataLayer/sqlite/";
	private static final String DB_NAME="Bank.db";
	private static final String CONNECTION_STRING="jdbc:sqlite:" + URL + DB_NAME;
	
	private Connection connection;	
	
	public Database() {
//		createTables();
	}
	
//	private void createTables() {
//    	try(Connection conn = Open();
//    		Statement statement = conn.createStatement()) {
//    		statement.execute("CREATE TABLE IF NOT EXISTS " + Tables.TABLE_USER + " ("
//    				+ Tables.COL_USER_USERNAME + " TEXT PRIMARY KEY,"
//    				+ Tables.COL_USER_PASSWORD + " TEXT NOT NULL,"
//    				+ Tables.COL_USER_FULLNAME + " TEXT,"
//    				+ Tables.COL_USER_TYPEID + " INTEGER NOT NULL,"
//    				+ "FOREIGN KEY (" + Tables.COL_USER_TYPEID 
//    				+ ") REFERENCES " + Tables.TABLE_TYPE + "(" + Tables.COL_TYPE_TYPEID + "))");
//    		
//    		statement.execute("CREATE TABLE IF NOT EXISTS " + Tables.TABLE_TYPE + " ("
//    				+ Tables.COL_TYPE_TYPEID + " INTEGER PRIMARY KEY,"
//    				+ Tables.COL_TYPE_TYPE + " TEXT NOT NULL)");
//
//	CREATE TABLE "Accounts" (
//			"accountNumber"	INTEGER,
//			"username"	TEXT NOT NULL,
//			"inventory"	INTEGER NOT NULL DEFAULT 0 CHECK(inventory>=0),
//			PRIMARY KEY("accountNumber"),
//			FOREIGN KEY("username") REFERENCES "Users"("username") ON DELETE CASCADE
//		);
//	
//	CREATE TABLE "CustomerInfo" (
//			"username"	TEXT,
//			"phoneNumber"	TEXT,
//			"address"	TEXT,
//			FOREIGN KEY("username") REFERENCES "Users"("username") ON DELETE CASCADE,
//			PRIMARY KEY("username")
//		);
//	
//
//		} catch(SQLException e) {
//			System.out.println("Error [DataBase.createTables]");
//			printWarning(e);
//		} finally {
//			close();
//		}
//	}
	
	public Connection Open() {
		try {
//			Properties properties = new Properties();
//			properties.setProperty("PRAGMA foreign_keys", "ON");
			
			SQLiteConfig config = new SQLiteConfig();  
	        config.enforceForeignKeys(true);
			connection = DriverManager.getConnection(CONNECTION_STRING, config.toProperties());

			return connection;
		}
		catch(SQLException e)
		{
			printWarning(e, "DataBase.Open");
			return null;
		}
	}
	
	public boolean close() {
		try {
			if(connection != null)
				connection.close();
			return true;
		}
		catch(SQLException e)
		{
			printWarning(e, "DataBase.close");
			return false;
		}
	}
	
	public void printWarning(SQLException e, String location) {
		System.out.println("\n*** Error ***");
		System.out.println("Error [" + location +"]");
		System.out.println("Message: " + e.getMessage());
	    System.out.println("SQLState: " + e.getSQLState());
	    System.out.println("Vendor error code: " + e.getErrorCode());
	}
}
