/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.controller;

import com.tictactoe.dao.tic_tac_toeDao;
import com.tictactoe.dao.tic_tac_toeDaoDbImpl;
import com.tictactoe.dto.AlreadyMarkedException;
import com.tictactoe.dto.Board;
import com.tictactoe.dto.GameOverException;
import com.tictactoe.dto.Mark;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sabaaslam
 */
@Controller
public class TicTacToeController {

    tic_tac_toeDao dao = new tic_tac_toeDaoDbImpl();

    public TicTacToeController(tic_tac_toeDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayBaord(Model model) {

        int newGameID = dao.createGame();
        model.addAttribute("newGameID", newGameID);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String makeMove(HttpServletRequest request, Model model) throws GameOverException, AlreadyMarkedException {

        String s = (request.getParameter("newGameID"));
        int newGameID = Integer.parseInt(s);
        Board b = dao.selectGame(newGameID);

        String pos = request.getParameter("position");
        int position = Integer.parseInt(pos);
        try {
            b = b.makeMove(position);
            dao.insertMove(b);
        } catch (AlreadyMarkedException e) {
            String error = "Tile already Cliked";
            model.addAttribute("error", error);
        }
        model.addAttribute("b", b);
        model.addAttribute("newGameID", newGameID);

//        return "index";
        return "index";
    }
}