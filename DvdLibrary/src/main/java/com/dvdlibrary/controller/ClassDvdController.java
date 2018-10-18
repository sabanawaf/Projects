/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.controller;

import com.dvdlibrary.dao.ClassDvdDao;
import com.dvdlibrary.dao.ClassDvdDaoException;
import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.ui.DvdView;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public class ClassDvdController {

    DvdView view;
    ClassDvdDao dao;

    public ClassDvdController(ClassDvdDao dao, DvdView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassDvdDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {

        return view.printMenuAndGetSelection();

    }

    private void createDvd() throws ClassDvdDaoException {
        view.displayCreateStudentBanner();
        Dvd newdvd = view.getNewDvdInfo();
        dao.addDvd(newdvd.getTitle(), newdvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() throws ClassDvdDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvd() throws ClassDvdDaoException {
        view.displayDisplayStudentBanner();
        String title = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws ClassDvdDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        dao.removeDvd(title);
        view.displayRemoveSuccessBanner();
    }

    private void editDvd() throws ClassDvdDaoException {
        view.displayEditBanner();

        String title = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(title);
        
        view.displayDvd(dvd);
        dao.removeDvd(title);
        Dvd newDvd = view.editDvdInfo();

        dao.addDvd(newDvd.getTitle(), newDvd);

        view.displayEditSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
