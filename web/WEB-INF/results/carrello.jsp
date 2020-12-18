<%@ page import="model.ProdottoCarrello" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
    <title>Carrello</title>
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <link rel = "stylesheet" type = "text/css" href = "./css/style_nav.css">
    <link rel = "stylesheet" type = "text/css" href = "./css/style_footer.css">
    <link rel="stylesheet" type = "text/css" href="./css/style_carrello.css">
</head>
<body>

<%@include file="/WEB-INF/results/alertBox.jsp"%>

<%@ include file="/WEB-INF/results/header.jsp"%>

<div class="wrap" id="card_auto">
    <h1>CARRELLO</h1>

    <div class='order-info'>
        <div id="carrello" class="carrello">
            <div class='line'></div>
            <c:choose>
            <c:when test="${empty carrello}"> <!-- verifica se ci sono prodotti oppure no -->

                    <br><br><br>
                    <h1>Nessun articolo nel carrello.</h1>
                    <br><br><br>
                    <div class='line'></div>

            <div class="button">
                <form action="index.html"><button id="btn_back1" class="btn_back"> INDIETRO </button></form>
                    <!-- form per tornare alla vista delle auto -->
                <form action="OrdinaCarrello"><button class="btn" disabled> ORDINA </button></form>
            </div>
                    <!-- form per andare al check out -->
        </div>
        </c:when>
        <c:otherwise>
        <input type="hidden" id="carrello_hidden" value="${carrello}">
        <c:forEach items="${carrello}" var="auto">
            <table class='order-table'>
                <tbody>
                <tr>
                    <td><img src="${auto.path}" class='full-width'></td>
                    <td>
                        <br>

                        <span class='thin'> ${auto.nomeAuto} </span>

                        <input id="${auto.nomeAuto}" type="hidden" value = "${auto.casaAuto}">
                        <br>${auto.casaAuto}<br>

                        <span class='thin small'>

                            <input type="number" name="${auto.nomeAuto}" class = "quantity" value = ${auto.quantita}
                                    min="1" max = "100" onchange = "updateQuantity(value,name)">
                                <!-- onchange deve andare a cambiare il prezzo totale e il prezzo dell'oggetto corrente -->

                            <button class="btn_remove" name="${auto.nomeAuto}" onclick = "removeCar(name)">
                                Remove
                            </button>

                            <br><br>
                        </span>
                            <!-- form per eliminare l'oggetto, con ajax -->
                    </td>
                </tr>
                <tr>
                    <td> <div class='price'>${auto.prezzo}</div> </td>
                </tr>
                </tbody>
            </table>

            <div class='line'></div>
        </c:forEach>

        <div class='total'>
            <span style='float:left;'>
                TOTAL
            </span>
            <span style='float:right; text-align:right;'>
                    ${prezzoTotale}
            </span>
        </div>

        <!-- div contenete i due button-->
        <div class="button">
                <form action="index.html"><button id="btn_back" class="btn_back"> INDIETRO </button></form> <!-- form per tornare alla vista delle auto -->
                <form action = "OrdinaCarrello"><button class="btn"> ORDINA </button></form> </div><!-- form per andare al check out -->
        </div>
    </c:otherwise>
    </c:choose>


    </div>
</div>

<%@ include file="/WEB-INF/results/footer.jsp"%>

<script>

    // Aggiorna la quantità
    function updateQuantity(value,id) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                myFunction(this);
            }
        };
        var casaAuto = document.getElementById(id).value;
        var remove = "update"; //varibile per dire che si tratti di un aggiornamento della quantita

        // Richiesta asincrona alla servlet ModificaCarrello
        xhttp.open("GET","ModificaCarrello?nomeAuto="+id+"&casaAuto="+casaAuto+"&remove="+remove+"&quantita="+value, true);
        xhttp.send();
    }

    // Funzione che rimuove l'auto dal carrello
    function removeCar(id) {

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                myFunction(this);
            }
        };

        var casaAuto = document.getElementById(id).value;
        var remove = 1; //dire che si tratti della rimozione dell'auto dal carrello

        // Richiesta asincrona alla servlet ModificaCarrello
        xhttp.open("GET","ModificaCarrello?nomeAuto="+id+"&casaAuto="+casaAuto+"&remove="+remove, true);
        xhttp.send();
    }

    //funzione per andare a modifica gli elementi del carrello; con costruzione div
    function myFunction(xml) {
        var i;
        var xmlDoc = xml.responseXML;
        var div="<div class='line'></div>";
        var x = xmlDoc.getElementsByTagName("item");
        if(x.length==0 || x.length==1) //se è uguale ad 1 significa che nel documento c'è solo il prezzo totale;// (vedi ModificaCarrello: 71);
             div +="<br><br><br><h1>Nessun articolo nel carrello.</h1><br><br><br><div class='line'></div> " +
                 "<div class='button'> <form action='index.html'>" +
                 "<button id='btn_back1' class='btn_back'> INDIETRO " +
                 "</button></form> <form action='OrdinaCarrello'>" +
                 "<button class='btn' disabled> ORDINA </button>" +
                 "</form></div>";

        else{
            for(i=0 ; i < x.length - 1;i++){
                div += "<table class='order-table'>  <tbody> <tr> <td>" +
                        "<img src='" +
                        x[i].getElementsByTagName("path")[0].childNodes[0].nodeValue +
                        "' class='full-width'></td><td><br>"+
                        "<span class='thin'>" +
                        x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue +
                        "</span> <input id='"+
                        x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue +
                        "' type='hidden' value='" +
                        x[i].getElementsByTagName("casaAuto")[0].childNodes[0].nodeValue +
                        "'><br>" +
                       x[i].getElementsByTagName("casaAuto")[0].childNodes[0].nodeValue +
                        "<br><span class='thin small'><input type='number' name='"+
                       x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue
                        +"' class='quantity' value=" +
                        x[i].getElementsByTagName("quantita")[0].childNodes[0].nodeValue +
                        " min='1' max='100' onchange='updateQuantity(value,name)'>"+
                        "<button class='btn_remove' name='"+
                          x[i].getElementsByTagName("nomeAuto")[0].childNodes[0].nodeValue +
                        "' onclick='removeCar(name)'> Remove </button>" +
                        "<br><br> </span></td></tr><tr><td><div class='price'>"+
                         x[i].getElementsByTagName("prezzo")[0].childNodes[0].nodeValue +
                        "</div></td></tr></tbody></table><div class='line'></div>";

            }
            div += "<div class='total'> <span style='float:left;'> TOTAL </span> <span style='float:right; text-align: right;'>"+ x[(x.length-1)].getElementsByTagName("prezzoTotale")[0].childNodes[0].nodeValue +"</span></div>" +
                    "<div class='button'> <form action='index.html'><button id='btn_back1' class='btn_back'> INDIETRO </button></form> <form action='OrdinaCarrello'><button class='btn'> ORDINA </button></form></div>";


        }

        document.getElementById("carrello").innerHTML = div;
    }

</script>

</body>
</html>



















