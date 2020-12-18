<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
    <title>Amministratore</title>
    <link href = "./css/style_amministratore.css" rel = "stylesheet" type = "text/css">
    <link rel = "stylesheet" type = "text/css" href="./css/style_footer.css">
    <link href = "./css/style_nav.css" rel = "stylesheet" type = "text/css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src = "./js/amministratore.js"></script>
</head>
<body>

<div class="hero">
    <div class="row">

        <div class="col3">
            <div class="list-group">
                <a href="#" class="list-group-item" id="utenti_view">Utenti</a>
                <a href="#" class="list-group-item" id="auto_view">Auto</a>
                <a href="#" class="list-group-item" id="ordini_view">Ordini</a>
                <a href="#" class="list-group-item" id="impostazioni_view">Impostazioni</a>
            </div>
        </div>

        <div class="col9">
            <div class="card">

                <div class="card-body" id="utenti"> <!-- div utenti presenti nel DB-->
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Utenti</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 100%">

                                <thead>
                                <tr>
                                    <th scope="col">IdUtente</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Cognome</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_utenti">
                                <c:forEach items="${sessionScope.listaClienti}" var = "cliente">
                                    <tr id = "${cliente.id}">
                                        <th scope = "row"> ${cliente.id} </th>
                                        <td data-title = "Nome"> ${cliente.nome} </td>
                                        <td data-title = "Cognome"> ${cliente.cognome} </td>
                                        <td data-title = "Remove">
                                            <button id = "remove" name = "${cliente.id}"
                                                    onclick = "rimuoviCliente(name)">  <!-- button per rimuovere utente dal DB-->
                                                Remove
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="card-body" id="auto" style="display: none;"> <!-- div auto presenti nel DB-->
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Auto</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" id="table_auto" style="width: 100%">
                                <thead>
                                <tr>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Marca</th>
                                    <th scope="col">Prezzo</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sessionScope.listaAuto}" var = "auto">
                                    <tr id = "${auto.nome}_${auto.casaAuto}">
                                        <th scope="row"> ${auto.nome} </th>
                                        <td data-title="Marca"> ${auto.casaAuto} </td>
                                        <td data-title="Prezzo"> ${auto.prezzo} </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>


                <div class="card-body" id="ordini" style="display: none;"> <!-- div ordini presenti nel DB-->
                    <div class="row1">
                        <div class="col-md-12">
                            <h4><span><i class="fas fa-database"></i></span> Ordini</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1" id="ordini_row">
                        <div class="input_cliente">
                            <br>
                            <label class="label">
                                <span>IdCliente</span>
                                <input type="text" name = "idcliente" id = "idCliente"> <!--cerca ordini per un
                                determinato cliente, tramite id -->
                            </label>
                            <input type="submit" name="cerca" id="cerca" value="Cerca">
                            <br>
                        </div>
                        <div class="data">
                            <table class="responsive-table" id="responsive-table-ordini" style="width: 100%;">

                            </table>


                        </div>
                    </div>
                </div>

                <div class="card-body" id="impostazioni" style="display: none"> <!-- div impostazioni-->
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
                                    <br>
                                    <form action = "LogOutAmministratore" method = "get">
                                        <input name="submit_esci" type="submit" class="btn_impostazioni" id = "log_out"
                                               value="Esci"> <!-- button per uscire dall'area dell'amministratore-->
                                    </form>
                                    <br>
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

<script>

    // Script per la visualizzazione degli ordini dell'utente
    $(document).ready(function() {
        $("#cerca").click(function() {
            var id = $("#idCliente").val();
            $.getJSON("OrdiniCliente", {idCliente: id}, function(data) {
                visualizzaOrdini(data);
            });
        });
    });

    function visualizzaOrdini(data) {

        if (data.length == 0)
            $("#responsive-table-ordini").css("visibility", "hidden");

        else {
            var elements = "<thead> <tr> <th scope='col'>Ordine N°</th>" +
                "<th scope='col'>IdUtente</th>" +
                "<th scope='col'>Nome auto</th>" +
                "<th scope='col'>Prezzo</th>" +
                "</tr></thead>" +
                "<tbody id='table_ordini'>";
            for (var i = 0; i < data.length; i++) {
                elements += "<tr>" +
                    "<th scope='row'>" + data[i].idOrdine + "</th>" +
                    "<td data-title = 'IdCustomer'>" + data[i].idCustomer + "</td>" +
                    "<td data-title = 'Nome auto'>" + data[i].nomeAuto + "</td>" +
                    "<td data-title = 'Prezzo'>" + data[i].prezzo + "</td>" +
                    "</tr>";
            }

            elements += "</tbody>";
            $("#responsive-table-ordini").html(elements);
            $("#responsive-table-ordini").css("visibility", "visible");
        }
    }


    // Script per la rimozione di un utente
    function rimuoviCliente(id) {

        $(document).ready(function () {
            $.getJSON("RimuoviCliente", {idCliente: id}, function (data) {
                myFunction(data);
            });
        });
    }
    
    function myFunction(data) {

        var table = null;
        for (var i = 0; i < data.length; i++) {
            table += "<tr id = '" + data[i].idCustomer + "'>" +
                        "<th scope = 'row'>" + data[i].idCustomer + "</th>" +
                        "<td data-title = 'Nome'>" + data[i].nome + "</td>" +
                        "<td data-title = 'Cognome'>" + data[i].cognome + "</td>" +
                        "<td data-title = 'Remove'>" +
                            "<button id = 'remove' name = '" + data[i].idCustomer +"' onclick = 'rimuoviCliente(name)'>"
                                + "Remove" +
                            "</button>" +
                        "</td>" +
                    "</tr>";
        }

        $("#tbody_utenti").html(table);
    }

    /* jquery per cambiare l'elemento da mostrare (utenti in DB, auto in DB, ordini effettuati, impostazioni */
    $(document).ready(function(){
        $("#utenti_view").click(function(){
            $("#utenti").show();
            $("#auto").hide();
            $("#ordini").hide();
            $("#impostazioni").hide();
        });
        $("#auto_view").click(function(){
            $("#utenti").hide();
            $("#auto").show();
            $("#ordini").hide();
            $("#impostazioni").hide();
        });
        $("#ordini_view").click(function(){
            $("#utenti").hide();
            $("#auto").hide();
            $("#ordini").show();
            $("#impostazioni").hide();
        });
        $("#impostazioni_view").click(function(){
            $("#utenti").hide();
            $("#auto").hide();
            $("#ordini").hide();
            $("#impostazioni").show();
        });
    });
</script>

</body>
</html>