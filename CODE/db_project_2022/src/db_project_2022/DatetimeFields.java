package db_project_2022;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class DatetimeFields
{
	public final JComboBox<String> year, month, day, hours, minutes, seconds;
	private final int index;
	
	DatetimeFields(int index, JComboBox<String> year,JComboBox<String> month,JComboBox<String> day,JComboBox<String> hours,JComboBox<String> minutes,JComboBox<String> seconds)
	{
		this.index = index;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public void setDatetime()
	{
		if (index >= 0)
		{
			int row = DatabaseTable.mainTable.getSelectedRow();

			Object obj = DatabaseTable.mainTable.getValueAt(row, index);

			if ((obj instanceof String) && (((String)obj).equals("NULL")))
			{
				resetDatetime();
			}
			else
			{
				year.setSelectedItem(String.valueOf(((LocalDateTime)obj).getYear()));
				month.setSelectedIndex(((LocalDateTime)obj).getMonthValue());
				day.setSelectedIndex(((LocalDateTime)obj).getDayOfMonth());
				hours.setSelectedIndex(((LocalDateTime)obj).getHour() + 1);
				minutes.setSelectedIndex(((LocalDateTime)obj).getMinute()+ 1);
				seconds.setSelectedIndex(((LocalDateTime)obj).getSecond()+ 1);
			}
		}
	}
	
	public void setDatetime(String year, String month, String day, String hours, String minutes, String seconds)
	{
		if (index < 0)
		{
			this.year.setSelectedItem(year);
			this.month.setSelectedItem(month);
			this.day.setSelectedItem(day);
			this.hours.setSelectedItem(hours);
			this.minutes.setSelectedItem(minutes);
			this.seconds.setSelectedItem(seconds);
		}
	}
	
	public void resetDatetime()
	{
		year.setSelectedIndex(0);
		month.setSelectedIndex(0);
		day.setSelectedIndex(0);
		hours.setSelectedIndex(0);
		minutes.setSelectedIndex(0);
		seconds.setSelectedIndex(0);
	}
	
	public int index()
	{
		return index;
	}
	
	public String toDatetimeString()
	{
		String str = "";
		
		str += year.getSelectedItem().toString() + "-" +
				month.getSelectedItem().toString() + "-" +
				day.getSelectedItem().toString() + "T" +
				hours.getSelectedItem().toString() + ":" +
				minutes.getSelectedItem().toString() + ":" +
				seconds.getSelectedItem().toString();
		
		if (str.equals("-----T-:-:-"))
			return null;
		
		return str;
	}
	
	public LocalDateTime toDatetime() throws DateTimeParseException
	{
		String str = toDatetimeString();
		if (str == null)
			return null;
		
		return LocalDateTime.parse(str);
	}
}
