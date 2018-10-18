/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sabaaslam
 */
public class ClassDvdDaoTest {
    
    private ClassDvdDao dao= null;
    
    public ClassDvdDaoTest() throws ClassDvdDaoException{
       dao =new ClassDvdDaoFileImpl();
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Dvd> dvdList=dao.getAllDvds();
        for(Dvd dvd :dvdList){
            dao.removeDvd(dvd.getTitle());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class ClassDvdDao.
     */
    @Test
    public void testAddGetDvd() throws Exception {
        Dvd dvd=new Dvd("frozen");
        dvd.setStudio("disney");
        dvd.setDirectorName("Jhon");
        dvd.setRating("4");
        dvd.setUserRating("Family");
        dvd.setReleaseDate(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addDvd(dvd.getTitle(), dvd);
        
        Dvd fromDao=dao.getDvd(dvd.getTitle());
        
        assertEquals(dvd,fromDao);
    }

    /**
     * Test of getAllDvds method, of class ClassDvdDao.
     */
    @Test
    public void testGetAllDvds() throws Exception {
        Dvd dvd1=new Dvd("Frozen");
        dvd1.setDirectorName("joe");
        dvd1.setRating("5");
        dvd1.setReleaseDate(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd1.setStudio("Walt disney");
        dvd1.setUserRating("Family");
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        
        
        
         Dvd dvd2=new Dvd("Superman");
        dvd2.setDirectorName("joe");
        dvd2.setRating("5");
        dvd2.setReleaseDate(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd2.setStudio("Walt disney");
        dvd2.setUserRating("Friends");
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        
        assertEquals(2,dao.getAllDvds().size());
        
        
        
    }

    

    /**
     * Test of removeDvd method, of class ClassDvdDao.
     */
    @Test
    public void testRemoveDvd() throws Exception {
        Dvd dvd1=new Dvd("Frozen");
        dvd1.setDirectorName("joe");
        dvd1.setRating("5");
        dvd1.setReleaseDate(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd1.setStudio("Walt disney");
        dvd1.setUserRating("Family");
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        
        
        
         Dvd dvd2=new Dvd("Superman");
        dvd2.setDirectorName("joe");
        dvd2.setRating("5");
        dvd2.setReleaseDate(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd2.setStudio("Walt disney");
        dvd2.setUserRating("Friends");
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        dao.removeDvd(dvd1.getTitle());
        assertEquals(1,dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd1.getTitle()));
        
        dao.removeDvd(dvd2.getTitle());
        assertEquals(0,dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd2.getTitle()));
        
        
    }


    
}
