<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>whoever server</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/sources/css/custom.css" />" rel="stylesheet">
</head>
<body>
	<div>
		<form action="">
			<table>
				<tr>
					<td>Account ID: </td>
					<td><input type="text" name="ssoId" /></td>
				</tr>
				<tr>
					<td>Password: </td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td/>
					<td><input type="submit" name="submit" value="Login"/></td>
			</table>
		</form>
	</div>
</body>
</html>