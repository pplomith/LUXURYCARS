<%--
  Created by IntelliJ IDEA.
  User: Pollax
  Date: 05/04/2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Log In </title>
    <link href = "./css/color.css" type = "text/css" rel = "stylesheet">
    <link href = "./css/structure.css" type = "text/css" rel = "stylesheet">
</head>
<body>

<div class = "hero">

    <header>
        <div class = "container">
            <nav>
                <ul>
                    <li><a href = "/index.jsp"> Home </a></li>
                    <li><a href = "#"> About </a></li>
                    <li><a href = "#"> Contacts </a></li>
                </ul>
            </nav>
        </div>
    </header>


    <div class = "form-box">
        <div class = "button-box">
            <div id = "btn"> </div>
            <button type = "button" class = "toggle-btn" onclick = "login()"> Log In </button>
            <button type = "button" class = "toggle-btn" onclick = "register()"> Register </button>
        </div>

        <form id = "login" class = "input-group" action = "login" method = "post">
            <input type = "text" class = "input-field" placeholder = "Username" required>
            <input type = "text" class = "input-field" placeholder = "Enter Password" required>
            <input type = "checkbox" class = "check-box"><span> Remember Password </span>
            <button type = "submit" class = submit-btn> Log In </button>
        </form>

        <form id = "register" class = "input-group" action = "registration" method = "post">
            <input type = "text" class = "input-field" placeholder = "Username" name = "username" required>
            <input type = "text" class = "input-field" placeholder = "Nome" name = "nome" required>
            <input type = "text" class = "input-field" placeholder = "Cognome" name = "cognome" required>
            <input type = "email" class = "input-field" placeholder = "Email" name = "email" required>
            <input type = "password" class = "input-field" placeholder = "Enter Password" name = "password" required>
            <input type = "checkbox" class = "check-box" required><span> I agree to terms & conditions </span>
            <button type = "submit" class = submit-btn> Register </button>
        </form>
    </div>

    <footer>
        <h5> Copyright 2020 -- MPLUXURYCARS --. All rights reserved </h5>
    </footer>
</div>
</body>
</html>

<script>

    var x = document.getElementById("login");
    var y = document.getElementById("register");
    var z = document.getElementById("btn");

    function register(){

        x.style.left = "-400px";
        y.style.left = "50px";
        z.style.left = "110px"

    }

    function login(){

        x.style.left = "50px";
        y.style.left = "450px";
        z.style.left = "0px"
    }

</script>