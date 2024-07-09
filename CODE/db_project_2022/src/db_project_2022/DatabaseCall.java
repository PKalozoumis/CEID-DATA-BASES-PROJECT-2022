package db_project_2022;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;

public class DatabaseCall
{
	private String name;
	
	//We assume that in every stored procedure we use, in parameters go first, and then follow the out parameters
	private LinkedList<Pair<Object, Integer>> input; //object and type
	private LinkedList<Integer> output; //only type
	
	public DatabaseCall(String name, LinkedList<Pair<Object, Integer>> input)
	{
		this.name = name;
		this.input = input;
		this.output = null;
	}
	
	public DatabaseCall(String name, LinkedList<Pair<Object, Integer>> input, LinkedList<Integer> output)
	{
		this.name = name;
		this.input = input;
		this.output = output;
	}
	
	public String name()
	{
		return name;
	}
	
	public LinkedList<Pair<Object, Integer>> input()
	{
		return input;
	}
	
	public LinkedList<Integer> output()
	{
		return output;
	}
	
	public int inCount()
	{
		if (input != null)
			return input.size();
		else return 0;
	}
	
	public int outCount()
	{
		if (output != null)
			return output.size();
		else return 0;
	}
	
	public int parameterCount()
	{
		return inCount() + outCount();
	}
	
	public void execute() throws SQLException
	{
		//Prepare the call
		//===========================================================================
		
		String str = "{CALL " + name + "(";
		
		for (int i = 0; i < parameterCount(); ++i)
		{
			str += "?";
			
			if (i < parameterCount() - 1)
				str += ", ";
		}
		
		str += ")}";
		
		//System.out.println(query);
		
		//===========================================================================
		
		//Make the call
		//===========================================================================

		Main.call = Main.connection().prepareCall(str);
		
		int j = 0;
		
		if (input != null)
		{
			for (Pair<Object, Integer> p : input)
			{
				Main.call.setObject(j+1, p.first(), p.second());
				j++;
			}
		}
		
		if (output != null)
		{
			for (Integer type : output)
			{
				Main.call.registerOutParameter(j+1, type);
				j++;
			}
		}
		
		Main.res = Main.call.executeQuery();
	}
}
