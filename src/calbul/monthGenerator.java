/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calbul;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author Pan-Kawia
 */

public class monthGenerator {
    private ArrayList<Day> days;
    private ArrayList<Day> weekBefore;
    private ArrayList<Day> weekAfter;
    private Day dzien;
    private static String longitude;
    private static String latitude;
    private static String timezone;
    
    public monthGenerator(){
        days = new ArrayList<>();
        weekBefore = new ArrayList<>();
        weekAfter = new ArrayList<>();
    }
    
    public static void setCoordinates(String longitude, String latitude){
        longitude = longitude;
        latitude = latitude;
    }

    public static void setTimezone(String timezone) {
        monthGenerator.timezone = timezone;
    }
    
    public void generatePreviousDays(int yearT, int monthT){
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, yearT);
        cal2.set(Calendar.MONTH, monthT-1);
        int maxDay = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal2.set(Calendar.DAY_OF_MONTH, maxDay);

        SimpleDateFormat yearString = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthString = new SimpleDateFormat("MM");
        SimpleDateFormat dayString = new SimpleDateFormat("dd");
        SimpleDateFormat weekNameString = new SimpleDateFormat("EEE");
        SimpleDateFormat weekNumberString = new SimpleDateFormat("ww");
        SimpleDateFormat weekNameNumberString = new SimpleDateFormat("u");
        
        for (int i = 1; i < 14; i++) {
            String year = yearString.format(cal2.getTime());
            String month = monthString.format(cal2.getTime());
            String day = dayString.format(cal2.getTime());
            String weekName = weekNameString.format(cal2.getTime());
            String weekNumber = weekNumberString.format(cal2.getTime()); 
            String weekNameNumber = weekNameNumberString.format(cal2.getTime());     
           
            Location location = new Location(longitude, latitude);
            SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, TimeZone.getTimeZone(timezone));
           
           
            String officialSunrise = calculator.getOfficialSunriseForDate(cal2);
            String officialSunset = calculator.getOfficialSunsetForDate(cal2);
            cal2.set(Calendar.DAY_OF_MONTH, maxDay - i);

            
            dzien = new Day(year, month, day, weekName, weekNumber, weekNameNumber, officialSunrise, officialSunset, "");
            dzien.setImieniny(imieniny.getBirthDay(month, day)); 
           
            weekBefore.add(dzien);
        }     
    }
    
    public void generateDays(int yearT, int monthT){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearT);
        cal.set(Calendar.MONTH, monthT);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat yearString = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthString = new SimpleDateFormat("MM");
        SimpleDateFormat dayString = new SimpleDateFormat("dd");
        SimpleDateFormat weekNameString = new SimpleDateFormat("EEE");
        SimpleDateFormat weekNumberString = new SimpleDateFormat("ww");
        SimpleDateFormat weekNameNumberString = new SimpleDateFormat("u");

        for (int i = 1; i != maxDay+1; i++) {
            String year = yearString.format(cal.getTime());
            String month = monthString.format(cal.getTime());
            String day = dayString.format(cal.getTime());
            String weekName = weekNameString.format(cal.getTime());
            String weekNumber = weekNumberString.format(cal.getTime()); 
            String weekNameNumber = weekNameNumberString.format(cal.getTime());     

            Location location = new Location(longitude, latitude);
            SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, TimeZone.getTimeZone(timezone));
           
            String officialSunrise = calculator.getOfficialSunriseForDate(cal);
            String officialSunset = calculator.getOfficialSunsetForDate(cal);
            cal.set(Calendar.DAY_OF_MONTH, i + 1);

            dzien = new Day(year, month, day, weekName, weekNumber, weekNameNumber, officialSunrise, officialSunset, "");
            dzien.setImieniny(imieniny.getBirthDay(month, day));
            System.out.println(imieniny.getBirthDay(day, month));
           
            days.add(dzien);
        }
    }

    
    public void generateNextDays(int yearT, int monthT){
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR, yearT);
        cal3.set(Calendar.MONTH, monthT+1);
        int maxDay = cal3.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal3.set(Calendar.DAY_OF_MONTH, 1);
        
        SimpleDateFormat yearString = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthString = new SimpleDateFormat("MM");
        SimpleDateFormat dayString = new SimpleDateFormat("dd");
        SimpleDateFormat weekNameString = new SimpleDateFormat("EEE");
        SimpleDateFormat weekNumberString = new SimpleDateFormat("ww");
        SimpleDateFormat weekNameNumberString = new SimpleDateFormat("u");
        
        for (int i = 1; i < 14; i++) {
            String year = yearString.format(cal3.getTime());
            String month = monthString.format(cal3.getTime());
            String day = dayString.format(cal3.getTime());
            String weekName = weekNameString.format(cal3.getTime());
            String weekNumber = weekNumberString.format(cal3.getTime()); 
            String weekNameNumber = weekNameNumberString.format(cal3.getTime());
            
            Location location = new Location(longitude, latitude);
            SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, TimeZone.getTimeZone(timezone));
           
            System.out.println(day);
            String officialSunrise = calculator.getOfficialSunriseForDate(cal3);
            String officialSunset = calculator.getOfficialSunsetForDate(cal3);
            cal3.set(Calendar.DAY_OF_MONTH, i + 1);

            
            dzien = new Day(year, month, day, weekName, weekNumber, weekNameNumber, officialSunrise, officialSunset, "");
            dzien.setImieniny(imieniny.getBirthDay(month, day)); 
           
           weekAfter.add(dzien);
        }
    }
    
    public ArrayList<Day> getDays() {
        return days;
    }

    public ArrayList<Day> getWeekBefore() {
        return weekBefore;
    }

    public ArrayList<Day> getWeekAfter() {
        return weekAfter;
    }

    public static void setLongitude(String longitude) {
        monthGenerator.longitude = longitude;
    }

    public static void setLatitude(String latitude) {
        monthGenerator.latitude = latitude;
    }
}
