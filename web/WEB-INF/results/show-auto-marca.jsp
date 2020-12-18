<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!------ Include the above in your HEAD tag ---------->


<!DOCTYPE html>
<head>
    <title> ${auto[0].casaAuto}</title>
    <link rel = "stylesheet" type="text/css" href = "./css/style_show_auto_marca.css">
    <link rel = "stylesheet" type="text/css" href = "./css/style_nav.css">
    <link rel = "stylesheet" type="text/css" href = "./css/style_footer.css">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script type = "text/javascript" src = "./js/show-auto-marca.js"></script>
</head>
<body style=" background-color: rgba(153,153,153,0.44);">

<%@ include file="/WEB-INF/results/header.jsp"%>


<!--Div contenente i filtri-->
<div class="filter">
    <section class="search-sec">
        <div class="container">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">

                            <div class="col-lg-3 co1l-md-3 col-sm-12 p-0">
                                <select class="form-control search-slt" name="categoria" id="categoria"> <!--Section per
                                 le categorie-->
                                    <option value="error">Seleziona Categoria</option>
                                    <option value="berlinetta">Berlinetta</option>
                                    <option value="coupè">Coupè</option>
                                    <option value="berlina">Berlina</option>
                                    <option value="spider">Spider</option>
                                    <option value="crossover suv">Crossover</option>
                                    <option value="roadster">Roadster</option>
                                    <option value="cabrio">Cabrio</option>
                                </select>
                            </div>


                            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                <select class="form-control search-slt" name="prezzoMin" id="prezzoMin">
                                    <!--Section per determinare il prezzo minimo-->
                                    <option value="error">Prezzo Min</option>
                                    <option value="100000">100000</option>
                                    <option value="120000">120000</option>
                                    <option value="150000">150000</option>
                                    <option value="200000">200000</option>
                                    <option value="250000">250000</option>
                                    <option value="300000">300000</option>
                                </select>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                <select class="form-control search-slt" name="prezzoMax" id="prezzoMax">
                                    <!--Selection per determinare il prezzo massimo-->
                                    <option value="error">Prezzo Max</option>
                                    <option value="200000">200000</option>
                                    <option value="250000">250000</option>
                                    <option value="300000">300000</option>
                                    <option value="350000">350000</option>
                                    <option value="400000">400000</option>
                                    <option value="500000">500000</option>
                                    <option value="600000">600000</option>
                                    <option value="800000">800000</option>
                                    <option value="1000000">1000000</option>
                                </select>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                <select class="form-control search-slt" name="velocitaMin" id="velocitaMin">
                                    <!--Selection per determinare velocità minima-->
                                    <option value="error">Velocità Min</option>
                                    <option value="250">250</option>
                                    <option value="270">270</option>
                                    <option value="290">290</option>
                                    <option value="310">310</option>
                                    <option value="330">330</option>
                                </select>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                <select class="form-control search-slt" name="velocitaMax" id="velocitaMax">
                                    <!--Selection per le velocità massima-->
                                    <option value="error">Velocità Max</option>
                                    <option value="300">300</option>
                                    <option value="350">350</option>
                                    <option value="390">380</option>
                                    <option value="410">400</option>
                                    <option value="430">430</option>
                                </select>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                <select class="form-control search-slt" name="ordinePrezzo" id="ordinePrezzo">
                                    <!--Selection per ordinare le auto per prezzo (cresc, desc)-->
                                    <option value="error">Nessun ordine prezzo</option>
                                    <option value="ordinePrezzoCrescente">Prezzo crescente</option>
                                    <option value="ordinePrezzoDecrescente">Prezzo decresente</option>
                                </select>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                <button type="button" class="btn btn-primary wrn-btn" id= "search"
                                        onclick="Filter('search')">Applica</button> <!--Button per applicare i filtri
                                        selezionati, chiamata a funzione AJAX-->
                            </div>
                            <form action="#" method="get">
                                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                    <button type="button" class="btn btn-primary wrn-btn" id="allcars"
                                            onclick="Filter('allcars')"> All Cars</button> <!--Button per vedere tutte
                                            le auto disponibili, chiamata a funzione AJAX-->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

        </div>
    </section>
</div>


<div class="wrap" id="card_auto"> <!--Div vista delle auto-->

    <c:forEach items="${auto}" var="auto"> <!--Ciclo che crea i div, contenente le informazioni delle auto-->
        <div class="card" >
            <div class="card-image"> <img src="${auto.path}"></div> <!--immagine auto-->
            <div class="card_content">
                <h2 class="card_title">${auto.nome}</h2> <!--Nome auto-->
                <p class="card_text">${auto.prezzo}</p> <!--Prezzo auto, formattato-->
                <p class="card_text">${auto.velMax} km/h</p> <!--Velocità massima auto-->
                <form action = "visualizza-auto">
                    <input type = "hidden" name = "marcaAuto" value = "${auto.casaAuto}">
                    <button class="btn card_btn" name = "${auto.nome}"><i class="fas fa-arrow-right"></i></button>
                    <!--Bottone per andare a vedere l'auto nello specifico-->
                </form>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/results/footer.jsp"%>

</body>

</html>






































