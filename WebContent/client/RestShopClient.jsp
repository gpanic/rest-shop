<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REST Shop Client</title>
</head>
<body>
<h1>REST Shop Client</h1>
<form action="/rest-shop/RestShopClient" method="post">
	Select method:
	<select name="selectMethod">
		<option value="POST">POST</option>
		<option value="GET">GET</option>
		<option value="PUT">PUT</option>
		<option value="DELETE">DELETE</option>
	</select></br></br>
	Resource URI:
	<input type="text" name="uri" style="width:250px;"/></br></br>
	<textarea name="content" style="width:400px;height:200px;"></textarea></br></br>
	<input type="submit" value="Send" />
</form>
</body>
</html>