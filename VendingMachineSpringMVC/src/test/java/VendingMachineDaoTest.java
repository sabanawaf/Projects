/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachinespringmvc.model.Item;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sabaaslam
 */
public class VendingMachineDaoTest {

    private VendingMachineDao dao;

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        String item=(getClass().getClassLoader().getResource("item.txt").getFile());
        String seed=(getClass().getClassLoader().getResource("seed.txt").getFile());
       Files.copy(Paths.get(seed), Paths.get(item), StandardCopyOption.REPLACE_EXISTING);
        

        dao = new VendingMachineDaoFileImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllMenu() throws Exception {

        List<Item> items = dao.getAllMenu();
        assertEquals(3, items.size());

        List<Item> finalItem = items.stream()
                .filter(i -> i.getItemName().equalsIgnoreCase("water"))
                .collect(Collectors.toList());

        Item water = finalItem.get(0);

        assertEquals(new BigDecimal(1), water.getCost());

        assertEquals(11, water.getInventory());

    }
    
    
    
    @Test
    public void testGetSelectedVM() throws Exception {
        Item lemonade = dao.getSelectedVM("lemonade");

        assertEquals(new BigDecimal(2), lemonade.getCost());

        assertEquals(24, lemonade.getInventory());

    }

    /**
     * Test of updateInventory method, of class VMDao.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        List<Item> items = dao.getAllMenu();

        List<Item> finalItem = items.stream()
                .filter(i -> i.getItemName().equalsIgnoreCase("water"))
                .collect(Collectors.toList());

        Item water = finalItem.get(0);

        assertEquals(11, water.getInventory());

        water.setInventory(10);

        dao.updateInventory(water);

        List<Item> validationItems = dao.getAllMenu();

        List<Item> validationCheckItem = validationItems.stream()
                .filter(i -> i.getItemName().equalsIgnoreCase("water"))
                .collect(Collectors.toList());

        Item validationWater = validationCheckItem.get(0);

        assertEquals(10, validationWater.getInventory());

    }

}
