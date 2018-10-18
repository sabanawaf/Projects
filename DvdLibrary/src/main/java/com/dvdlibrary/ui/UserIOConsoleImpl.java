/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author sabaaslam
 */
public class UserIOConsoleImpl implements UserIO {
    
    
    
     Scanner reader = new Scanner( System.in );
    
    
    public void print(String message) {
        System.out.println(message);
        
    }

  
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;
        System.out.println( prompt );
        
        //we want to loop until the input is valid
        //(aka we want to loop while the input is not valid)
        boolean validInput = false;
        while( !validInput )
        {
            try
            {
                //retrieve a line from the user
                String toCheck = reader.nextLine();
                toReturn = Double.parseDouble( toCheck );
                
                //if we get to this line, that means
                //that parseDouble() didn't throw an exception
                validInput = true;
            }
            catch(NumberFormatException e)
            {
                //if we hit this catch block, that means
                //that the user failed to enter something
                //that could be parsed as a double
                System.out.println("Please enter a valid number.");
            }
                
        }

        
        return toReturn;
    }

  
    public double readDouble(String prompt, double min, double max) {
        
        double toReturn = Double.NaN;
        
        boolean isValid = false;
        while( !isValid )
        {
            toReturn = readDouble( prompt );
            
            if( toReturn < min || toReturn > max)
            {
                System.out.println( "Please enter a number between " + min + " and " + max + ".");
            }
            else
            {
                isValid = true;
            }
        }
        
        return toReturn;
       
    }

   
    public float readFloat(String prompt) {
        
        float toReturn=Float.NaN;
        System.out.println(prompt);
        
        //we want to loop until the input is valid
        boolean validInput=false;
        while(!validInput)
        {
            try
            {
                String toCheck=reader.nextLine();
                toReturn=Float.parseFloat(toCheck);
                validInput=true;
            }
            catch(NumberFormatException e)
            {
            
                System.out.println("Please enter a valid number");
            
            }
        }
        
        return toReturn;
        
    }

   
    public float readFloat(String prompt, float min, float max) {
        
        float toReturn=Float.NaN;
        boolean isValid=false;
        while(!isValid)
        {   
            toReturn=readFloat(prompt);
            if(toReturn<min || toReturn>max)
            {
                System.out.println("Please enter a number between"+min
     +"and"+max+".");       
            }
            
            else
            {
                isValid=true;
            
            }
        }
        
        return toReturn;
    }

    
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        System.out.println(prompt);
        
        boolean validInput=false;
        while(!validInput)
        {
            try
            {
                String toCheck=reader.nextLine();
                toReturn=Integer.parseInt(toCheck);
                
                validInput=true;
            }
            
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid number");
            }    
        }    
        
        return toReturn;
    }

    
    public int readInt(String prompt, int min, int max) {
        int toReturn=Integer.MIN_VALUE;
        boolean isValid=false;
        while(!isValid)
        {
            toReturn=readInt(prompt);
            
            if(toReturn<min || toReturn>max)
            {
                System.out.println("Please enter a number between"+min+"and"+max+".");
            
            }
            else
            {
            
            isValid=true;
            }
        
        
        
        }    
        return toReturn;
    }

    
    public long readLong(String prompt) {
        Long toReturn=Long.MIN_VALUE;
        System.out.println(prompt);
        
        boolean validInput=false;
        while(!validInput)
        {
            try
            {
                String toCheck=reader.nextLine();
                toReturn=Long.parseLong(toCheck);
                validInput=true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid number");
            
            }
        }    
        return toReturn;
    }

    
    public long readLong(String prompt, long min, long max) {
        Long toReturn=Long.MIN_VALUE;
        boolean isValid=false;
        while(!isValid)
        {
            toReturn=readLong(prompt);
            
            if(toReturn<min||toReturn>max)
                    {
                        
                        System.out.println("Please enter a number between"+min+"and"+max+".");
                        
                    }
            else
            {
                isValid=true;
            }
        }    
            
      return toReturn;  
    }

    
    public String readString(String prompt) {
        
        System.out.println(prompt);
        String toReturn=reader.nextLine();
        return toReturn;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
       
       LocalDate toReturn=null;
      
        
        boolean validInput=false;
        while(!validInput)
              
        {
            System.out.println(prompt);
            try
            {
                String toCheck=reader.nextLine();
                
                toReturn = LocalDate.parse(toCheck, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                
                validInput=true;
            }
            catch(DateTimeParseException  e)
            {
                System.out.println("Please enter a valid date");
            
            }
        }    
        return toReturn;
    
}
}
