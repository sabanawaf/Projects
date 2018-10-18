<%-- 
    Document   : search
    Created on : Sep 26, 2018, 3:01:20 PM
    Author     : sabaaslam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        
        
        <div class="container">
            <h1>Search Contacts</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                            
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                Home
                            </a>
                        </li>
                	<li role="presentation" ><a href="${pageContext.request.contextPath}/displayContactPage">
                                Contacts
                            </a>
                        </li>
                        <li role="presentation" class="active">
                            
                            <a href="${pageContext.request.contextPath}/displaySearchPage">
                                Search
                            </a>
                        </li>
                </ul>    
            </div>
            <h2>Welcome to the Company Contacts Demo Project</h2>
        </div>
        
    </body>
</html>
