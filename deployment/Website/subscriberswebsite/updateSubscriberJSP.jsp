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

	<h1>Update Subscriber</h1>

	<form action="updateSubscriber" method="POST">
		<input type="hidden" name="id" value="${subscriberId}" />
		<table>
			<tr>
				<th>apiKey</th>
				<th>Mobile Token</th>
			</tr>
			
			<tr>
				<td> <input type="text" name="apiKey" value="${apiKey}"> </td>
				<td> <input type="text" name="mobileToken" value="${mobileToken}" /> </td>
				<td> <input type="text" name="subscriberId" value="${subscriberId}" disabled/> </td>
			</tr>
		</table>
	
		<br />
		
		<input type="submit" value="Submit" />
	</form>
</body>
</html>