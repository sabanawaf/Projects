/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;

/**
 *
 * @author sabaaslam
 */
public class NoItemInventoryException extends Exception{
         Item item;
    BigDecimal moneyEntered;

    public Item getItem() {
        return item;
    }

    public BigDecimal getMoneyEntered() {
        return moneyEntered;
    }
    public NoItemInventoryException(String message,Item item, BigDecimal moneyEntered){
        
        super(message);
        this.item=item;
        this.moneyEntered=moneyEntered;
    }
    
    
    public NoItemInventoryException(String message, Throwable cause){
        
        super(message,cause);
    }
    
}
