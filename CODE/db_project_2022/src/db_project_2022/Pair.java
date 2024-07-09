package db_project_2022;

public class Pair<T, U>
{
	private T first;
	private U second;
	
	public Pair(T first, U second)
	{
		this.first = first;
		this.second = second;
	}
	
	T first()
	{
		return first;
	}
	
	U second()
	{
		return second;
	}
}
