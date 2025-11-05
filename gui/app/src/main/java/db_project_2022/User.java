package db_project_2022;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;

public class User
{
	private static String username, name, lname, id;
	
	public static String username()
	{
		return User.username;
	}
	
	public static String name()
	{
		return User.name;
	}
	
	public static String lname()
	{
		return User.lname;
	}
	
	public static String id() { return User.id; }
	
	public static void setLoggedInUser(String userId) throws SQLException
	{
		User.id = userId;

		//Empty the logged-in user table
		//---------------------------------------------------------------------------
		Main.statement = Main.connection().prepareStatement("DELETE FROM logged_in_user");
		Main.statement.executeUpdate();

		//Insert the current user
		//---------------------------------------------------------------------------
		LinkedList<Pair<Object, Integer>> list = new LinkedList<Pair<Object, Integer>>();
		list.add(new Pair<Object, Integer>(userId, Types.CHAR));

		DatabaseQuery query = new DatabaseQuery("INSERT INTO logged_in_user VALUES (?)", list);
		query.executeUpdate();

		//Retrieve worker info from the database, based on the ID
		//---------------------------------------------------------------------------
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
			User.username = Main.res.getString(1);
			User.name = Main.res.getString(2);
			User.lname = Main.res.getString(3);
		}
	}
}
