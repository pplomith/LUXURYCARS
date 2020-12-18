<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <title>Profilo</title>
    <link href = "./css/style_profile.css" rel = "stylesheet" type = "text/css">
    <link rel = "stylesheet" type = "text/css" href="./css/style_footer.css">
    <link href = "./css/style_nav.css" rel = "stylesheet" type = "text/css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        /*jQuery per cambiare l'elemento da mostrare (Dati utente, Ordini effettuati, Auto preferite, Impostazioni)*/
        $(document).ready(function(){
            $("#dati_view").click(function(){
                $("#dati").show();
                $("#ordini").hide();
                $("#preferiti").hide();
                $("#impostazioni").hide();
            });
            $("#ordini_view").click(function(){
                $("#dati").hide();
                $("#ordini").show();
                $("#preferiti").hide();
                $("#impostazioni").hide();
            });
            $("#preferiti_view").click(function(){
                $("#dati").hide();
                $("#ordini").hide();
                $("#preferiti").show();
                $("#impostazioni").hide();
            });
            $("#impostazioni_view").click(function(){
                $("#dati").hide();
                $("#ordini").hide();
                $("#preferiti").hide();
                $("#impostazioni").show();
            });
        });
    </script>
</head>
<body>

<%@ include file = "/WEB-INF/results/header.jsp"%>

<div class="hero">
    <div class="row">

        <div class="col3"> <!--Div contenete il menu-->
            <div class="list-group">
                <a href="#" class="list-group-item" id="dati_view">Dati</a>
                <a href="#" class="list-group-item" id="ordini_view">Ordini</a>
                <a href="#" class="list-group-item" id="preferiti_view">Preferiti</a>
                <a href="#" class="list-group-item" id="impostazioni_view">Impostazioni</a>
            </div>
        </div>

        <div class="col9">
            <div class="card">
                <div class="card-body" id="dati" > <!--Div contenente dati utente-->
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-user-alt"></i></span> Profilo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">

                            <form action="modifica-dati"> <!-- form per andare a modificare i dati utente-->

                                <label class="label">
                                    <span class="fname">Nome</span>
                                    <input type="text" name="fname" id="nome" value="${customer.nome}"
                                           oninput="validaNome()">
                                </label>
                                <label class="label">
                                    <span class="lname">Cognome</span>
                                    <input type="text" name="lname" id="cognome" value="${customer.cognome}"
                                           oninput="validaCognome()">
                                </label>
                                <label class="label">
                                    <span class="username">Username</span>
                                    <input type="hidden" id="username_db" value="${customer.username}">
                                    <input type="text" name="username" id="username" value="${customer.username}"
                                           oninput="validaUsername('username_db')">
                                </label>

                                <label class="label">
                                    <span>Email</span>
                                    <input type="email" name="email" id="email" value="${customer.email}"
                                           oninput="validaEmail()">
                                </label>
                                <label class="label">
                                    <span>Vecchia password</span>
                                    <input type="password" name="oldpw" id="oldpw" placeholder="Inserisci password corrente">
                                </label>
                                <label class="label">
                                    <span>Nuova password</span>
                                    <input type="password" name="newpw" id="newpw" placeholder="Inserisci nuova password"
                                           oninput="validaPassword()">
                                </label>
                                <input type="submit" name="modifica_dati" id="modifica_dati" value="Modifica profilo">
                            </form>
                        </div>
                    </div>

                </div>

                <div class="card-body" id="ordini" style="display: none;" > <!--Div contenente ordini utente-->
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-money-check-alt"></i></span> Ordini</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="col-md-12">

                            <div class="wrap" id="card_auto">
                                <div class='order-info'>
                                    <div id="ordini1" class="ordini">
                                        <div class='line'></div>
                                        <c:choose>
                                            <c:when test="${empty acquisti}"> <!--controllo se ci sono ordini oppure no-->
                                                <br><br><br>
                                                <h1>Nessun ordine effettuato.</h1>
                                                <br><br><br>
                                                 <div class='line'></div>
                                            </c:when>
                                            <c:otherwise>

                                                <c:forEach items="${acquisti}" var="auto">
                                                    <table class='order-table'>
                                                        <tbody>
                                                        <tr>
                                                            <td><img src="${auto.path}" class='full-width'></td>
                                                            <td>
                                                        <br>
                                                        Order N°: ${auto.idAcquisto}<br>
                                                        <span class='thin'> ${auto.nomeAuto} x ${auto.quantita} (Qty)</span>
                                                        <input id="${auto.nomeAuto}" type="hidden" value = "${auto.casaAuto}">
                                                        <br>${auto.casaAuto}<br>
                                                        ${auto.data}
                                                        <span class='thin small'>
                                                    <br><br>
                                                  </span>

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td><div class='price'>${auto.prezzoTotaleString}</div></td>
                                                </tr>
                                                </tbody>
                                            </table>

                                            <div class='line'></div>
                                        </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-body" id="preferiti" style="display: none;"> <!--Div contenente auto preferite utente-->
                    <div class="row1">
                        <div class="col-md-12">
                            <h4><span><i class="fas fa-star"></i></span> Preferiti</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="col-md-12">

                            <div class="wrap" id="card_auto1">
                                <div class='order-info'>
                                   <div id="preferiti1" class="preferiti">
                                        <div class='line'></div>
                                        <c:choose>
                                            <c:when test="${empty preferiti}"> <!--Controllo se l'utente ha delle auto preferite-->
                                                <br><br><br>
                                                <h1>Nessun auto preferita.</h1>
                                                <br><br><br>
                                                <div class='line'></div>
                                            </c:when>
                                            <c:otherwise>
                                        <c:forEach items="${preferiti}" var="auto">
                                            <table class='order-table'>
                                                <tbody>
                                                <tr>
                                                    <td><img src="${auto.path}" class='full-width'></td>
                                                    <td>
                                                        <br>

                                                        <span class='thin'> ${auto.nomeAuto} </span><span class="remove">
                                                        <a class="btn_remove" name="${auto.nomeAuto}"
                                                           onclick = "removeCar(name)"> <!--Button per rimuovere l'auto dai preferiti-->
                                                            <i class="fas fa-times"></i>
                                                        </a></span>
                                                        <input id="${auto.nomeAuto}" type="hidden" value = "${auto.casaAuto}">
                                                        <br>${auto.casaAuto}<br>
                                                        <span class='thin small'>
                                                    <br><br>
                                                  </span>

                                                    </td>
                                                </tr>

                                                <tr>

                                                </tr>
                                                </tbody>
                                            </table>

                                            <div class='line'></div>
                                        </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card-body" id="impostazioni" style="display: none"> <!--Div contenente le impostazioni per l'utente-->
                    <div class="row1">
                        <div class="col-md-12">
                            <h4><span><i class="fas fa-cog"></i></span> Impostazioni</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="col-md-12">

                            <div class="data">
                                <div class="offset-4 col-8">
                                    <br>
                                    <form action="login-registration">
                                        <input name="submit_esci" type="submit" class="btn_impostazioni" value="Esci">
                                    </form> <!--form per uscire dall'account-->
                                    <br>
                                </div>
                            </div>
                            <div class="data">
                                <div class="offset-4 col-8">

                                    <form action="login-registration">
                                        <input name="submit_delete" type="submit" class="btn_impostazioni btn_delete"
                                               value="Elimina profilo">
                                    </form> <!--form per eliminare l'account-->
                                    <br>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/results/footer.jsp"%>

<script>

    //funzioni per andare a validare gli input inseriti nella modifica dei dati
    var borderOK = '1px solid green';
    var borderError = '1px solid red';

    //controllo username
    function validaUsername(value) {

        var input = document.getElementById("username");
        var username_db = document.getElementById(value).value;

        if (input.value == username_db) {
            input.style.borderBottom = borderOK;
            document.getElementById('modifica_dati').disabled = false;
        }

        else if(input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+.+$/)) {

            //Verifica se lo username è disponibile del DB

            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                    input.style.borderBottom = borderOK;
                    document.getElementById('modifica_dati').disabled = false;
                } else {
                    input.style.borderBottom = borderError;
                    document.getElementById('modifica_dati').disabled = true;
                }
            };

            xmlHttpReq.open("GET", "VerificaUsername?username=" + encodeURIComponent(input.value), true);
            xmlHttpReq.send()
        }

        else {

            input.style.borderBottom = borderError;
            document.getElementById('modifica_dati').disabled = false;
        }
    }

    // controllo password
    function validaPassword() {

        var inputpw = document.getElementById("newpw");
        var password = inputpw.value;

        if (password.length >= 8 && password.toUpperCase() != password && password.toLowerCase() != password &&
            password.match(/^[A-Z]+.*[0-9].*/)){

            inputpw.style.borderBottom = borderOK;
            document.getElementById('modifica_dati').disabled = false;
        }

        else {
            inputpw.style.borderBottom = borderError;
            document.getElementById('modifica_dati').disabled = true;
        }
    }

    // controllo email
    function validaEmail() {

        var input = document.getElementById("email");

        if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
            input.style.borderBottom = borderOK;
            document.getElementById('modifica_dati').disabled = false;
        }

        else {

            input.style.borderBottom = borderError;
            document.getElementById('modifica_dati').disabled = true;
        }
    }

    // controllo nome
    function validaNome() {

        var input = document.getElementById("nome");

        if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff']+$/)) {
            input.style.borderBottom = borderOK;
            document.getElementById('modifica_dati').disabled = false;
        }

        else {
            input.style.borderBottom = borderError;
            document.getElementById('modifica_dati').disabled = true;
        }
    }

    // controllo cognome
    function validaCognome() {

        var input = document.getElementById("cognome");

        if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff']+$/)) {
            input.style.borderBottom = borderOK;
            document.getElementById('modifica_dati').disabled = false;
        }

        else {

            input.style.borderBottom = borderError;
            document.getElementById('modifica_dati').disabled = true;
        }
    }

    //funzione per andare a rimuovere l'auto dai preferiti, tramire AJAX
    function removeCar(id) {

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                myFunction(this);
            }
        };

        var casaAuto = document.getElementById(id).value;
        var remove = 1;

        console.log(id+" "+casaAuto+" "+remove);
        xhttp.open("GET","modifica-preferiti?nomeAuto="+id+"&casaAuto="+casaAuto+"&remove="+remove, true);
        xhttp.send();
    }


    //funzione per andare a ricostruire i div aggiornati
    function myFunction(xml) {

        var i;
        var xmlDoc = xml.responseXML;
        var div="<div class='line'></div>";
        var x = xmlDoc.getElementsByTagName("item");

        if(x.length==0)
            div +="<br><br><br><h1> Nessun auto preferita. </h1><br><br><br><div class='line'></div>";

        else{

            for(i = 0; i < x.length; i++){
                div += "<table class='order-table'>  <tbody> <tr> <td>" +
                    "<img src='" +
                    x[i].getElementsByTagName("path")[0].childNodes[0].nodeValue +
                    "' class='full-width'></td><td><br>"+
                    "<span class='thin'>" +
                    x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue +
                    "</span><span class='remove'><a class='btn_remove' name='"+
                    x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue
                    +"' onclick='removeCar(name)'> <i class='fas fa-times'></i></a></span> <input id='"+
                    x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue +
                    "' type='hidden' value='" +
                    x[i].getElementsByTagName("casaAuto")[0].childNodes[0].nodeValue +
                    "'><br>" +
                    x[i].getElementsByTagName("casaAuto")[0].childNodes[0].nodeValue +
                    "<br><span class='thin small'><br><br></span></td></tr><tr></tr></tbody></table><div class='line'></div>";
            }
        }

        document.getElementById("preferiti1").innerHTML = div;
    }

</script>
</body>
</html>