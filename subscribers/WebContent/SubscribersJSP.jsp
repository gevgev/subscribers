<%@page import="com.demo.Subscriber"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<b>The Demo Object Names Are:-
<br>
 
<table>
<c:forEach items="${subscribersCtrl}" var="subscriber">
    <tr>
        <td>${subscriber.apiKey}</td>
        <td>${subscriber.mobileToken}</td>
    </tr>
</c:forEach>
</table>
 
</body>
</html>