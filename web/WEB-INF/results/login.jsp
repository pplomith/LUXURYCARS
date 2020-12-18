<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <title> Log In </title>
    <link href = "./css/style_login.css" rel = "stylesheet" type = "text/css">
    <link href = "./css/style_nav.css" rel = "stylesheet" type = "text/css">
    <link rel = "stylesheet" type = "text/css" href="./css/style_footer.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>

<div class = "hero" id = "hero" >
    <%@ include file = "/WEB-INF/results/header.jsp"%>

    <div class = "form-box" id = "form">
        <div class = "button-box">
            <div id = "btn"> </div>
            <button type = "button" class = "toggle-btn" onclick = "login()"> Log In </button>
            <button type = "button" class = "toggle-btn" onclick = "register()"> Register </button>
        </div>

        <form id = "login" class = "input-group" action = "login" method = "post">
            <input type = "hidden" name = "acquistoProssimo" value = "${acquistoProssimo}">
            <input type = "hidden" name = "accessoCarrello" value = "${accessoCarrello}">
            <input type = "hidden" name = "nomeAuto" value = "${auto.nomeAuto}">
            <input type = "hidden" name = "casaAuto" value = "${auto.casaAuto}">
            <input type = "text" class = "input-field" name = "username" placeholder= "Username" required>
            <input type = "password" class = "input-field" name = "password" placeholder = "Enter Password" required>
            <input type = "checkbox" class = "check-box"><span> Remember Password </span>
            <button type = "submit" class = submit-btn> Log In </button>
        </form>

        <form action = "registration" id = "register" class = "input-group" method = "post">
            <input type = "text" class = "input-field" id="username_reg" name = "username" placeholder = "Username" oninput="validaUsername()">
            <input type = "text" class = "input-field" id="nome_reg" name = "nome" placeholder = "Nome" oninput="validaNome()">
            <input type = "text" class = "input-field" id="cognome_reg" name = "cognome" placeholder = "Cognome" oninput="validaCognome()">
            <input type = "email" class = "input-field" id="email_reg" name = "email" placeholder = "Email" oninput="validaEmail()">
            <input type = "password" class = "input-field" id="password_reg" name = "password" placeholder = "Enter Password" oninput="validaPassword()">
            <input type = "checkbox" id="agree" class = "check-box" onchange="cambiaStatoRegistrami()"><span> I agree to terms & conditions </span>
            <button type = "submit" id="registrami" class = submit-btn disabled> Register </button>
        </form>
    </div>
    <%@ include file="/WEB-INF/results/footer.jsp"%>
</div>


<script type = "text/javascript">

    var x = document.getElementById("login");
    var y = document.getElementById("register");
    var z = document.getElementById("btn");
    var borderOK = '1px solid green';
    var borderError = '1px solid red';
    var usernameOk = false;
    var passwordOk = false;
    var emailOk = false;
    var nomeOk = false;
    var cognomeOk = false;

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


    function validaUsername() {
        var input = document.getElementById("username_reg");
        if(input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+.+$/)) {

            //Verifica se lo username è disponibile

            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                    usernameOk = true;
                    input.style.borderBottom = borderOK;
                } else {
                    input.style.borderBottom = borderError;
                    usernameOk = false;
                }
                cambiaStatoRegistrami();
            };

            xmlHttpReq.open("GET", "VerificaUsername?username=" + encodeURIComponent(input.value), true);
            xmlHttpReq.send()
        }

        else {
            input.style.borderBottom = borderError;
            usernameOk = false;
            cambiaStatoRegistrami();
        }
    }

    function validaPassword() {

        var inputpw = document.getElementById("password_reg");
        var password = inputpw.value;

        if (password.length >= 8 && password.toUpperCase() != password && password.toLowerCase() != password && password.match(/^[A-Z]+.*[0-9].*/)){
            inputpw.style.borderBottom = borderOK;
            passwordOk = true;
        }

        else {
            inputpw.style.borderBottom = borderError;
            passwordOk = false;
        }

        cambiaStatoRegistrami();
    }

    function validaEmail() {
        var input = document.getElementById("email_reg");

        if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
            input.style.borderBottom = borderOK;
            emailOk = true;
        } else {
            input.style.borderBottom = borderError;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaNome() {
        var input = document.getElementById("nome_reg");
        if (input.value.trim().length > 0
            && input.value.match(/^[ a-zA-Z\u00C0-\u00ff']+$/)) {
            input.style.borderBottom = borderOK;
            nomeOk = true;
        } else {
            input.style.borderBottom = borderError;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaCognome() {
        var input = document.getElementById("cognome_reg");

        if (input.value.trim().length > 0
            && input.value.match(/^[ a-zA-Z\u00C0-\u00ff']+$/)) {
            input.style.borderBottom = borderOK;
            cognomeOk = true;
        } else {
            input.style.borderBottom = borderError;
            cognomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function cambiaStatoRegistrami() {
        if (document.getElementById("agree").checked && usernameOk && passwordOk && nomeOk && emailOk && cognomeOk) {
            document.getElementById('registrami').disabled = false;
        } else {
            document.getElementById('registrami').disabled = true;
        }
    }

</script>

</body>
</html>