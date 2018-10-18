/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.service;

import com.tictactoe.dao.tic_tac_toeDao;
import com.tictactoe.dto.Board;
import java.sql.ResultSet;

/**
 *
 * @author sabaaslam
 */
public class TicTacToeServiceImpl implements TicTacToeService{
    tic_tac_toeDao dao;
    
    public TicTacToeServiceImpl(tic_tac_toeDao dao){
        this.dao =dao;
    }

    @Override
    public int createGame() {
        return dao.createGame();
    }


    
    
    
}
