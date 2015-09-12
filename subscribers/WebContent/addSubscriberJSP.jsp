<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Subscriber Page</title> 

</head>
<body>

<c:choose>
    <c:when test="${subscriberId==null}">
		<h1>Add Subscriber</h1>
    </c:when>    
    <c:otherwise>
		<h1>Update Subscriber</h1>
    </c:otherwise>
</c:choose>

	<form action="addSubscriber" method="POST">
		<table>
			<tr>
				<th>apiKey</th>
				<th>Mobile Token</th>
			</tr>
			
			<tr>
				<td> <input type="text" name="apiKey" value="${apiKey}"> </td>
				<td> <input type="text" name="mobileToken" value="${mobileToken}" /> </td>

				<c:choose>
				    <c:when test="${subscriberId!=null}">
						<td> <input type="text" name="subscriberId" value="${subscriberId}" disabled/> </td>
			    	</c:when>    
			</c:choose>
				
			</tr>
		</table>
	
		<br />
		
		<input type="submit" value="Submit" />
	</form>
</body>
</html>