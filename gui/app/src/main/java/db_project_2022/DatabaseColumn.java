package db_project_2022;

public class DatabaseColumn
{
	private String name;
	private String alias;
	private int type;
	
	public DatabaseColumn(String name, String alias, int type)
	{
		this.name = name;
		this.alias = alias;
		this.type = type;
	}
	
	public String name()
	{
		return name;
	}
	
	public String alias()
	{
		return alias;
	}
	
	public int type()
	{
		return type;
	}
	
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
	
	public void print()
	{
		System.out.printf("%s AS %s\n", name, alias);
	}
}
