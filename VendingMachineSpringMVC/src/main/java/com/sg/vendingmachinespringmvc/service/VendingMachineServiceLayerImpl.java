/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VMPersistenceException;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author sabaaslam
 */@Service
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    // VMAuditDao auditDao;
     @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;

    }

    @Override
    public List<Item> getAllMenu() throws VMPersistenceException {
        return dao.getAllMenu();
    }

    @Override
    public Item getSelectedVM(String uChoiceItem) throws VMPersistenceException ,ItemNotFoundException{
        return dao.getSelectedVM(uChoiceItem);
    }

    @Override
    public int vendItem(Item VM, BigDecimal uChoiceAmount) throws VMPersistenceException,InsufficientFundsException ,NoItemInventoryException{
        int priceCheck = uChoiceAmount.compareTo(VM.getCost());

        if (priceCheck < 0) {

            throw new InsufficientFundsException("ERROR insufficient funds", VM, uChoiceAmount);

        }

        int currentInventory = VM.getInventory();
        if (currentInventory <= 0) {

            throw new NoItemInventoryException("ERROR item not found", VM, uChoiceAmount);
        }

        BigDecimal b = uChoiceAmount.subtract(VM.getCost());

        currentInventory = currentInventory - 1;
        VM.setInventory(currentInventory);
        dao.updateInventory(VM);

        int changeInPennies = b.multiply(new BigDecimal(100)).intValue();
        return changeInPennies;

    }


}
