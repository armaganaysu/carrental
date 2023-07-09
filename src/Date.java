

public class Date
{

    private int day, month, year;


    
    public Date()
    {
    	day=1;
    	month=1;
    	year=2021;

    }

    public Date(String formatted_date)
    {
        String [] splitted_date = formatted_date.split("\\.");
        this.day = Integer.parseInt(splitted_date[0]);
        this.month = Integer.parseInt(splitted_date[1]);
        this.year = Integer.parseInt(splitted_date[2]);
    }

    public Date (int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public int getDay()
    {
        return day;
    }
    public void setDay(int day)
    {
        this.day = day;
        calibrateDate(day,month,year);


    }


    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
        calibrateDate(day,month,year);

    }

    public int getYear()
    {
        return year;
    }
    public void setYear(int year)
    {
        this.year = year;
        calibrateDate(day,month,year);
    }
    
    private void calibrateDate(int d,int m,int y)
    {
    	boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    	if	((this.month==1) || (this.month==3) || (this.month==5) || (this.month==7)|| (this.month==8)|| (this.month==10)|| (this.month==12))
    	{
    		if(this.day>31)
    		{
    			d=1;
    	        m++;
    		}

    	}
    	else if	((this.month==4) || (this.month==6) || (this.month==9) || (this.month==11))
    	{
    		if(this.day>30)
    		{
    			d=1;
    	        m++;
    		}
    	        
    	}
    	else if(isLeapYear && this.month==2)
    	{
    		if(this.day>29)
    		{
    			d=1;
    	        m++;
    		}
    	        
    	}
    	else if((!isLeapYear) && this.month==2)
    	{
    		if(this.day>28)
    		{
    			d=1;
    	        m++;
    		}	        
    	}
    	this.day=d;
    	this.month=m;
    	
    	
    	if (this.month>12)
    	{
    		m=1;
    	    y++;   
    	}
    	this.month = m;
    	this.year=y;
    }



    public void nextDay(){
        setDay(getDay() +1);
    }



    public  String displayDate()
    {
        return String.format("%d.%d.%d",day, month, year);

    }






	
}