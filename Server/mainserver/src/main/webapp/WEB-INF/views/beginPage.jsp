<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>whoever</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="<c:url value="/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<c:url value="/bootstrap/css/bootstrap-them.min.css" />"
	rel="stylesheet"/>
	
<link href="<c:url value="/sources/css/whoever.css" />" rel="stylesheet" />
</head>
<body class="begin">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" 
		style="background: #E43F4D; border: 0;">
		<div class="container" >
			<div class="navbar-header" >
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<b><font size="32" href="#" color="white">Whoever</font></b></h2>
			</div>
			<div class="navbar-collapse collapse" style="margin-top: 10px" >
				<form  action="home" method="post" class="navbar-form navbar-right" role="form">
					<div class="form-group">
						<input type="text" placeholder="Account" name="ssoId" class="form-control" required>
					</div>
					<div class="form-group" >
						<input type="password" placeholder="Password" name="password" class="form-control" required>
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>
	
	<div class="container col-md-4" style="margin-top: 90px;">
		<img class="col-md-9 col-xs-offset-1" src="<c:url value="/sources/images/android.png"/>">
	</div>

	<div class="container col-md-8">
	<!--<img class="img_background" src="<c:url value="/sources/images/background.jpg"/>">-->
		<div class="jumbotron" style="margin-top: 90px;">
       	 	<font size="24px" color="black">The new social networking</font>
        	<p>Are you want to using a new social networking?</p>
        	<p>This is social networking anonymous for you. Now, you could share your tone with every people nearby.</p>
        	<p>Try this,</p>
        	<p>
        	<form action="anonymous">
        		<button class="btn btn-lg btn-success btn-danger" type="submit">Using as anonymous &raquo;</button>
        	</form>
       		 </p>
       		 <p>
       		 <p>Or</p>
        	<form action="anonymous">
        		<button class="btn btn-lg btn-success btn-danger" type="submit">Register new account&raquo;</button>
        	</form>
       		 </p>
      </div>
	</div>
	<!-- /.container -->
	<script type="text/javascript"
		src="<c:url value="/bootstrap/js/jquery-1.12.3.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
	
</body>
</html>