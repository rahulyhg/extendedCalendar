/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calbul;

/**
 *
 * @author Pan-Kawia
 */

//klasa zawiera informacje dotyczące dnia np. fazę księżyca, godzinę górowania słońca w danym dniu dla danych współrzędnych geograficznych,
//nazwę dnia tygodnia etc.
public class Day {
    private String year;
    private String month;
    private String day;
    private String weekName;
    private String weekNumber;
    private String weekNameNumber;

    private String imieniny;
    private double moonT;
    private String sunSet;
    private String sunRise;
    private String sunNoon;

    private static int iter = 1;
    private static int base = 0;
       
    public Day(String year, String month, String day, String weekName, String weekNumber, String weekNameNumber) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weekName = weekName;
        this.weekNumber = weekNumber;
        this.weekNameNumber = weekNameNumber;
        this.moonT = moon(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
    }

    public Day(String year, String month, String day, String weekName, String weekNumber, String weekNameNumber, String sunRise, String sunSet, String sunNoon) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weekName = weekName;
        this.weekNumber = weekNumber;
        this.weekNameNumber = weekNameNumber;
        this.moonT = moon(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
       
        String[] sunriseT = sunRise.split(":");
        String[] sunsetT = sunSet.split(":");
        int hoursSunRise = Integer.parseInt(sunriseT[0]);
        int hoursSunSet = Integer.parseInt(sunsetT[0]);  
        int minutesSunRise = hoursSunRise * 60 + Integer.parseInt(sunriseT[1]); 
        int minutesSunSet = hoursSunSet * 60 + Integer.parseInt(sunsetT[1]); 
        int minutesSunNoon = minutesSunRise + (minutesSunSet - minutesSunRise)/2;
        this.sunNoon = (minutesSunNoon / 60) +":"+ (minutesSunNoon % 60);
        
        this.sunRise = sunRise;
        this.sunSet = sunSet;
    }
    
    public String getYear() {
        return year;
    }

    public String getImieniny() {
        return imieniny;
    }

    public void setImieniny(String imieniny) {
        this.imieniny = imieniny;
    }

    public String getSunSet() {
        return sunSet;
    }

    public String getSunRise() {
        return sunRise;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getWeekName() {
        return weekName;
    }

    public String getWeekNumber() {
        return weekNumber;
    }
       
    public String printDayData(){
        return getYear() + "-" + getMonth() + "-" + getDay() + " " + getWeekName() + " " + getWeekNumber() + " numer: " + getWeekNameNumber();
    }   

    public String getWeekNameNumber() {
        return weekNameNumber;
    }

    public double getMoonT() {
        return moonT;
    }

    public String getSunNoon() {
        return sunNoon;
    }
     
    public static double moon(int dzien,int miesiac,int rok){
        double JD,MS=29.5305902778,Faza;
        double x,a,y,v,b,q,c,w,e;

        x=(miesiac+9)/12;  
        a=4716+rok+(int)x;
        y=275*miesiac/9;
        v=7*a/4;
        b=1729279.5+367*rok+(int)y-(int)v+dzien;
        q=(a+83)/100;
        c=(int)q;
        w=3*(c+1)/4;
        e=(int)w;
        JD=b+38-e;
        
        Faza=(JD/MS)-0.3033;
        Faza=Faza-(int)Faza;
        Faza*=100;
        Faza=Math.round(Faza);
        Faza/=100;

        double qwe = (double)Math.round(Math.random()*10)/20;
        double result = Math.round((Faza)*100);
           
        if(result >= 99.0){
            result = 99.0;
        }
           
        return result;
    }       
}
