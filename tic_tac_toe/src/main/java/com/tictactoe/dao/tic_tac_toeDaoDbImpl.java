/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.dao;

import com.tictactoe.dto.AlreadyMarkedException;
import com.tictactoe.dto.Board;
import com.tictactoe.dto.GameOverException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sabaaslam
 */
public class tic_tac_toeDaoDbImpl implements tic_tac_toeDao {

    private static final String SQL_INSERT_BOARD
            = "insert into Games"
            + " (GameOutcome)"
            + " values(?)";
    private static final String SQL_SELECT_ALL_MOVES
            = "select * from Games"
            + " left join Moves on Games.GameID = Moves.GameID"
            + " where Games.GameID = ?";
    
    private static final String SQL_INSERT_MOVES
            ="insert into moves"
            +" (GameID,PositionOfMove,move_order)"
            +" values(?,?,?)";
            

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

  

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int createGame() {
        
        int rowsAffected = jdbcTemplate.update(SQL_INSERT_BOARD, "incomplete");
  
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        return newId;
    }

    @Override
    public Board selectGame(int GameID) {
        
        //insert integer from view into array
        //pass object argument into query (convert int to Integer object)
        Object[] gameIds = new Object[1];
        gameIds[0] = GameID;
                
        
        return jdbcTemplate.query(SQL_SELECT_ALL_MOVES, gameIds, new MovesTable());
    }

    @Override
    public void insertMove(Board board) {
        int toReturn = jdbcTemplate.update(SQL_INSERT_MOVES,
              board.getId(),
              board.getProducingMove(),
                board.getHistory().size());
            
        }

    private static final class MovesTable implements ResultSetExtractor<Board> {

        public Board extractData(ResultSet rs) throws SQLException, DataAccessException {
            Board board = null;
            while (rs.next()) {
                try {
                    if (board == null) {
                        board = new Board(rs.getInt("GameID"));
                    }
                    int positionMove = rs.getInt("PositionOfMove");
                    
                    if( !rs.wasNull() ){
                        board = board.makeMove(positionMove);
                    }
                    
                    
                    

                } catch (GameOverException ex) {
                    Logger.getLogger(tic_tac_toeDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AlreadyMarkedException ex) {
                    Logger.getLogger(tic_tac_toeDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            return board;
        }

    }

}
