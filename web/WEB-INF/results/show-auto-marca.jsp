<%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 30/03/2020
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href = "./css/cars/color_car.css" rel = "stylesheet" type = "text/css">
<link href = "./css/cars/structure_car.css" rel = "stylesheet" type = "text/css">

<%
    String marca = (String)request.getAttribute("marcaAuto");
%>

<html>
<head>
    <title> <%=marca%> </title>
</head>
<body>

<div class = "hero">

    <header>
        <div class = "container">
            <nav>
                <ul>
                    <li><a href = "index.html"> Home </a></li>
                    <li><a href = "#"> About </a></li>
                    <li><a href = "#"> Contacts </a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class = "filter">
        <form action = "research" method = "get" class = "research">

            <label for = "modello"></label>

            <select id = "modello" class = "scelta" >
                <option value = "all"> Tutti i Modelli </option>
            </select>

            <label for = "vel"></label>

            <select id = "vel" class = "scelta">
                <option value = "all"> Max Speed </option>
            </select>

            <input type = "submit" value = "Cerca"> </input>
        </form>
    </div>

    <div class = "car_cont">
        <table>
        </table>
    </div>

    <footer>
        <h5> Copyright 2020 -- MPLUXURYCARS --. All rights reserved </h5>
    </footer>
</div>



</body>
</html>
