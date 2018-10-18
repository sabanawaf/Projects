/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VMPersistenceException;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.ItemNotFoundException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sabaaslam
 */
@Controller
public class VendingController {

    VendingMachineServiceLayer sl;

    @Inject
    public VendingController(VendingMachineServiceLayer sl) {
        this.sl = sl;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayVendingMachinePage(Model model) throws VMPersistenceException {
        // Get all the Items from the DAO
        List<Item> ItemList = sl.getAllMenu();

        // Put the List of Items on the Model
        model.addAttribute("ItemList", ItemList);

        // Return the logical name of our View component
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request, Model model) {
        String itemName = request.getParameter("itemName");
        String moneyEntered = request.getParameter("money-entered");
        
if ((itemName != null) && (!itemName.isEmpty()) && (moneyEntered != null) && (!moneyEntered.isEmpty())){
            try {

                Item item = sl.getSelectedVM(itemName);

                // String moneyEntered = request.getParameter("money-entered");
                BigDecimal mEntered = new BigDecimal(moneyEntered);

                int changeInPennies = sl.vendItem(item, mEntered);

                Change c = new Change();
                c.change(changeInPennies);
                String changeMessage = "Dollars:" + c.getDollars() + " Quarters:" + c.getQuarters() + " Dimes:" + c.getDimes() + " Nickels " + c.getNickels() + " Pennies:" + c.getPennies();
                model.addAttribute("changeOutput", changeMessage);

                model.addAttribute("error", "Thankyou");

            } catch (InsufficientFundsException e) {
                String error = "Insufficient Funds";
                model.addAttribute("error", error);
                //display message to user
            } catch (VMPersistenceException e) {
                String error = "File not Found";
                model.addAttribute("error", error);

            } catch (NoItemInventoryException e) {
                String error = "SOLD OUT";
                model.addAttribute("error", error);
            } catch (ItemNotFoundException e) {
                String error = "Item not Found";
                model.addAttribute("error", error);

            }
            try {
                List<Item> ItemList = sl.getAllMenu();

                // Put the List of Contacts on the Model
                model.addAttribute("ItemList", ItemList);
            } catch (VMPersistenceException e) {
                String error = "File not Found";
                model.addAttribute("error", error);
            }
            return "index";
        } else {
            String error = "Please select the item and enter money";
            model.addAttribute("error", error);
            try {
                List<Item> ItemList = sl.getAllMenu();

                // Put the List of Contacts on the Model
                model.addAttribute("ItemList", ItemList);
            }catch (VMPersistenceException e) {
                String error1 = "File not Found";
                model.addAttribute("error", error1);
            }
            return "index";
        }
    }

}
