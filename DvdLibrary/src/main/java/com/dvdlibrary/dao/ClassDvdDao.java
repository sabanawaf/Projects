/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public interface ClassDvdDao {

    Dvd addDvd(String title, Dvd dvd) throws ClassDvdDaoException;

    List<Dvd> getAllDvds() throws ClassDvdDaoException;

    Dvd getDvd(String title) throws ClassDvdDaoException;

    Dvd removeDvd(String title) throws ClassDvdDaoException;

    Dvd editDvd(String title, Dvd dvd) throws ClassDvdDaoException;

}
