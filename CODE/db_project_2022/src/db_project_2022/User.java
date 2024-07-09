package db_project_2022;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class User
{
	private static String username, name, lname, id;
	
	public static String username()
	{
		return username;
	}
	
	public static String name()
	{
		return name;
	}
	
	public static String lname()
	{
		return lname;
	}
	
	public static String id()
	{
		return id;
	}
	
	public static void setLoggedInUser(String userId) throws SQLException
	{
		id = userId;
		
		try
		(
			PrintWriter pw = new PrintWriter(Main.uploadPath());
		)
		{
			pw.print(id);
			pw.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Main.statement = Main.connection().prepareStatement
		(
			"SELECT it_username, wrk_name, wrk_lname " + 
			"FROM it_worker " +
			"INNER JOIN worker ON it_worker.it_AT = worker.wrk_AT " +
			"WHERE wrk_AT = ?"
		);

		Main.statement.setString(1, userId);
		Main.statement.executeQuery();
		Main.res = Main.statement.getResultSet();
		
		if (Main.res.next())
		{
			username = Main.res.getString(1);
			name = Main.res.getString(2);
			lname = Main.res.getString(3);
		}
	}
}
