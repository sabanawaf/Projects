/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary;

import com.dvdlibrary.controller.ClassDvdController;
import com.dvdlibrary.dao.ClassDvdDao;
import com.dvdlibrary.dao.ClassDvdDaoException;
import com.dvdlibrary.dao.ClassDvdDaoFileImpl;
import com.dvdlibrary.ui.DvdView;
import com.dvdlibrary.ui.UserIO;
import com.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author sabaaslam
 */
public class App {

    public static void main(String[] args) throws ClassDvdDaoException{

        UserIO myIo = new UserIOConsoleImpl();
        DvdView myView = new DvdView(myIo);
        ClassDvdDao myDao = new ClassDvdDaoFileImpl();
        ClassDvdController controller = new ClassDvdController(myDao, myView);
        controller.run();

    }
}
