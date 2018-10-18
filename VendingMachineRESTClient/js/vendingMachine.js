money=0.0;
function addMoney(toAdd){

  money+=toAdd;
  $('#money-entered').val(money.toFixed(2));
}
$(document).ready(function(){
      loadItems();



      $('#dollar-button').click(function(){ addMoney(1);});
      $('#quarter-button').click(function(){ addMoney(.25);});
      $('#dime-button').click(function(){ addMoney(.1);});
      $('#nickel-button').click(function(){ addMoney(.05);});
      $('#penny-button').click(function(){ addMoney(.01);});


      $('#make-purchase').click(function(){
        makePurchase();
      });

      $('#change-return').click(clearAllForms);


});
//var money=0.0;

function makePurchase(){

  //var change='';
  var haveValidationErrors=checkAndDisplayValidation($('#add-form').find('input'));
  if(haveValidationErrors){
    return false;
  }
  var itemId=$('#item-id').val();

  var money=$('#money-entered').val();
  money=Math.round(money * 100) / 100;


  $.ajax({
    type:'GET',
    url:'http://localhost:8080/money/'+money+'/item/'+itemId,
    success:function(item,status){
      $('#errorMessages').empty();
        var change='';
        change+=" Quarters ="+item.quarters+",";
        change+=" Dimes ="+item.dimes+",";
        change+=" Nickels ="+item.nickels+",";
        change+=" Pennies ="+item.pennies;

        $('#change-output').val(change);
        $('#message').val('Thank You !');
        addMoney(-money);
       $("#itemRows").empty();
       loadItems();

    },
    error:function(jqXHR,b,c){

         $('#message').val((jqXHR.responseJSON).message);



    }

  });
}

function loadItems(){
  var itemRows=$('#itemRows');
  $.ajax({
    type:'GET',
    url:'http://localhost:8080/items',
    success:function(itemArray){
      $('#errorMessages').empty();
        $.each(itemArray, function(index,item){
          var itemId=item.id;
          var itemName=item.name;
          var itemPrice=item.price;
          var itemQuantity=item.quantity;

          //var row='<div class="col-sm-12">';
          var row = '<button type="button"  class="btn btn-default " onclick="showItemId('+itemId+')" >';
          //row+='<hr/>'
          row+='<p class="item-id">'+itemId+'</p>';
          row+='<p class="item-name">'+itemName+'</p>';
          row+='<p class="item-price">'+itemPrice+'</p>';
          row+='<p class="item-quantity">'+itemQuantity+'</p>';
          itemRows.append(row);
        });
    },
    error:function(){
      $('#errorMessages')
      .append($('<li>')
    .attr({class:'list-group-item list-group-item-danger'})
  .text('Error calling web services.Please try again later.'));
    }
  });

}
function showItemId(itemId){
  $('#item-id').val(itemId);
}



function clearAllForms(){
  $('#money-entered').val(0);
  $('#message').val("");
  $('#item-id').val("")
  $('#change-output').val("");
}

function checkAndDisplayValidation(input){
  $('#errorMessages').empty();
  var errorMessages = [];

  input.each(function(){
    if(!this.validity.valid){
    var errorField = $('label[for=' +this.id +']'). text();
    errorMessages.push(errorField+' ' +this.validationMessage);
      }
  });
  if(errorMessages.length > 0){
    $.each(errorMessages,function(index,message){
      $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
    });
    return true;
  }else{
    return false;
  }
}
