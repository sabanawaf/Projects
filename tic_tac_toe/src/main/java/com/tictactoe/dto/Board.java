/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dsmelser
 */
public class Board {

    //all the board states that have lead to this one
    List<Board> history = new ArrayList<>();

    //the immediately preceding move that lead to this state
    int producingMove = -1;

    Mark[][] allMarks = new Mark[3][3];
    boolean xTurn = true;
    boolean xWins = false;
    boolean oWins = false;
    boolean gameOver = false;
    
    int gameId = -1;
    
    public int getId(){
        return gameId;
    }

    public List<Board> getHistory() {
        return history;
    }

    public int getProducingMove() {
        return producingMove;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public boolean xWins() {
        return xWins;
    }

    public boolean oWins() {
        return oWins;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Board(int gameId) {
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                allMarks[x][y] = Mark.Empty;
            }
        }
        
        this.gameId = gameId;
    }

    // Copy constructor
    private Board(Board that) {
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.allMarks[x][y] = that.allMarks[x][y];
            }
        }

        this.gameId = that.gameId;
        
        this.xTurn = that.xTurn;
        this.xWins = that.xWins;
        this.oWins = that.oWins;
        this.gameOver = that.gameOver;

        //copy the game history over
        this.history.addAll(that.history);

    }

    public Board makeMove(int pos) throws GameOverException, AlreadyMarkedException {

        pos--;
        int x = pos % 3;
        int y = pos / 3;

        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IndexOutOfBoundsException();
        }

        if (gameOver || xWins || oWins) {
            throw new GameOverException();
        }

        if (allMarks[x][y] != Mark.Empty) {
            throw new AlreadyMarkedException();
        }

        //copy board
        Board toReturn = new Board(this);

        //prepare mark
        Mark toMake = toReturn.xTurn ? Mark.X : Mark.O;

        //make move
        toReturn.allMarks[x][y] = toMake;

        toReturn.producingMove = pos + 1;

        //copy this to the game history
        toReturn.history.add(this);

        //determine win/loss/draw
        toReturn.computeWin();

        //flip turn
        toReturn.xTurn = !toReturn.xTurn;

        return toReturn;
    }

    public Mark getMark(int pos) {
        pos--;
        int x = pos % 3;
        int y = pos / 3;

        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IndexOutOfBoundsException();
        }

        return allMarks[x][y];
    }

    private void computeWin() {

        Mark checkFor = this.xTurn ? Mark.X : Mark.O;

        for (int x = 0; x < 3; ++x) {
            boolean rowSame = true;
            for (int y = 0; y < 3; ++y) {
                if (allMarks[x][y] != checkFor) {
                    rowSame = false;
                    break;
                }
            }
            if (rowSame) {
                this.gameOver = true;
                this.xWins = this.xTurn;
                this.oWins = !this.xTurn;
                break;
            }
        }

        for (int y = 0; y < 3; ++y) {
            boolean colSame = true;
            for (int x = 0; x < 3; ++x) {
                if (allMarks[x][y] != checkFor) {
                    colSame = false;
                    break;
                }
            }
            if (colSame) {
                this.gameOver = true;
                this.xWins = this.xTurn;
                this.oWins = !this.xTurn;
                break;
            }
        }

        boolean diagSame = true;
        for (int i = 0; i < 3; i++) {
            if (allMarks[i][i] != checkFor) {
                diagSame = false;
                break;
            }
        }
        if (diagSame) {
            this.gameOver = true;
            this.xWins = this.xTurn;
            this.oWins = !this.xTurn;

        }

        diagSame = false;
        for (int i = 0; i < 3; i++) {
            if (allMarks[i][2 - i] != checkFor) {
                diagSame = false;
                break;
            }
        }
        if (diagSame) {
            this.gameOver = true;
            this.xWins = this.xTurn;
            this.oWins = !this.xTurn;

        }

        if (!this.gameOver) {
            boolean anySpace = false;
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    if (this.allMarks[x][y] == Mark.Empty) {
                        anySpace = true;
                        break;
                    }
                }
                if (anySpace) {
                    break;
                }
            }

            this.gameOver = !anySpace;
        }
    }

}
