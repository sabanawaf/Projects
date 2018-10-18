/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.ItemNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{

  Item onlyItem;
   // private List<VendingMachine> VMList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl(){
        onlyItem=new Item();
        onlyItem.setItemName("water");
        onlyItem.setCost(new BigDecimal(4.5));
        onlyItem.setInventory(10);
       
        
    }

    @Override
    public List<Item> getAllMenu() throws VMPersistenceException {
        
        return Arrays.asList(onlyItem);
    }

    @Override
    public Item getSelectedVM(String uChoiceItem) throws VMPersistenceException, ItemNotFoundException {
        if(uChoiceItem.equals(onlyItem.getItemName())){
            return onlyItem;
            
        }else{
            throw new ItemNotFoundException(uChoiceItem+"not found");
        }
                
                
                
                
    }

    @Override
    public void updateInventory(Item selectedVM) throws VMPersistenceException {
        onlyItem.setItemName(selectedVM.getItemName());
        onlyItem.setInventory(selectedVM.getInventory());
        onlyItem.setCost(selectedVM.getCost());
    }


    
    
}
