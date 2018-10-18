<%-- 
    Document   : contactDetails.jsp
    Created on : Sep 27, 2018, 6:00:38 AM
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
        <title>Company Contacts</title>
    </head>
    <body>
        <h1>Contact Details</h1>
        <hr/>
                 <div class="navbar">
                <ul class="nav nav-tabs">
                  <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayContactsPage">
                          Contacts
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySearchPage">
                          Search
                      </a>
                  </li>
                </ul>    
            </div>
                <p>
                    Name: <c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/>
                </p>
                <p>
                    Company: <c:out value="${contact.company}"/>
                </p>
                <p>
                    Phone: <c:out value="${contact.phone}"/>
                </p>
                <p>
                    Email: <c:out value="${contact.email}"/>
                </p>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
