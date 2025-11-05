package db_project_2022;

import java.sql.SQLException;
import java.util.LinkedList;

public class DatabaseQuery
{
	private String query;
	private LinkedList<Pair<Object, Integer>> values; //object and type
	
	DatabaseQuery(String query, LinkedList<Pair<Object, Integer>> values)
	{
		this.query = query;
		this.values = values;
	}
	
	DatabaseQuery(String query)
	{
		this.query = query;
		this.values = null;
	}
	
	public String query()
	{
		return query;
	}
	
	public LinkedList<Pair<Object, Integer>> values()
	{
		return values;
	}
	
	public int valueCount()
	{
		if (values != null)
			return values.size();
		else return 0;
	}
	
	public void execute() throws SQLException
	{
		Main.statement = Main.connection().prepareStatement(query);
		
		int j = 0;
		
		if (values != null)
		{
			for (Pair<Object, Integer> p : values)
			{
				Main.statement.setObject(j+1, p.first(), p.second());
				j++;
			}
		}
		
		Main.res = Main.statement.executeQuery();
	}
	
	public int executeUpdate() throws SQLException
	{
		Main.statement = Main.connection().prepareStatement(query);
		
		int j = 0;
		
		if (values != null)
		{
			for (Pair<Object, Integer> p : values)
			{
				Main.statement.setObject(j+1, p.first(), p.second());
				j++;
			}
		}
		
		return Main.statement.executeUpdate();
	}
}
