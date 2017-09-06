/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calbul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Pan-Kawia
 */

//klasa, która generuje plik HTML
public class HtmlBuilder {
    public static String getHeader(String month, String year) throws IOException{
        String header = "";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/calendarHeader.html"), "UTF8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                header += line;
            }
        }
        
        header = header.replace("{{{MonthYear}}}", month+" "+year );
        header = header.replace("{{{Latitude}}}", Calbul.getLatitude() );
        header = header.replace("{{{Longitude}}}", Calbul.getLongitude() );
        header = header.replace("{{{TimeZone}}}",  Calbul.getTimezone() );
        return header;
    }
    
    public static String getFooter() throws IOException{
        
         String footer = "";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/footer.html"), "UTF8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                footer += line;
            }
        }

        return footer;
    }
    
    public static String getWeekNumer(String numer) throws IOException{
        
        String weekNumber = "";
        
         try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/weekNumber.html"), "UTF8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                weekNumber += line;
            }
        }
         
        weekNumber = weekNumber.replace("{{{WeekNumber}}}", numer ); 
        return weekNumber;
    }
    
    
    public static String getMoonPhaseImg(Day dayT, String t){
        String name = "";
        
        if( dayT.getMoonT() < 3.125){
            name = "1";
        }
        
        if( 3.125 <= dayT.getMoonT() && dayT.getMoonT() < 9){
            name = "2";
        }

        if( 9 <= dayT.getMoonT() && dayT.getMoonT() < 18){
            name = "3";
        } 

        if( 18 <= dayT.getMoonT() && dayT.getMoonT() < 23){
            name = "4";
        } 
        
        if( 23 <= dayT.getMoonT() && dayT.getMoonT() < 27){
            name = "5";
        }

        if( 27 <= dayT.getMoonT() && dayT.getMoonT() < 33){
            name = "6";
        }

        if( 33 <= dayT.getMoonT() && dayT.getMoonT() < 39){
            name = "7";
        } 

        if( 39 <= dayT.getMoonT() && dayT.getMoonT() < 48){
            name = "8";
        } 

        if( 48 <= dayT.getMoonT() && dayT.getMoonT() < 52){
            name = "9";
        }
         
        if( 52 <= dayT.getMoonT() && dayT.getMoonT() < 58){
            name = "10";
        }

        if( 58 <= dayT.getMoonT() && dayT.getMoonT() < 65){
            name = "11";
        } 

        if( 65 <= dayT.getMoonT() && dayT.getMoonT() < 73){
            name = "12";
        } 
                
        if( 73 <= dayT.getMoonT() && dayT.getMoonT() < 77){
            name = "13";
        }
        
        if( 77 <= dayT.getMoonT() && dayT.getMoonT() < 83){
            name = "14";
        }

        if( 83 <= dayT.getMoonT() && dayT.getMoonT() < 89){
            name = "15";
        } 

        if( 89 <= dayT.getMoonT() && dayT.getMoonT() < 97){
            name = "16";
        } 

        if( 97 <= dayT.getMoonT() ){
            name = "1";
        }
        
        return "fazy/"  + t + name + ".jpg";
    }
    
    public static Double previous;

    public static String generateDayCell(Day dayT) throws IOException{
        String cellDay = (Integer.parseInt(dayT.getDay())) + "";
        
        if(previous == null)
            previous = Day.moon(1,1,Integer.parseInt(dayT.getYear()));
        
        int base = -1;
        double maskedMoon = dayT.getMoonT();
        
        String cellMoon = (int)(99-Math.abs(( 99 - ((2 * maskedMoon%200)) ))) + "%";
        String cellSunUp = dayT.getSunRise();
        String cellSunNoon = dayT.getSunNoon();
        String cellSunDown = dayT.getSunSet();
        String cellNameDay = dayT.getImieniny();
        String cellData = "";
        String dayCell = "";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/dayCell.html"), "UTF8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                dayCell += line;
            }
        }
        
        dayCell = dayCell.replace("{{{cellDay}}}", cellDay);
        dayCell = dayCell.replace("{{{cellNameDay}}}", cellNameDay);
        dayCell = dayCell.replace("{{{getMoonPhaseImg}}}", getMoonPhaseImg(dayT,""));
        dayCell = dayCell.replace("{{{cellMoon}}}", cellMoon);
        dayCell = dayCell.replace("{{{cellSunUp}}}", cellSunUp);
        dayCell = dayCell.replace("{{{cellSunNoon}}}", cellSunNoon);
        dayCell = dayCell.replace("{{{cellSunDown}}}", cellSunDown);
        
        return dayCell;
    } 

    public static String generateBlankDayCell(Day dayT, String g) throws IOException {
        String cellDay = (Integer.parseInt(dayT.getDay())) + "";
        
        if(previous == null)
            previous = Day.moon(1,1,Integer.parseInt(dayT.getYear()));
        
        int base = -1;
        double maskedMoon = dayT.getMoonT();
        String cellMoon = (int)(99-Math.abs(( 99 - ((2 * maskedMoon%200)) ))) + "%";
        
        String cellSunUp = dayT.getSunRise();
        String cellSunNoon = dayT.getSunNoon();
        String cellSunDown = dayT.getSunSet();
        String cellNameDay = dayT.getImieniny();
        String cellData = "";
        String dayCell = "";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/dayBlankCell.html"), "UTF8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                dayCell += line;
            }
        }
        
        dayCell = dayCell.replace("{{{cellDay}}}", cellDay + g);
        dayCell = dayCell.replace("{{{cellNameDay}}}", cellNameDay);
        dayCell = dayCell.replace("{{{getMoonPhaseImg}}}", getMoonPhaseImg(dayT,"b"));
        dayCell = dayCell.replace("{{{cellMoon}}}", cellMoon);
        dayCell = dayCell.replace("{{{cellSunUp}}}", cellSunUp);
        dayCell = dayCell.replace("{{{cellSunNoon}}}", cellSunNoon);
        dayCell = dayCell.replace("{{{cellSunDown}}}", cellSunDown);
        
        return dayCell;
    } 
}


