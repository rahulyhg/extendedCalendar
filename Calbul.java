/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calbul;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pan-Kawia
 */

//główna klasa, która skleja cały kalendarz do kupy
public class Calbul {
    private static String latitude;
    private static String longitude;
    private static String timezone;
    private static String timezoneId;

    public static String getTimezoneId() {
        return timezoneId;
    }

    public static void setTimezoneId(String timezoneId) {
        Calbul.timezoneId = timezoneId;
    }

    public static String getLatitude() {
        return latitude;
    }

    public static String getLongitude() {
        return longitude;
    }

    public static String getTimezone() {
        return timezone;
    }
    
    public void genCalendar(int year, int month) throws IOException{
        monthGenerator.setCoordinates( longitude, latitude );
        monthGenerator.setTimezone( timezone );
        monthGenerator genT = new monthGenerator();
        genT.generateDays( year, month );

        genT.generatePreviousDays( year,month );
        genT.generateNextDays( year,month );
        String miesiac = "";
        
        if( month == 0 ) miesiac = "Styczeń";
        if( month == 1 ) miesiac = "Luty";
        if( month == 2 ) miesiac = "Marzec";
        if( month == 3 ) miesiac = "Kwiecień";
        if( month == 4 ) miesiac = "Maj";
        if( month == 5 ) miesiac = "Czerwiec";
        if( month == 6 ) miesiac = "Lipiec";
        if( month == 7 ) miesiac = "Sierpień";
        if( month == 8 ) miesiac = "Wrzesień";
        if( month == 9 ) miesiac = "Październik";
        if( month == 10 ) miesiac = "Listopad";
        if( month == 11 ) miesiac = "Grudzień";
        
        String HTML = "";
        int lpustedni = Integer.parseInt( genT.getDays().get( 1 ).getWeekNameNumber() );
        HTML += HtmlBuilder.getHeader( year + "", miesiac );
        HTML += "<tr>";
        
        for( int j = 1; j < lpustedni - 1; j++ ) {
            HTML += HtmlBuilder.generateBlankDayCell( genT.getWeekBefore().get( lpustedni - j - 2 ), "" );
            //System.out.println( "lpustedni: " + lpustedni + " moth: " + month );
        }    
            
        if( lpustedni == 1 ) {
            for( int j = 0; j < 6; j++ ) {
                HTML += HtmlBuilder.generateBlankDayCell( genT.getWeekBefore().get( 5-j ), "" );
            } 
        }
        
        int i = lpustedni - 2;
        
        if( i == -1 ) {
            i = 0;
        }
        
        String lastWeek = "";
        if(lpustedni != 1){
            for( Day dzien : genT.getDays() ) {
                if ( i >= 7 ) { 
                    HTML += HtmlBuilder.getWeekNumer( (Integer.parseInt( dzien.getWeekNumber() ) - 1 ) + "" );
                    HTML += "</tr><tr>";
                }

                HTML += HtmlBuilder.generateDayCell( dzien );
                i++;
                
                if (i > 7) {
                    i=1;
                }       
                lastWeek = Integer.parseInt( dzien.getWeekNumber() ) + "";
            }
        } else {
            boolean c = true;
            boolean x = true;
            for( Day dzien : genT.getDays() ) {
                if( c == true ) {
                    HTML += HtmlBuilder.generateDayCell( dzien );
                    c = false;
                    HTML += HtmlBuilder.getWeekNumer( ( Integer.parseInt( dzien.getWeekNumber() ) - 1 ) + "" );
                    HTML +="</tr><tr>";
                }
                
                if( x != true ) {
                    if ( i >= 7 ) { 
                        HTML += HtmlBuilder.getWeekNumer ( (Integer.parseInt( dzien.getWeekNumber() ) - 1 ) + "");
                        HTML += "</tr><tr>";
                    }

                    HTML += HtmlBuilder.generateDayCell( dzien );
                    i++;
                    
                    if( i > 7 ) {
                        i=1;
                    }   
                    
                    lastWeek = ( Integer.parseInt( dzien.getWeekNumber() ) ) + "";
                }
            x = false;
            }
        }
        
        int a = 0;
        while( i < 7 ) {
            HTML += HtmlBuilder.generateBlankDayCell( genT.getWeekAfter().get( a ), "" );   
            a++;
            i++;
        }
        
        HTML += HtmlBuilder.getWeekNumer( lastWeek );
        HTML += "</tr>";
        HTML += HtmlBuilder.getFooter();
        
        try {   
            BufferedWriter bufWrite = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(month + ".html" ), "UTF8" ) );
            bufWrite.append( HTML );   
            bufWrite.flush();
        } catch ( IOException e ) {
        }   
    }
     
    public void genFullCalendar( int year ) throws IOException {
        genCalendar( year, 0 );
        genCalendar( year, 1 );
        genCalendar( year, 2 );
        genCalendar( year, 3 );
        genCalendar( year, 4 );
        genCalendar( year, 5 );
        genCalendar( year, 6 );
        genCalendar( year, 7 );
        genCalendar( year, 8 );
        genCalendar( year, 9 );
        genCalendar( year, 10 );
        genCalendar( year, 11 );
        genCalendar( year, 12 );
    }

    public Calbul( String year ) throws IOException{
        new imieniny();
        genFullCalendar( Integer.parseInt( year ));
        
        try {
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec(
                    "wkhtmltopdf -s A4 -O landscape --dpi 300 --zoom 0.96 0.html"
                    + " 1.html 2.html 3.html 4.html 5.html 6.html 7.html"
                    + " 8.html 9.html 10.html 11.html kalendarz.pdf");
        } catch( IOException e ){
            e.printStackTrace();
        } 
    }

    public static void setLatitude( String latitude ) {
        Calbul.latitude = latitude;
        monthGenerator.setLatitude( latitude );
    }

    public static void setLongitude( String longitude ) {
        Calbul.longitude = longitude;
        monthGenerator.setLongitude( longitude );
    }

    public static void setTimezone( String timezone ) {
        Calbul.timezone = timezone;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setVisible( true );
                frame.setTitle( "Generator kalendarza" );
                frame.setSize( 500, 280 );
    
                JPanel panel = new calendarInterface();
                frame.add( panel );
            }
        });
    }
}
