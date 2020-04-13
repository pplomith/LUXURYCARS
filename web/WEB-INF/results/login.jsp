<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title> Log In </title>
	<link href = "./css/style_login.css" rel = "stylesheet" type = "text/css">
	<link href = "./css/style_nav.css" rel = "stylesheet" type = "text/css">
	<link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body onresize = "res()">
	<div class = "hero" id = "hero">
		<%@ include file = "/WEB-INF/results/header.jsp"%>

		<div class = "form-box" id = "form">
			<div class = "button-box">
				<div id = "btn"> </div>
				<button type = "button" class = "toggle-btn" onclick = "login()"> Log In </button>
				<button type = "button" class = "toggle-btn" onclick = "register()"> Register </button>
			</div>
			
			<form id = "login" class = "input-group">
				<input type = "text" class = "input-field" placeholder = "Username" required>
				<input type = "text" class = "input-field" placeholder = "Enter Password" required>
				<input type = "checkbox" class = "check-box"><span> Remember Password </span>
				<button type = "submit" class = submit-btn> Log In </button>
			</form>

			<form id = "register" class = "input-group">
				<input type = "text" class = "input-field" placeholder = "Username" required>
				<input type = "text" class = "input-field" placeholder = "Nome" required>
				<input type = "text" class = "input-field" placeholder = "Cognome" required>
				<input type = "email" class = "input-field" placeholder = "Email" required>
				<input type = "text" class = "input-field" placeholder = "Enter Password" required>
				<input type = "checkbox" class = "check-box"><span> I agree to terms & conditions </span>
				<button type = "submit" class = submit-btn> Register </button>
			</form>
		</div>

		<footer>
			<h5> Copyright 2020 -- MPLUXURYCARS --. All rights reserved </h5>
		</footer>
	</div>

	<script>
		
		var x = document.getElementById("login");
		var y = document.getElementById("register");
		var z = document.getElementById("btn");

		function register(){

			x.style.left = "-400px";
			y.style.left = "50px";
			z.style.left = "110px";

		}

		function login(){

			x.style.left = "50px";
			y.style.left = "450px";
			z.style.left = "0px";
		}

		function hide_show() {

			var t = document.getElementById("form");

			if (t.style.display == "none") 
				t.style.display = "block";

			else 
				t.style.display = "none";
		}

		function res() {

			var t = document.getElementById("form");
			var c = document.getElementById("check");

			if (window.innerWidth >= 921 && t.style.display == "none") {
				c.click();
				t.style.display = "block";
			}
		}
	</script>
</body>
</html>