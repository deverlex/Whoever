<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>whoever - admin</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="<c:url value="/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<c:url value="/bootstrap/css/bootstrap-them.min.css" />"
	rel="stylesheet"/>
	
<link href="<c:url value="/sources/css/whoever.css" />" rel="stylesheet" />
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" 
		style="background: #E43F4D; border: 0;">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <b><font size="32" color="white">Whoever</font></b></h2>
          </div>
          <div class="navbar-collapse collapse navbar-right" style="margin-top: 20px;">
            <ul class="nav navbar-nav">
              <li class="#active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Account</a></li>
                  <li><a href="#">Logout</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
      
    <div class="container">
    
    	<div class="jumbotron" style="margin-top: 90px;">
       	 	<font size="18px" color="black">Manager Thread</font>
  			<p>
        	<form action="start">
        		<button class="btn btn-lg btn-success btn-danger" type="submit">Start &raquo;</button>
        	</form>
       		 </p>
       		 <p>
       		 <p>Or</p>
        	<form action="stop">
        		<button class="btn btn-lg btn-success btn-danger" type="submit">Stop &raquo;</button>
        	</form>
       		 </p>
      </div>
    </div>
      
      <script type="text/javascript"
		src="<c:url value="/bootstrap/js/jquery-1.12.3.min.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
</body>
</html>