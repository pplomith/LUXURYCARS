<%--
  Created by IntelliJ IDEA.
  User: Pollax
  Date: 05/04/2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href = "./css/style_homepage.css" type = "text/css" rel = "stylesheet" />
    <link href="./css/nav_structure.css" type="text/css" rel="stylesheet"/>
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
    <title>Our site</title>
</head>
<body onload = "LoadRandomBackground(); StartBackgroundRefreshTimer()">

<%--<nav class="nav_homepage">--%>
<%--    <input type="checkbox" id="check" name="checkbox" onclick="MostraTable('auto')">--%>
<%--    <label for="check" class="checkbtn">--%>
<%--        <i class="fas fa-bars"></i>--%>
<%--    </label>--%>
<%--    <label class="logo">LUXURY</label>--%>
<%--    <ul>--%>
<%--        <li><a class="active" href="#">Home</a></li>--%>
<%--        <li><a href="#">About</a></li>--%>
<%--        <li><a href="#">Services</a></li>--%>
<%--        <li><a href="#">Contact</a></li>--%>
<%--        <li><a href="login.html"><i class="fa fa-user"></i> </a></li>--%>
<%--    </ul>--%>
<%--</nav>--%>
<%@ include file="WEB-INF/results/header.jsp"%>
<table id="auto" name="auto">
    <tr>
        <td></td>
        <form action = "show-auto-marca" method = "get">
            <td id ="lamborghini" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Lamborghini"> Lamborghini </button>
            </td>
        </form>

        <form action = "show-auto-marca" method = "get">
            <td id = "ferrari" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Ferrari"> Ferrari </button>
            </td>
        </form>

        <form action = "show-auto-marca" method = "get">
            <td id = "mclaren" onmouseover = "setNewImage(id);StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "McLaren"> McLaren </button>
            </td>
        </form>

        <form action = "show-auto-marca" method = "get">
            <td id = "bugatti" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Bugatti"> Bugatti </button>
            </td>
        </form>
        <td></td>
    </tr>

    <tr>
        <td></td>

        <form action = "show-auto-marca" method = "get">
            <td id = "astonmartin" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Aston Martin"> Aston Martin </button>
            </td>
        </form>

        <form action = "show-auto-marca" method = "get">
            <td id = "bentley" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Bentley"> Bentley </button>
            </td>
        </form>

        <form action = "show-auto-marca" method = "get">
            <td id = "tesla" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Tesla"> Tesla </button>
            </td>
        </form>

        <form action = "show-auto-marca" method = "get">
            <td id = "koenigsegg" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                <button type = "submit" name = "Koenigsegg"> Koenigsegg </button>
            </td>
        </form>

        <td></td>
    </tr>
</table>

</body>

</html>

<script type="text/javascript">

    var timer;
    var n = 0;
    var bgImages= new Array();


    bgImages.push("./images/home3.jpg");
    bgImages.push("./images/home5.jpg");
    bgImages.push("./images/home6.jpg");
    bgImages.push("./images/home8.jpg");
    bgImages.push("./images/home9.jpg");
    bgImages.push("./images/home10.jpg");

    var bgImagesCars = new Array();

    bgImagesCars.push("./images/rotanti/bugatti1.jpeg");
    bgImagesCars.push("./images/rotanti/astonmartin1.jpg");
    bgImagesCars.push("./images/rotanti/ferrari10.jpg");
    bgImagesCars.push("./images/rotanti/lamborghini10.jpg");
    bgImagesCars.push("./images/rotanti/mclaren5.jpg");
    bgImagesCars.push("./images/rotanti/tesla.jpg");
    bgImagesCars.push("./images/rotanti/koenigsegg1.jpg");
    bgImagesCars.push("./images/rotanti/bentley1.jpg");


    function setNewImage(id) {

        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundAttachment ="fixed";
        document.body.style.backgroundPosition = "50% 50%";
        document.body.style.transition = "background 0.5s ease-in-out";

        switch (id) {

            case "bugatti": document.body.background = bgImagesCars[0]; break;
            case "astonmartin": document.body.background = bgImagesCars[1]; break;
            case "ferrari": document.body.background = bgImagesCars[2]; break;
            case "lamborghini": document.body.background = bgImagesCars[3]; break;
            case "mclaren": document.body.background = bgImagesCars[4]; break;
            case "tesla": document.body.background = bgImagesCars[5]; break;
            case "koenigsegg": document.body.background = bgImagesCars[6]; break;
            case "bentley": document.body.background = bgImagesCars[7]; break;
        }
    }

    function LoadRandomBackground() {

        document.body.style.backgroundSize= "cover";
        document.body.style.backgroundRepeat= "no-repeat";
        document.body.style.backgroundAttachment="fixed";
        document.body.style.backgroundPosition= "50% 50%";
        document.body.style.transition= "background 1s ease-in-out";

        if (n == 6){

            document.body.background = bgImages[0];
            n = 1;
        }

        else {

            document.body.background = bgImages[n];
            n++;
        }
    }

    function StartBackgroundRefreshTimer() {

        timer = setInterval('LoadRandomBackground()', 4000); //4seconds
    }

    function StopBackgroundRefreshTimers() {

        clearInterval(timer);
    }

    function MostraTable(id) {

        if(document.getElementById(id).style.visibility == "hidden"){
            // document.getElementById(id).style.transition="all 0.5s";
            document.getElementById(id).style.visibility = "visible";
        }

        else{

            // document.getElementById(id).style.transition="all 0.5s";
            document.getElementById(id).style.visibility = "hidden";
        }
    }

</script>