/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.ItemNotFoundException;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public interface VendingMachineDao {

    List<Item> getAllMenu() throws VMPersistenceException;

    Item getSelectedVM(String uChoiceItem) throws VMPersistenceException,ItemNotFoundException;

    void updateInventory(Item selectedVM) throws VMPersistenceException;

}
