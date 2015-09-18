<%@page import="common.models.Subscriber"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<script>
function deleteSubscriber(id) {
	var answer = confirm("Delete subscriber id:" + id + "?")
	if (answer){
		window.location.href = 'deleteSubscriber?id='+id;		
	}
	else{
	}
}
</script>

<b>The Demo Object Names Are:-</b>
<br>
 
<table border="1" style="width:100%" >
<tr>
<th>SubscriberId</th>
<th>apiKey</th>
<th>mobileToken</th>
<th colspan=2>Action</th>
</tr>

<c:forEach items="${subscribersCtrl}" var="subscriber">
    <tr>
    	<td>${subscriber.subscriberId}</td>
        <td>${subscriber.apiKey}</td>
        <td>${subscriber.mobileToken}</td>
        <td><a href=updateSubscriber?id=${subscriber.subscriberId}>Edit</a></td>        
        <td><input type="button" onclick="deleteSubscriber(${subscriber.subscriberId})" name="Delete" value="Delete"></td>        
    </tr>
</c:forEach>
</table>
 <p>
 <p>
<a href="addSubscriber">Add New Subscriber</a> 
</body>
</html>