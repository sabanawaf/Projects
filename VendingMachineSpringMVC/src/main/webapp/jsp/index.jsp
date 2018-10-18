<%-- 
    Document   : items.jsp
    Created on : Sep 28, 2018, 1:45:46 PM
    Author     : sabaaslam
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vending Machine Items</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Vending Machine Items!</h1>
    <li role="presentation"
        class="active">



        <c:forEach var="currentItem" items="${ItemList}">
            <button onclick="displayItemName('${currentItem.getItemName()}')">


                <p>


                    <c:out value="${currentItem.itemName}"/></br> 
                    <c:out value="Cost: ${currentItem.cost}"/></br>
                    <c:out value="Inventory: ${currentItem.inventory}"/></br>

                </p>
            </button>



        </c:forEach> 
        
      

        <div class="col-md-12">
            <h1>Total $ In</h1>
            <form class="form-horizontal" role="form" id="add-money-form" method="POST" action="/VendingMachineSpringMVC/">


                <div class="form-group">
                    <label for="money-entered" class="col-md-2 control-label">
                    </label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="money-entered"
                               name="money-entered"
                               placeholder="Money" readonly required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="button"

                                id="dollar-button"
                                class="btn-btn-default">
                            Add Dollar
                        </button>

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="button"

                                id="quarter-button"
                                class="btn-btn-default">
                            Add Quarter
                        </button>

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="button"

                                id="dime-button"
                                class="btn-btn-default">
                            Add Dime
                        </button>

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="button"

                                id="nickel-button"
                                class="btn-btn-default">
                            Add Nickel
                        </button>

                    </div>
                </div>


<!--            </form>-->
        </div>


        <hr/>     

        <div class="col-md-12">
            <h1>Messages</h1>
<!--            <form class="form-horizontal" role="form" id="messages" >-->
                <div class="form-group">
                    <div class="col-md-8">
                        <input type="text"
                               value="${error}"
                               class="form-control"
                               id="message"
                               placeholder="Welcome"  readonly required/>
                    </div>
                </div>


                <div class="form-group">


                    <label for="itemName" class="col-md-2 control-label">
                        Item:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="itemName"
                               name="itemName"
                               placeholder="Item Num" readonly required/>
                    </div>
                </div>



                <div class="form-group" >
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" 

                                id="make-purchase"
                                class="btn-btn-default">
                            Make Purchase
                        </button>

                    </div>
                </div>


            <!--</form>-->



        </div>
        <hr/>
        
        

<div class="col-md-12">
  <h1>Change</h1>
<!--  <form class="form-horizontal" role="form" id="change">-->
    <div class="form-group">
      <div class="col-md-8">
        <input type="text"
               value="${changeOutput}"
                class="form-control"
                id="change-output"
                name="change-output"
                placeholder="Change" readonly />
          
    </div>
  </div>



  <div class="form-group">
    <div class="col-md-offset-4 col-md-8">
        <button type="button" onclick=" clearAllForms()"

              id="change-return"
              class="btn-btn-default">
              Change Return
            </button>

  </div>
  </div>

  <!--</form>-->


</div>
<hr/>

</form>

        <!-- Main Page Content Start -->

        <!-- Main Page Content Stop --> 


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/item.js"></script>
    </body>
</html>
