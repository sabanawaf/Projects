/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.ItemNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sabaaslam
 */
@Repository
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> VM = new HashMap<>();
    File f = new File(getClass().getClassLoader().getResource("item.txt").getFile());

   // public static final String VM_FILE = "f.txt";
    public static final String DELIMITER = "::";

    private void loadVM() throws VMPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(f)));
        } catch (FileNotFoundException e) {
            throw new VMPersistenceException(
                    "-_- Could not load Vending Machine data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentVM = new Item();
            // Set the remaining vlaues on currentDvd manually

            currentVM.setItemName(currentTokens[0]);
            String test = currentTokens[1];
            BigDecimal costofItem = new BigDecimal(test);
            currentVM.setCost(costofItem);
            int i = Integer.parseInt(currentTokens[2]);
            currentVM.setInventory(i);

            // Put currentVM into the map using item name as the key
            VM.put(currentVM.getItemName(), currentVM);
        }
        // close scanner
        scanner.close();
    }
    
    
        private void writeVM() throws VMPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(f));
        } catch (IOException e) {
            throw new VMPersistenceException(
                    "Could not save VM data.", e);
        }

        List<Item> vmList = this.getAllMenu();
        for (Item currentVM : vmList) {
            out.println(currentVM.getItemName() + DELIMITER
                    + currentVM.getCost() + DELIMITER
                    + currentVM.getInventory()
            );
            out.flush();
        }

        out.close();
    }

    @Override
    public List<Item> getAllMenu() throws VMPersistenceException {
         loadVM();
        Collection<Item> c = VM.values();
        return new ArrayList(c);
        
    }

    @Override
    public Item getSelectedVM(String uChoiceItem) throws VMPersistenceException,ItemNotFoundException                                           {
         loadVM();
        return VM.get(uChoiceItem);
    }

    @Override
    public void updateInventory(Item selectedVM) throws VMPersistenceException {
        VM.put(selectedVM.getItemName(), selectedVM);
        writeVM();
    }

}
