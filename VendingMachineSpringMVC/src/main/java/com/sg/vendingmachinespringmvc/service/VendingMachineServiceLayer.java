/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VMPersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public interface VendingMachineServiceLayer {
    public List<Item> getAllMenu() throws VMPersistenceException;

    public Item getSelectedVM(String uChoiceItem) throws VMPersistenceException,ItemNotFoundException;

    public int vendItem(Item VM, BigDecimal uChoiceAmount)throws VMPersistenceException,InsufficientFundsException ,NoItemInventoryException;
    
}
