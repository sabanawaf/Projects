/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author sabaaslam
 */
public class Change {
     int dollars;
    int quarters;
    int dimes;
    int nickels;
    int pennies;

    public int getDollars() {
        return dollars;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
    
        
    public void change (int pennies){
        
        dollars =  pennies/100;
        pennies =  pennies%100;
        
         
        
        quarters =  pennies/25;
        pennies =  pennies%25;
        
        
        
        
        
        
        dimes =  pennies/10;
        pennies = dimes % 10;
            
      
        
        nickels = pennies/5;
        pennies = nickels %5;
            
        this.pennies=pennies;
        
    
    }
}
