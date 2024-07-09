package db_project_2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Main
{
	public static final int NONE = -1,
	ADMIN = 0, BRANCH = 1, DESTINATION = 2,DRIVER = 3,
	EVENT = 4,GUIDE = 5,IT_WORKER = 6,LANGUAGES = 7,
	LOG = 8,MANAGES = 9,OFFER = 10,PHONES = 11,RESERVATION = 12,
	RESERVATION_OFFER = 13,TRAVEL_TO = 14,TRIP = 15,WORKER = 16;
	
	private static List<DatabaseTable> tables = new ArrayList<DatabaseTable>(); //used to be linkedlist
			
	private static final String DBURL = "jdbc:mysql://localhost:3306/db_project_2022";
	private static String uploadPath;
	private static Connection con = null;
	public static PreparedStatement statement = null;
	public static ResultSet res = null;
	public static CallableStatement call = null;
	
	//private static List<LinkedList<String>> keys = new ArrayList<LinkedList<String>>();
	
	private Main(){}
	
    public static void main(String[] args)
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				disconnect();
			}
		}, "disconnection"));
		
		connect();
		
		uploadPath = setupUploadPath();
		
		//getAllPrimaryKeys();
		setupTables();
		
		//setupReservationOffer();
		LoginWindow.loginWindow();
    }
	
	public static DatabaseTable table(int tableNum)
	{
		return tables.get(tableNum);
	}
	
	public static void connect()
	{
		try
		{
			con = DriverManager.getConnection(DBURL, "root", "");
			System.out.println("Connected to database.");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void disconnect()
	{
		if (res != null)
		{
			try {res.close();}
			catch(SQLException e){e.printStackTrace();}
		}

		if (statement != null)
		{
			try {statement.close();}
			catch(SQLException e){e.printStackTrace();}
		}
		
		if (call != null)
		{
			try {call.close();}
			catch(SQLException e){e.printStackTrace();}
		}
			
		if (con != null)
		{
			try
			{
				con.close();
				con = null;
				System.out.println("Disconnected from database.");
			}
			catch(SQLException e){
				e.printStackTrace();}
		}
	}
	
	public static Connection connection()
	{
		return con;
	}
	
	public static void printResults(ResultSet res) throws SQLException
	{
		ResultSetMetaData meta = res.getMetaData();
		
		int columnCount = meta.getColumnCount();
		
		for (int i = 1; i <= columnCount; ++i)
		{
			System.out.printf("%-16s\t", meta.getColumnName(i));
		}

		System.out.println();

		while (res.next())
		{
			for (int i = 1; i <= columnCount; ++i)
			{
				System.out.printf("%-16s\t", res.getObject(i));
			}

			System.out.println();
		}

		System.out.println();
	}
	
	private static void setupTables()
	{
		try
		{
			DatabaseMetaData md = con.getMetaData();	
			
			//Get information about the tables
			//Each row of the result set contains many things
			//I only need TABLE_NAME
			ResultSet resTables = md.getTables("db_project_2022", "db_project_2022", "%", new String[]{"TABLE"});
			
			int tableNum = 0;
			
			while (resTables.next())
			{
				LinkedList<Integer> tableKeys = new LinkedList<Integer>();
				
				String tableName = resTables.getString("TABLE_NAME");
				//System.out.println(tableName);
				
				//SELECT * QUERY TO GET ROWS, COLUMNS, COLUMN NAMES/ALIASES, ETC...
				//============================================================================================
				List<DatabaseColumn> tableColumns = new ArrayList<DatabaseColumn>();
				
				Main.statement = Main.connection().prepareStatement("SELECT * FROM " + tableName, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				Main.res = Main.statement.executeQuery();
				ResultSetMetaData rsmd = Main.res.getMetaData();
				
				//Rows
				Main.res.last();
				int tableRowCount = Main.res.getRow();
				Main.res.beforeFirst();
				
				//Columns
				int tableColumnCount = rsmd.getColumnCount();
				
				for (int i = 0; i < tableColumnCount; ++i)
				{
					//System.out.printf("Name: %s\nAlias: %s\nType: %d %s\n", rsmd.getColumnName(i+1), rsmd.getColumnLabel(i+1), rsmd.getColumnType(i+1), rsmd.getColumnTypeName(i+1));
					tableColumns.add(new DatabaseColumn(rsmd.getColumnName(i+1), rsmd.getColumnLabel(i+1), rsmd.getColumnType(i+1)));
				}
				
				//Aliases
				setupAliases(tableNum, tableColumns);
				//============================================================================================
						
				//DETERMINE THE PRIMARY KEYS
				//============================================================================================
				ResultSet resKeys = md.getPrimaryKeys(null, null, tableName);
				
				while(resKeys.next())
				{
					String key = resKeys.getString("COLUMN_NAME");
					int pos = resKeys.getInt("KEY_SEQ");
					
					//System.out.printf("KEY: %s POS: %d\n", key, pos);
					tableKeys.add(pos-1);
				}
				
				resKeys.close();
				//============================================================================================
				
				//System.out.printf("%02d : %s\n", tableNum, tableName);
				
				tables.add(new DatabaseTable(tableName, tableNum, tableRowCount, tableColumnCount, tableColumns, tableKeys));
				//tables.get(tableNum).print();
				
				tableNum++;
			}
			
			resTables.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static String setupUploadPath()
	{
		String str = "";
		
		try
		{
			statement = con.prepareStatement("SHOW VARIABLES LIKE 'secure_file_priv'");
			res = statement.executeQuery();
		
			if (res.next())
			{
				str = res.getString(2);
				str = str.replaceAll("\\\\", "\\\\\\\\");
				str += "loggedIn.txt";
				//System.out.println(str);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static String uploadPath()
	{
		return uploadPath;
	}
	
	private static void  setupAliases(int tableNum, List<DatabaseColumn> tableColumns)
	{
		switch(tableNum)
		{
			case ADMIN:
			{
				tableColumns.get(0).setAlias("AT");
				tableColumns.get(1).setAlias("Type");
				tableColumns.get(2).setAlias("Diploma");
			}
			break;
			
			case BRANCH:
			{
				tableColumns.get(0).setAlias("Branch Code");
				tableColumns.get(1).setAlias("Street");
				tableColumns.get(2).setAlias("Number");
				tableColumns.get(3).setAlias("City");
			}
			break;
			
			case DESTINATION:
			{
				tableColumns.get(0).setAlias("ID");
				tableColumns.get(1).setAlias("Name");
				tableColumns.get(2).setAlias("Description");
				tableColumns.get(3).setAlias("Type");
				tableColumns.get(4).setAlias("Language");
				tableColumns.get(5).setAlias("Location");
			}
			break;
			
			case DRIVER:
			{
				tableColumns.get(0).setAlias("AT");
				tableColumns.get(1).setAlias("License");
				tableColumns.get(2).setAlias("Route");
				tableColumns.get(3).setAlias("Experience");
			}
			break;
			
			case EVENT:
			{
				tableColumns.get(0).setAlias("Trip ID");
				tableColumns.get(1).setAlias("Start Date");
				tableColumns.get(2).setAlias("End Date");
				tableColumns.get(3).setAlias("Description");
			}
			break;
			
			case GUIDE:
			{
				tableColumns.get(0).setAlias("AT");
				tableColumns.get(1).setAlias("CV");
			}
			break;
			
			case IT_WORKER:
			{
				tableColumns.get(0).setAlias("AT");
				tableColumns.get(1).setAlias("Username");
				tableColumns.get(2).setAlias("Password");
				tableColumns.get(3).setAlias("Start Date");
				tableColumns.get(4).setAlias("End Date");
			}
			break;
			
			case LANGUAGES:
			{
				tableColumns.get(0).setAlias("Guide AT");
				tableColumns.get(1).setAlias("Language");
			}
			break;
			
			case LOG:
			{
				tableColumns.get(0).setAlias("Entry ID");
				tableColumns.get(1).setAlias("IT Manager AT");
				tableColumns.get(2).setAlias("Table");
				tableColumns.get(3).setAlias("Action");
				tableColumns.get(4).setAlias("Time");
			}
			break;
			
			case MANAGES:
			{
				tableColumns.get(0).setAlias("Admin AT");
				tableColumns.get(1).setAlias("Branch Code");
			}
			break;
			
			case OFFER:
			{
				tableColumns.get(0).setAlias("ID");
				tableColumns.get(1).setAlias("Destination");
				tableColumns.get(2).setAlias("Start Date");
				tableColumns.get(3).setAlias("End Date");
				tableColumns.get(4).setAlias("Cost");
			}
			break;
			
			case PHONES:
			{
				tableColumns.get(0).setAlias("Branch Code");
				tableColumns.get(1).setAlias("Phone Number");
			}
			break;
			
			case RESERVATION:
			{
				tableColumns.get(0).setAlias("Trip ID");
				tableColumns.get(1).setAlias("Seat Number");
				tableColumns.get(2).setAlias("Name");
				tableColumns.get(3).setAlias("Lastname");
				tableColumns.get(4).setAlias("Age");
			}
			break;
			
			case RESERVATION_OFFER:
			{
				tableColumns.get(0).setAlias("ID");
				tableColumns.get(1).setAlias("Offer ID");
				tableColumns.get(2).setAlias("Name");
				tableColumns.get(3).setAlias("Lastname");
				tableColumns.get(4).setAlias("Deposit");
			}
			break;
			
			case TRAVEL_TO:
			{
				tableColumns.get(0).setAlias("Trip ID");
				tableColumns.get(1).setAlias("Destination ID");
				tableColumns.get(2).setAlias(" Arrival");
				tableColumns.get(3).setAlias("Departure");
			}
			break;
			
			case TRIP:
			{
				tableColumns.get(0).setAlias("ID");
				tableColumns.get(1).setAlias("Departure");
				tableColumns.get(2).setAlias("Return");
				tableColumns.get(3).setAlias("Max Seats");
				tableColumns.get(4).setAlias("Cost");
				tableColumns.get(5).setAlias("Branch Code");
				tableColumns.get(6).setAlias("Guide AT");
				tableColumns.get(7).setAlias("Driver AT");
			}
			break;
			
			case WORKER:
			{
				tableColumns.get(0).setAlias("AT");
				tableColumns.get(1).setAlias("Name");
				tableColumns.get(2).setAlias("Lastname");
				tableColumns.get(3).setAlias("Salary");
				tableColumns.get(4).setAlias("Branch Code");
			}
			break;
		}
	}
	
	private static void setupReservationOffer()
	{
		String str = "";
		
		try
		{
			statement = con.prepareStatement("SHOW VARIABLES LIKE 'secure_file_priv'");
			res = statement.executeQuery();
		
			if (res.next())
			{
				str = res.getString(2);
				str = str.replaceAll("\\\\", "\\\\\\\\");
				str += "names.txt";
				//System.out.println(str);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		File file = new File(str);
		
		try
		{
			Scanner scan = new Scanner(file);
			
			List<String> names = new LinkedList<String>();
			List<String> lnames = new LinkedList<String>();
			
			while (scan.hasNextLine())
			{
				String data = scan.nextLine();
				String[] parts = data.split("\\s+");
				names.add(parts[0]);
				lnames.add(parts[1]);
				
				//System.out.println(data);
			}
			
			scan.close();
			
			DatabaseQuery query  = new DatabaseQuery("delete from reservation_offer");
			query.executeUpdate();
			
			query  = new DatabaseQuery("ALTER TABLE reservation_offer AUTO_INCREMENT = 0;");
			query.executeUpdate();
			
			Random rand = new Random();
			
			for (int i = 0; i < 60000; i++)
			{
				System.out.println(i);
				LinkedList<Pair<Object, Integer>> values = new LinkedList<Pair<Object, Integer>>();
				values.add(new Pair<Object, Integer>(null, Types.INTEGER));
				values.add(new Pair<Object, Integer>(i/20000 + 1, Types.INTEGER));
				values.add(new Pair<Object, Integer>(names.get(rand.nextInt(10000)), Types.VARCHAR));
				values.add(new Pair<Object, Integer>(lnames.get(rand.nextInt(10000)), Types.VARCHAR));
				values.add(new Pair<Object, Integer>(rand.nextFloat(150) + 50, Types.FLOAT));
				
				query  = new DatabaseQuery("insert into reservation_offer values (?,?,?,?,?)",values);
				query.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//OBSOLETE
	
	/*public static LinkedList<String> getKeysOf(int table)
	{
		return keys.get(table);
	}
	
	private static void getAllPrimaryKeys()
	{
		try
		{
			DatabaseMetaData md = con.getMetaData();
			
			statement = con.prepareStatement("SHOW TABLES");
			ResultSet names = statement.executeQuery();
			ResultSet keyname;
			
			int i = 0;
			
			while (names.next())
			{
				keys.add(new LinkedList<String>());
				keyname = md.getPrimaryKeys(null, null, names.getString(1));
				while(keyname.next())
					keys.get(i).add(keyname.getString("COLUMN_NAME"));
				
				i++;
			}
			
			//printKeys();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/
}