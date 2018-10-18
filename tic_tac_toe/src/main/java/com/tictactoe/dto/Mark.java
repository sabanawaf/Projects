/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.dto;

/**
 *
 * @author dsmelser
 */
public enum Mark {
     X("X"), O("O"), Empty(" ");

  private String textValue;

  Mark(String textValue) {
    this.textValue = textValue;
  }

  public String toString(){
        return textValue;
      }
    
    
}
