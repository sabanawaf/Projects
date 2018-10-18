
import com.sg.vendingmachinespringmvc.dao.VMPersistenceException;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.ItemNotFoundException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayerImpl;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sabaaslam
 */
public class VendingMachineServiceLayerTest {
    
     private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
      //  VMAuditDao auditDao = new VMAuditDaoStubImpl();
       // service = new VMServiceLayerImpl(dao, auditDao);
       service=new VendingMachineServiceLayerImpl(dao);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllMenu method, of class VMServiceLayer.
     */
    @Test
    public void testGetAllMenu() throws VMPersistenceException {
        assertEquals(1, service.getAllMenu().size());
    }

    /**
     * Test of getSelectedVM method, of class VMServiceLayer.
     */
    @Test
    public void testGetSelectedVM() throws VMPersistenceException {

        try {
            Item vm = service.getSelectedVM("water");
        } catch (ItemNotFoundException e) {
            fail("Hit unexpected Exceptin");
        }
        
        
        try {
           Item vm = service.getSelectedVM("wwwwww");
            fail("Expected exception not thrown");
        } catch (ItemNotFoundException e) {
            return;
        }

    }

    /**
     * Test of calculateChange method, of class VMServiceLayer.
     */
    /**
     * Test of convertIntoPennies method, of class VMServiceLayer.
     */
    @Test
    public void testConvertIntoPennies() throws Exception {

    }

    @Test
    public void vendItem() throws VMPersistenceException {
        Item item = new Item();
        item.setCost(new BigDecimal(4));
        item.setInventory(0);
        try {
            service.vendItem(item, new BigDecimal(5));
            fail("NoItem Inventory exception not thrown");
        } catch (NoItemInventoryException e) {

        } catch (InsufficientFundsException e) {
            fail(item.getCost() + "Is the amount thats required ");

        }
        item.setInventory(3);

        //cheking if the change came back as we expected
        item.setItemName("snickers");
        item.setCost(new BigDecimal(2));

        int changeInPennies = -1;
        try {
            changeInPennies = service.vendItem(item, new BigDecimal(3));

        } catch (InsufficientFundsException e) {
            fail("Hit IndufficientFunds Incorrectly");
        } catch (NoItemInventoryException e) {
            fail("Hit NoIteminvetory exception Incorrectly");
        }

        assertEquals(100, changeInPennies);

        try {
            service.vendItem(item, new BigDecimal(0));
            fail("InsufficientFundsException not thrown");
        } catch (NoItemInventoryException e) {
            fail("Hit NoItemInventoryException incorrectly");

        } catch (InsufficientFundsException e) {

        }

    }

//    @Test
//    public void testConvertIntoPenniesInvalidUserInput() throws Exception {
//        //uChoiceAmount.multiply(new BigDecimal(100)).intValue();
////        VendingMachine vm= new VendingMachine();
////        vm.setItemName("choclate");
////        vm.setCost(new BigDecimal(4));
////        vm.setInventory(3);
//
//        int pennies = service.convertIntoPennies(new BigDecimal(3));
//        assertEquals(300, pennies);
//
//        try {
//            pennies = service.convertIntoPennies(null);
//            fail("Exception isnt thrown");
//        } catch (IllegalArgumentException e) {
//            return;
//        }
//
//    }
    /**
     * Test of checkFund method, of class VMServiceLayer.
     */
    @Test
    public void testCheckFund() throws Exception {

    }

    
}
