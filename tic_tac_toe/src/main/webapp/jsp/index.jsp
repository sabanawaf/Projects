<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tic Tac Toe</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <style>

            body{
                background-color: beige;
            }
            button{
                background-color: white;
                width: 70px;
                height:50px;

            }
        </style>
    </head>
    <body>
        <h1 class="center-block">Tic-Tac-Toe</h1>
        <hr/>

        <c:choose>
            <c:when test="${!b.isGameOver()}">

                <div class="container" id="buttonDiv">
                    <form action="/" method="GET">

                        <button type="button" class="btn btn-default" id="tile1" onclick="tileClick(1)"><c:out value = "${b.getMark(1).toString()}"/></button>
                        <button type="button" class="btn btn-default" id="tile2" onclick="tileClick(2)" ><c:out value = "${b.getMark(2).toString()}"/></button>
                        <button type="button" class="btn btn-default" id="tile3" onclick="tileClick(3)" ><c:out value = "${b.getMark(3).toString()}"/></button><br/>
                        <button type="button" class="btn btn-default" id="tile4" onclick="tileClick(4)" ><c:out value = "${b.getMark(4).toString()}"/></button>
                        <button type="button" class="btn btn-default" id="tile5" onclick="tileClick(5)" ><c:out value = "${b.getMark(5).toString()}"/></button>
                        <button type="button" class="btn btn-default" id="tile6" onclick="tileClick(6)" ><c:out value = "${b.getMark(6).toString()}"/></button><br/>
                        <button type="button" class="btn btn-default" id="tile7" onclick="tileClick(7)" ><c:out value = "${b.getMark(7).toString()}"/></button>
                        <button type="button" class="btn btn-default" id="tile8" onclick="tileClick(8)" ><c:out value = "${b.getMark(8).toString()}"/></button>
                        <button type="button" class="btn btn-default" id="tile9" onclick="tileClick(9)" ><c:out value = "${b.getMark(9).toString()}"/></button>

                    </form>
                </div>
            </c:when>
            <c:when test="${b.xWins()}">
                <h1 class="center-block">X Wins!</h1>
            </c:when>
            <c:when test="${b.oWins()}">
                <h1 class="center-block">O Wins!</h1>
            </c:when>
                <c:otherwise><h1 class="center-block">Draw</h1></c:otherwise>
           
        </c:choose>
                
                <a href="/tic_tac_toe/" class="btn btn-primary" role="button">New Game</a>
                <c:out value = "${error}"/>
                
        <form method="POST"  id="makeMove" action="/tic_tac_toe/">
            <input type="hidden" id="position" name="position"/>
            <input type='hidden' name="newGameID" value="${newGameID}">
            <!-- Placed at the end of the document so the pages load faster -->
        </form>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tictactoe.js"></script>



    </body>
</html>

