/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sabaaslam
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    //File f = new File(getClass().getClassLoader().getResource("item.txt").getFile());

    private Map<String, Item> VM = new HashMap<>();

    public VendingMachineDaoInMemImpl() {
  
        Item item1 = new Item();

        item1.setItemName("snickers");
        item1.setCost(new BigDecimal(4));
        item1.setInventory(14);
        VM.put("snickers", item1);

        Item item2 = new Item();

        item2.setItemName("lemonade");
        item2.setCost(new BigDecimal(2));
        item2.setInventory(24);
        VM.put("lemonade", item2);

        Item item3 = new Item();

        item3.setItemName("water");
        item3.setCost(new BigDecimal(1));
        item3.setInventory(11);
        VM.put("water", item3);

//         VM.put(snickers,4,14);
//         VM.put(lemonade,2,24);
//         VM.put(water,1,11);
    }

    @Override
    public List<Item> getAllMenu() throws VMPersistenceException {
        Collection<Item> c = VM.values();
        return new ArrayList(c);
    }

    @Override
    public Item getSelectedVM(String uChoiceItem) throws VMPersistenceException {
        return VM.get(uChoiceItem);
    }

    @Override
    public void updateInventory(Item selectedVM) throws VMPersistenceException {
        VM.put(selectedVM.getItemName(), selectedVM);

    }

}
