<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Subscriber</title>
</head>
<body>
	<h1>Add Subscriber</h1>
	<form action="addSubscriber" method="POST">
		<table>
			<tr>
				<th>apiKey</th>
				<th>Mobile Token</th>
			</tr>
			
			<tr>
				<td> <input type="text" name="apiKey"> </td>
				<td> <input type="text" name="mobileToken" /> </td>
			</tr>
		</table>
	
		<br />
		
		<input type="submit" value="Submit" />
	</form>
</body>
</html>