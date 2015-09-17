<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<body>
<b>The Demo Object Names Are </b>>
<br>
 
<table>
<c:forEach items="${demoNames}" var="DemoNames">
    <tr>
        <td>${DemoNames.name}</td>
    </tr>
</c:forEach>
</table>
 
</body>
</html>