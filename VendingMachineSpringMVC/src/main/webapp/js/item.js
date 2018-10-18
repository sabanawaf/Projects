/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */money=0.0;
function addMoney(toAdd){

  money+=toAdd;
  $('#money-entered').val(money.toFixed(2));
}
$(document).ready(function(){
      



      $('#dollar-button').click(function(){ addMoney(1);});
      $('#quarter-button').click(function(){ addMoney(.25);});
      $('#dime-button').click(function(){ addMoney(.1);});
      $('#nickel-button').click(function(){ addMoney(.05);});
      $('#penny-button').click(function(){ addMoney(.01);});
      
      


});

function displayItemName(itemName){
 $('#itemName').val(itemName);
}


function clearAllForms(){
  $('#money-entered').val("");
  $('#message').val("");
  $('#itemName').val("");
  $('#change-output').val("");
}
