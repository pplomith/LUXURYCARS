<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title> ${auto.nome} </title>
    <meta charset = "UTF-8">
    <meta name="viewport" content = "width = device-width, initial-scale=1">
    <link href = "./css/style_car.css" rel = "stylesheet" type = "text/css">
    <link href = "./css/style_nav.css" rel = "stylesheet" type = "text/css">
    <link href = "./css/style_footer.css" rel = "stylesheet" type = "text/css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>



<%@ include file="/WEB-INF/results/alertBox.jsp"%>

<div class = "hero">

    <%@ include file="/WEB-INF/results/header.jsp"%>

    <div class = "first">

        <h3>${auto.nome} </h3>

        <hr>
        <div id="photo_first" class="slideshow-container"> <!--Div contente le immagini dell'auto, mostrate un alla volta, dopo un certo tempo-->
            <div id="slide-show" class="mySlides1">
                <img class="img" src = "${pathFront}">
            </div>

            <div id="slide-show1" class="mySlides1">
                <img class="img" src = "${pathEngine}">
            </div>

            <div id="slide-show2" class="mySlides1">
                <img class="img" src = "${pathAnother}">
            </div>
            <div id="slide-show3" class="mySlides1">
                <img class="img" src = "${pathSide}">
            </div>

        </div>

        <div id = "div_buy" class = "div_buy">

            <input id = "nomeAutoPref" type = "hidden" name = "nomeAuto" value = "${auto.nome}">
            <input id = "casaAutoPref" type = "hidden" name = "casaAuto" value = "${auto.casaAuto}">
            <button class = "buy_button" id = "pref" name = "preferito" onclick = "aggiungiPreferito()"> <!--bottone per aggiungere l'auto ai preferiti-->
                <i class = "fas fa-heart"></i>
            </button>

            <input id = "nomeAutoCarrello" type = "hidden" name = "nomeAuto" value = "${auto.nome}">
            <input id = "casaAutoCarrello" type = "hidden" name = "casaAuto" value = "${auto.casaAuto}">
            <input id = "prezzoAutoCarrello" type = "hidden" name = "prezzoAuto" value = "${auto.prezzo}">
            <button class = "buy_button" id = "carr" name = "carrello" onclick = "aggiungiCarrello()"> <!--bottone per aggiungere l'auto al carrello-->
                <i class = "fas fa-shopping-cart"></i>
            </button>

            <form action = "ServletAcquisto" method = "get">
                <input type = "hidden" name = "nomeAuto" value = "${auto.nome}">
                <input type = "hidden" name = "casaAuto" value = "${auto.casaAuto}">
                <button class = "buy_button" id = "buy" name = "acquisto"> <!--bottone per andare ad acquistare l'auto direttamente-->
                    <i class = "fas fa-money-bill-alt" id = "shop_btn"></i>
                </button>
            </form>

        </div>
    </div>


    <div id = "specifiche" class = "specification">

        <h3> specifiche </h3>

        <hr>

        <table>
            <thead>
            <tr>
                <th scope="col">MARCA</th>
                <th scope="col">CATEGORIA</th>
                <th scope="col">ALIMENTAZIONE</th>
                <th scope="col">TRASMISSIONE</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td data-label="MARCA">${auto.casaAuto}</td>
                <td data-label="CATEGORIA">${auto.categoria}</td>
                <td data-label="ALIMENTAZIONE">${auto.alimentazione}</td>
                <td data-label="TRASMISSIONE">${auto.trasmissione}</td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th scope="col">LUNGHEZZA</th>
                <th scope="col">LARGHEZZA</th>
                <th scope="col">ALTEZZA</th>
                <th scope="col">PESO</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td scope="row" data-label="LUNGHEZZA">${auto.lunghezza} cm</td>
                <td data-label="LARGHEZZA">${auto.larghezza} cm</td>
                <td data-label="ALTEZZA">${auto.altezza} cm</td>
                <td data-label="PESO">${auto.peso} kg</td>
            </tr>
            </tbody>

            <thead>
            <tr>
                <th scope="col">RAPPORTI</th>
                <th scope="col">CILINDRI</th>
                <th scope="col">CILINDRATA</th>
                <th scope="col">POTENZA</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td scope="row" data-label="RAPPORTI">${auto.rapporti}</td>
                <td data-label="CILINDRI">${auto.cilindri}</td>
                <td data-label="CILINDRATA">${auto.cilindrata} cm3</td>
                <td data-label="POTENZA">${auto.kW} kW (${auto.potenza} CV)</td>
            </tr>
            </tbody>

            <thead>
            <tr>
                <th scope="col">TRAZIONE</th>
                <th scope="col">VELOCITÀ MAX</th>
                <th scope="col">ACCELERAZIONE 0-100 kM/H</th>
                <th scope="col">EMISSIONE CO2</th>

            </thead>
            <tbody>
            <tr>
                <td scope="row" data-label="TRAZIONE">/</td>
                <td data-label="VELOCITÀ MAX">${auto.velMax} km/h</td>
                <td data-label="ACCELERAZIONE 0-100 KM/H">${auto.accelerazione} s</td>
                <td data-label="EMISSIONI CO2">${auto.emissioneCO2} g/km</td>
            </tbody>

        </table>
    </div>

    <%@ include file="/WEB-INF/results/footer.jsp"%>
</div>

<script>

    var slideIndex = 0;
    showSlides();

    // funzione per cambiare le immagini delle auto
    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides1");
        for (i = 0; i < slides.length; i++)
            slides[i].style.display = "none";
        slideIndex++;

        if (slideIndex > slides.length)
            slideIndex = 1;

        slides[slideIndex-1].style.display = "block";
        setTimeout(showSlides, 2000); // Cambia immagine ogni 2 seconds
    }
    //funzione per aggiungere ai preferiti
    function aggiungiPreferito() {

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText == "<ok/>")
                    document.getElementById("popup").innerHTML = "<br><br>Articolo inserito nei preferiti.<br><br><br>";
                else if (this.responseText == "<no/>")
                    document.getElementById("popup").innerHTML = "<br><br>Articolo già inserito nei preferiti.<br><br><br>";
                else if (this.responseText == "<okAccesso/>")
                    document.getElementById("popup").innerHTML = "<br><br>Esegui l'accesso per inserire l'elemento nei preferiti.<br><br><br>";

                document.getElementById('id01').style.display='block';
            }
        };

        var nomeAuto = document.getElementById("nomeAutoPref").value;
        var casaAuto = document.getElementById("casaAutoPref").value;
        xhttp.open("GET", "ServletPreferito?nomeAuto=" + nomeAuto + "&casaAuto=" + casaAuto, true);
        xhttp.send();
    }
    //funzione per aggiungere l'elemento nel carrello
    function aggiungiCarrello() {

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText == "<ok/>")
                    document.getElementById("popup").innerHTML = "<br><br>Articolo inserito nel carrello.<br><br><br>";

                else if (this.responseText == "<okAccesso/>")
                    document.getElementById("popup").innerHTML =
                        "<br><br>Esegui l'accesso per inserire l'elemento nel carrello.<br><br><br>";
            }
            document.getElementById('id01').style.display='block';
        };

        var nomeAuto = document.getElementById("nomeAutoCarrello").value;
        var casaAuto = document.getElementById("casaAutoCarrello").value;
        var prezzoAuto = document.getElementById("prezzoAutoCarrello").value;
        xhttp.open("GET", "ServletCarrello?nomeAuto=" + nomeAuto + "&casaAuto=" + casaAuto +
            "&prezzoAuto=" + prezzoAuto, true);
        xhttp.send();
    }

    // Prende il pop-up
    var modal = document.getElementById('id01');

    // Quando l'utente clicca in una parte al di fuori del popup, si chiude
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

</script>

</body>
</html>