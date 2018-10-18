/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.dao;

import com.tictactoe.dto.Board;
import java.util.List;

/**
 *
 * @author sabaaslam
 */
public interface tic_tac_toeDao {
    
    public int createGame();
    
    public void insertMove(Board board);
    
    public Board selectGame(int GameID);
    
}
