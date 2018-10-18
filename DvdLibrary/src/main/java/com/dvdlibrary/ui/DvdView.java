/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.ui;

import com.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public class DvdView {

    UserIO io;

    public DvdView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Dvd Collections");
        io.print("2. Add New Dvd");
        io.print("3. View a Dvd");
        io.print("4. Remove a Dvd");
        io.print("5. Edit");
        io.print("6. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 6);
    }

    public int printEditMenuAndGetEditSelection(Dvd dvd) {
        io.print("Edit Menu");
        io.print("1. Edit Title: " + dvd.getTitle());
        io.print("2. Edit Release Date: " + dvd.getReleaseDate());
        io.print("3. Edit MPAA Rating: " + dvd.getRating());
        io.print("4. Edit Director Name: " + dvd.getDirectorName());
        io.print("5. Edit Studio: " + dvd.getStudio());
        io.print("6. Edit User Rating: " + dvd.getUserRating());
        io.print("7. Return To Main Menu");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public void displayEditBanner() {
        io.print("===Edit UserRating===");
    }

    public void displayEditSuccessBanner() {
        io.print("===Edited Successfully===");
    }


    public Dvd getNewDvdInfo() {
        LocalDate ld;
        String title = io.readString("Please enter the title");
        LocalDate releaseDate = io.readLocalDate("Please enter the Release Date in the format MM/dd/yyyy");

        String rating = io.readString("Please enter Rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter the Studio");
        String userRating = io.readString("Please enter User Ratings");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setRating(rating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayCreateStudentBanner() {
        io.print("=== Add new Dvd ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Dvd successfully added.  Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getTitle() + ": "
                    + currentDvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " "
                    + currentDvd.getRating() + " " + currentDvd.getDirectorName() + " " + currentDvd.getStudio() + " " + currentDvd.getUserRating());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display Dvd ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the Dvd Title.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " " + dvd.getStudio() + " " + dvd.getRating() + " " + dvd.getDirectorName() + " " + dvd.getUserRating());

            io.print("");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Dvd successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public Dvd editDvdInfo() {
        String title = io.readString("Please edit/update the DVD title: ");
        LocalDate releaseDate = io.readLocalDate("Please edit/update the DVD release date (MM/dd/yyyy): ");
        String Rating = io.readString("Please edit/update the DVD  rating: ");
        String directorsName = io.readString("Please edit/update the director's name: ");
        String studio = io.readString("Please edit/update the studio : ");
        String userRating = io.readString("Please edit/update any user Rating ");
        Dvd currentDVD = new Dvd(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(Rating);
        currentDVD.setUserRating(userRating);
        currentDVD.setDirectorName(directorsName);
        currentDVD.setStudio(studio);

        return currentDVD;

    }
}
