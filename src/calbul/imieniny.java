/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calbul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pan-Kawia
 */

//ta klasa zajmuje się obsługą imienin
public class imieniny {
    private static ArrayList<birthday> listOfBirthdays = new ArrayList<>();

    private class birthday{
        private String day;
        private String month;
        private String birthday;
        
        public String getDay() {
            return day;
        }

        public String getMonth() {
            return month;
        }

        public String getBirthday() {
            return birthday;
        }
     
        public birthday(String day, String month, String birthday) {
            this.day = day;
            this.month = month;
            this.birthday = birthday;
        }  
    }
          
    public imieniny(){
        try {
            fillBirthDay();
        } catch (IOException ex) {
            Logger.getLogger(imieniny.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         
    public static String getBirthDay(String dayT, String monthT){
        for(birthday d : listOfBirthdays){
            if(d.getDay().equals(dayT) && d.getMonth().equals(monthT)){
                return d.getBirthday();
            }
        }
        return "";
    }
        
    private void fillBirthDay() throws IOException{
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("imieniny.txt"), "UTF8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(";");
                System.out.println(elements[0]);
                listOfBirthdays.add(new birthday((String)elements[0],(String)elements[1],(String)elements[2]));
            }
        }
    }
}
