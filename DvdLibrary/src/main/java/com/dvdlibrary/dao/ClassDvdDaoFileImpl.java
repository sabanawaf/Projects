/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author sabaaslam
 */
public class ClassDvdDaoFileImpl implements ClassDvdDao {

    private Map<String, Dvd> dvds = new HashMap<>();

    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "::";

    public ClassDvdDaoFileImpl() throws ClassDvdDaoException {

        loadDvd();
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws ClassDvdDaoException {

        Dvd newDvd = dvds.put(title, dvd);
        writeDvd();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws ClassDvdDaoException {
        // loadDvd();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) throws ClassDvdDaoException {
        // loadDvd();
        return dvds.get(title);
    }

    @Override
    public Dvd removeDvd(String title) throws ClassDvdDaoException {
        Dvd removedDvd = dvds.remove(title);

        writeDvd();
        return removedDvd;

    }

    private void loadDvd() throws ClassDvdDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassDvdDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // Frozen::23 june::4::Jhon::Walt Disney::Good Movie

        String[] currentTokens;
        // Go through DVD_FILE line by line, decoding each line into a 
        // Dvd object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Dvd object and put it into the map of dvds
            // NOTE FOR APPRENTICES: We are going to use the DVd Title
            // which is currentTokens[0] as the map key for our dvd object.
            // We also have to pass the Dvd Title into the Dvd constructor
            Dvd currentDvd = new Dvd(currentTokens[0]);
            // Set the remaining vlaues on currentDvd manually

            currentDvd.setReleaseDate(LocalDate.parse(currentTokens[1], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            currentDvd.setStudio(currentTokens[2]);
            currentDvd.setRating(currentTokens[3]);
            currentDvd.setDirectorName(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);

            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster
     * for file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeDvd() throws ClassDvdDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new ClassDvdDaoException(
                    "Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        List<Dvd> dvdList = this.getAllDvds();

        for (Dvd currentDvd : dvdList) {

            // write the Student object to the file
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getRating() + DELIMITER
                    + currentDvd.getDirectorName() + DELIMITER
                    + currentDvd.getUserRating());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public Dvd editDvd(String oldtitle, Dvd dvd) throws ClassDvdDaoException {

        dvds.remove(oldtitle);
        dvds.put(dvd.getTitle(), dvd);

        writeDvd();
        return dvd;

    }

}
