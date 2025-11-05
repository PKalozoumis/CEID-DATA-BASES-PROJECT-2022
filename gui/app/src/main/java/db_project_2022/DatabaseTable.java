package db_project_2022;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DatabaseTable
{
	public static JTable mainTable;
			
	private String name;
	private int num;
	private int rowCount, columnCount;
	
	private List<DatabaseColumn> columns; //Arraylist
	private LinkedList<Integer> keys;
	
	private List<Object> editFields; //Arraylist
	
	public DatabaseTable(String name, int num, int rowCount, int columnCount, List<DatabaseColumn> columns, LinkedList<Integer> keys)
	{
		this.name = name;
		this.num = num;
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.columns = columns;
		this.keys = keys;
	}
	
	public String columnAlias(int i)
	{
		return columns.get(i).alias();
	}
	
	public String columnName(int i)
	{
		return columns.get(i).name();
	}
	
	public void setRowCount(int rowCount)
	{
		this.rowCount = rowCount;
	}
	
	public int rowCount()
	{
		return rowCount;
	}
	
	public int columnCount()
	{
		return columnCount;
	}
	
	public String name()
	{
		return name;
	}
	
	public int columnType(int i)
	{
		return columns.get(i).type();
	}
	
	public void setEditFields(List<Object> editFields)
	{
		this.editFields = editFields;
	}
	
	public void setValues()
	{
		int row = mainTable.getSelectedRow();
		int i = 0;
		
		for (Object obj : editFields)
		{
			if (obj instanceof JComboBox)
			{
				((JComboBox<String>)obj).setSelectedItem(String.valueOf(mainTable.getValueAt(row, i)));
			}
			else if (obj instanceof JTextComponent)
			{
				String text = String.valueOf(mainTable.getValueAt(row, i));
				
				if (text.equals("NULL"))
				{
					((JTextComponent)obj).setText("");
				}
				else ((JTextComponent)obj).setText(text);
				
			}
			else if (obj instanceof DatetimeFields)
			{
				((DatetimeFields)obj).setDatetime();
			}
			
			i++;
		}
	}
	
	public void resetValues()
	{
		int i = 0;
		
		for (Object obj : editFields)
		{
			if (obj instanceof JComboBox)
			{
				((JComboBox<String>)obj).setSelectedIndex(0);
			}
			else if (obj instanceof JTextComponent)
			{
				((JTextComponent)obj).setText("");
			}
			else if (obj instanceof DatetimeFields)
			{
				((DatetimeFields)obj).resetDatetime();
			}
			
			i++;
		}
	}
	
	public void print()
	{
		System.out.println("===========================================");
		
		System.out.printf("Name: %s\nNum: %d\nRows: %d\nColumns: %d\n\nColumns:\n", name, num, rowCount, columnCount);
		
		for (DatabaseColumn c : columns)
			c.print();
		
		System.out.println("\nKeys:");
		
		for (Integer p : keys)
		{
			System.out.printf("%s, %d\n", columns.get(p).name(), p);
		}
		
		System.out.println("===========================================");
	}
	
	public String query()
	{
		String str = "SELECT * FROM " + name;
		return str;
	}
	
	public String labeledQuery()
	{
		String str = "SELECT ";
		
		int i = 0;
		for (DatabaseColumn c : columns)
		{
			str += c.name() + " AS '" + c.alias() + "'";
			
			if (i < columnCount - 1)
				str += ", ";
			
			i++;
		}
		
		str += " FROM " + name;
		
		return str;
	}
	
	public String where()
	{
		String str = "WHERE ";
		ListIterator keyIter = keys.listIterator();
					
		for (int i = 0; i < keys.size(); ++i)
		{
			str += columns.get((Integer)keyIter.next()).name() + " = ?";

			if (i < keys.size()-1)
				str += " AND ";
		}
		
		return str;
	}
	
	public LinkedList<Integer> keys()
	{
		return keys;
	}
	
	public Object editFieldValue(int i) throws DateTimeParseException, NumberFormatException
	{
		Object obj = editFields.get(i);
		String str = null;
		
		if (obj instanceof JComboBox)
		{
			str = ((JComboBox<String>)obj).getSelectedItem().toString();
			
			if (str.equals("-"))
				str = null;
		}
		else if (obj instanceof JTextComponent)
		{
			str = ((JTextComponent)obj).getText();
			
			if (str.equals(""))
				str = null;
		}
		else if (obj instanceof DatetimeFields)
		{
			str = ((DatetimeFields)obj).toDatetimeString();
		}
		
		if (str == null)
			return null;
		
		if ((columnType(i) == Types.INTEGER)||(columnType(i) == Types.TINYINT))
		{
			return Integer.valueOf(str);
		}
		else if ((columnType(i) == Types.REAL)||(columnType(i) == Types.FLOAT))
		{
			return Float.valueOf(str);
		}
		else if ((columnType(i) == Types.CHAR)||(columnType(i) == Types.VARCHAR)||(columnType(i) == Types.LONGVARCHAR))
		{
			return str;
		}
		else if (columnType(i) == Types.TIMESTAMP)
		{
			return LocalDateTime.parse(str);
		}
		
		return null;
	}
	
	public Object keyValue(int i)
	{
		//Object obj = editFields.get(i);
		Object obj = editFields.get(keys.get(i));
		
		String str = mainTable.getValueAt(mainTable.getSelectedRow(), i).toString();
		
		if ((columnType(i) == Types.INTEGER)||(columnType(i) == Types.TINYINT))
		{
			return Integer.valueOf(str);
		}
		else if ((columnType(i) == Types.REAL)||(columnType(i) == Types.FLOAT))
		{
			return Float.valueOf(str);
		}
		else if ((columnType(i) == Types.CHAR)||(columnType(i) == Types.VARCHAR)||(columnType(i) == Types.LONGVARCHAR))
		{
			return str;
		}
		else if (columnType(i) == Types.TIMESTAMP)
		{
			return LocalDateTime.parse(str);
		}
		
		return null;
	}
}