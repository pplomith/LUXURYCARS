<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
    <title> Check Out</title>
    <meta charset = "UTF-8">
    <meta name = "viewport" content = "width = device-width, initial-scale=1">
    <link href = "./css/style_nav.css" rel = "stylesheet" type = "text/css">
    <link href = "./css/style_checkout.css" rel = "stylesheet" type = "text/css">
    <link href = "./css/style_footer.css" rel = "stylesheet" type = "text/css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel = "stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/results/header.jsp"%>

<!-- div contiene tutto il box sia per inserire dati che per resoconto ordini -->
<div class="container_order">
    <div class="title">
        <h2>EFFETTUA L'ORDINE</h2>
    </div>
    <div class="d-flex">
        <div class="data">  <!--Div per inserire i dati-->
            <form action = "FinalizzaOrder" method="POST"> <!--form per finalizzare l'acquisto-->
                <h2>DATI</h2>

                <label class="label">
                    <span class = "fname"> Nome <span class = "required">*</span></span>
                    <input id = "nome" type = "text" name = "nome">
                </label>

                <label class="label">
                    <span class = "lname"> Cognome <span class = "required">*</span></span>
                    <input id = "cognome" type = "text" name = "cognome">
                </label>

                <label class="label">
                    <span> Nazione <span id = "country_lbl" class = "required">*</span></span>
                    <select id = "nazione" name = "nazione">
                        <option value="select">Seleziona una nazione...</option>
                        <option value="AFG">Afghanistan</option>
                        <option value="ALA">Åland Islands</option>
                        <option value="ALB">Albania</option>
                        <option value="DZA">Algeria</option>
                        <option value="ASM">American Samoa</option>
                        <option value="AND">Andorra</option>
                        <option value="AGO">Angola</option>
                        <option value="AIA">Anguilla</option>
                        <option value="ATA">Antarctica</option>
                        <option value="ATG">Antigua and Barbuda</option>
                        <option value="ARG">Argentina</option>
                        <option value="ARM">Armenia</option>
                        <option value="ABW">Aruba</option>
                        <option value="AUS">Australia</option>
                        <option value="AUT">Austria</option>
                        <option value="AZE">Azerbaijan</option>
                        <option value="BHS">Bahamas</option>
                        <option value="BHR">Bahrain</option>
                        <option value="BGD">Bangladesh</option>
                        <option value="BRB">Barbados</option>
                        <option value="BLR">Belarus</option>
                        <option value="BEL">Belgium</option>
                        <option value="BLZ">Belize</option>
                        <option value="BEN">Benin</option>
                        <option value="BMU">Bermuda</option>
                        <option value="BTN">Bhutan</option>
                        <option value="BOL">Bolivia, Plurinational State of</option>
                        <option value="BES">Bonaire, Sint Eustatius and Saba</option>
                        <option value="BIH">Bosnia and Herzegovina</option>
                        <option value="BWA">Botswana</option>
                        <option value="BVT">Bouvet Island</option>
                        <option value="BRA">Brazil</option>
                        <option value="IOT">British Indian Ocean Territory</option>
                        <option value="BRN">Brunei Darussalam</option>
                        <option value="BGR">Bulgaria</option>
                        <option value="BFA">Burkina Faso</option>
                        <option value="BDI">Burundi</option>
                        <option value="KHM">Cambodia</option>
                        <option value="CMR">Cameroon</option>
                        <option value="CAN">Canada</option>
                        <option value="CPV">Cape Verde</option>
                        <option value="CYM">Cayman Islands</option>
                        <option value="CAF">Central African Republic</option>
                        <option value="TCD">Chad</option>
                        <option value="CHL">Chile</option>
                        <option value="CHN">China</option>
                        <option value="CXR">Christmas Island</option>
                        <option value="CCK">Cocos (Keeling) Islands</option>
                        <option value="COL">Colombia</option>
                        <option value="COM">Comoros</option>
                        <option value="COG">Congo</option>
                        <option value="COD">Congo, the Democratic Republic of the</option>
                        <option value="COK">Cook Islands</option>
                        <option value="CRI">Costa Rica</option>
                        <option value="CIV">Côte d'Ivoire</option>
                        <option value="HRV">Croatia</option>
                        <option value="CUB">Cuba</option>
                        <option value="CUW">Curaçao</option>
                        <option value="CYP">Cyprus</option>
                        <option value="CZE">Czech Republic</option>
                        <option value="DNK">Denmark</option>
                        <option value="DJI">Djibouti</option>
                        <option value="DMA">Dominica</option>
                        <option value="DOM">Dominican Republic</option>
                        <option value="ECU">Ecuador</option>
                        <option value="EGY">Egypt</option>
                        <option value="SLV">El Salvador</option>
                        <option value="GNQ">Equatorial Guinea</option>
                        <option value="ERI">Eritrea</option>
                        <option value="EST">Estonia</option>
                        <option value="ETH">Ethiopia</option>
                        <option value="FLK">Falkland Islands (Malvinas)</option>
                        <option value="FRO">Faroe Islands</option>
                        <option value="FJI">Fiji</option>
                        <option value="FIN">Finland</option>
                        <option value="FRA">France</option>
                        <option value="GUF">French Guiana</option>
                        <option value="PYF">French Polynesia</option>
                        <option value="ATF">French Southern Territories</option>
                        <option value="GAB">Gabon</option>
                        <option value="GMB">Gambia</option>
                        <option value="GEO">Georgia</option>
                        <option value="DEU">Germany</option>
                        <option value="GHA">Ghana</option>
                        <option value="GIB">Gibraltar</option>
                        <option value="GRC">Greece</option>
                        <option value="GRL">Greenland</option>
                        <option value="GRD">Grenada</option>
                        <option value="GLP">Guadeloupe</option>
                        <option value="GUM">Guam</option>
                        <option value="GTM">Guatemala</option>
                        <option value="GGY">Guernsey</option>
                        <option value="GIN">Guinea</option>
                        <option value="GNB">Guinea-Bissau</option>
                        <option value="GUY">Guyana</option>
                        <option value="HTI">Haiti</option>
                        <option value="HMD">Heard Island and McDonald Islands</option>
                        <option value="VAT">Holy See (Vatican City State)</option>
                        <option value="HND">Honduras</option>
                        <option value="HKG">Hong Kong</option>
                        <option value="HUN">Hungary</option>
                        <option value="ISL">Iceland</option>
                        <option value="IND">India</option>
                        <option value="IDN">Indonesia</option>
                        <option value="IRN">Iran, Islamic Republic of</option>
                        <option value="IRQ">Iraq</option>
                        <option value="IRL">Ireland</option>
                        <option value="IMN">Isle of Man</option>
                        <option value="ISR">Israel</option>
                        <option value="ITA">Italy</option>
                        <option value="JAM">Jamaica</option>
                        <option value="JPN">Japan</option>
                        <option value="JEY">Jersey</option>
                        <option value="JOR">Jordan</option>
                        <option value="KAZ">Kazakhstan</option>
                        <option value="KEN">Kenya</option>
                        <option value="KIR">Kiribati</option>
                        <option value="PRK">Korea, Democratic People's Republic of</option>
                        <option value="KOR">Korea, Republic of</option>
                        <option value="KWT">Kuwait</option>
                        <option value="KGZ">Kyrgyzstan</option>
                        <option value="LAO">Lao People's Democratic Republic</option>
                        <option value="LVA">Latvia</option>
                        <option value="LBN">Lebanon</option>
                        <option value="LSO">Lesotho</option>
                        <option value="LBR">Liberia</option>
                        <option value="LBY">Libya</option>
                        <option value="LIE">Liechtenstein</option>
                        <option value="LTU">Lithuania</option>
                        <option value="LUX">Luxembourg</option>
                        <option value="MAC">Macao</option>
                        <option value="MKD">Macedonia, the former Yugoslav Republic of</option>
                        <option value="MDG">Madagascar</option>
                        <option value="MWI">Malawi</option>
                        <option value="MYS">Malaysia</option>
                        <option value="MDV">Maldives</option>
                        <option value="MLI">Mali</option>
                        <option value="MLT">Malta</option>
                        <option value="MHL">Marshall Islands</option>
                        <option value="MTQ">Martinique</option>
                        <option value="MRT">Mauritania</option>
                        <option value="MUS">Mauritius</option>
                        <option value="MYT">Mayotte</option>
                        <option value="MEX">Mexico</option>
                        <option value="FSM">Micronesia, Federated States of</option>
                        <option value="MDA">Moldova, Republic of</option>
                        <option value="MCO">Monaco</option>
                        <option value="MNG">Mongolia</option>
                        <option value="MNE">Montenegro</option>
                        <option value="MSR">Montserrat</option>
                        <option value="MAR">Morocco</option>
                        <option value="MOZ">Mozambique</option>
                        <option value="MMR">Myanmar</option>
                        <option value="NAM">Namibia</option>
                        <option value="NRU">Nauru</option>
                        <option value="NPL">Nepal</option>
                        <option value="NLD">Netherlands</option>
                        <option value="NCL">New Caledonia</option>
                        <option value="NZL">New Zealand</option>
                        <option value="NIC">Nicaragua</option>
                        <option value="NER">Niger</option>
                        <option value="NGA">Nigeria</option>
                        <option value="NIU">Niue</option>
                        <option value="NFK">Norfolk Island</option>
                        <option value="MNP">Northern Mariana Islands</option>
                        <option value="NOR">Norway</option>
                        <option value="OMN">Oman</option>
                        <option value="PAK">Pakistan</option>
                        <option value="PLW">Palau</option>
                        <option value="PSE">Palestinian Territory, Occupied</option>
                        <option value="PAN">Panama</option>
                        <option value="PNG">Papua New Guinea</option>
                        <option value="PRY">Paraguay</option>
                        <option value="PER">Peru</option>
                        <option value="PHL">Philippines</option>
                        <option value="PCN">Pitcairn</option>
                        <option value="POL">Poland</option>
                        <option value="PRT">Portugal</option>
                        <option value="PRI">Puerto Rico</option>
                        <option value="QAT">Qatar</option>
                        <option value="REU">Réunion</option>
                        <option value="ROU">Romania</option>
                        <option value="RUS">Russian Federation</option>
                        <option value="RWA">Rwanda</option>
                        <option value="BLM">Saint Barthélemy</option>
                        <option value="SHN">Saint Helena, Ascension and Tristan da Cunha</option>
                        <option value="KNA">Saint Kitts and Nevis</option>
                        <option value="LCA">Saint Lucia</option>
                        <option value="MAF">Saint Martin (French part)</option>
                        <option value="SPM">Saint Pierre and Miquelon</option>
                        <option value="VCT">Saint Vincent and the Grenadines</option>
                        <option value="WSM">Samoa</option>
                        <option value="SMR">San Marino</option>
                        <option value="STP">Sao Tome and Principe</option>
                        <option value="SAU">Saudi Arabia</option>
                        <option value="SEN">Senegal</option>
                        <option value="SRB">Serbia</option>
                        <option value="SYC">Seychelles</option>
                        <option value="SLE">Sierra Leone</option>
                        <option value="SGP">Singapore</option>
                        <option value="SXM">Sint Maarten (Dutch part)</option>
                        <option value="SVK">Slovakia</option>
                        <option value="SVN">Slovenia</option>
                        <option value="SLB">Solomon Islands</option>
                        <option value="SOM">Somalia</option>
                        <option value="ZAF">South Africa</option>
                        <option value="SGS">South Georgia and the South Sandwich Islands</option>
                        <option value="SSD">South Sudan</option>
                        <option value="ESP">Spain</option>
                        <option value="LKA">Sri Lanka</option>
                        <option value="SDN">Sudan</option>
                        <option value="SUR">Suriname</option>
                        <option value="SJM">Svalbard and Jan Mayen</option>
                        <option value="SWZ">Swaziland</option>
                        <option value="SWE">Sweden</option>
                        <option value="CHE">Switzerland</option>
                        <option value="SYR">Syrian Arab Republic</option>
                        <option value="TWN">Taiwan, Province of China</option>
                        <option value="TJK">Tajikistan</option>
                        <option value="TZA">Tanzania, United Republic of</option>
                        <option value="THA">Thailand</option>
                        <option value="TLS">Timor-Leste</option>
                        <option value="TGO">Togo</option>
                        <option value="TKL">Tokelau</option>
                        <option value="TON">Tonga</option>
                        <option value="TTO">Trinidad and Tobago</option>
                        <option value="TUN">Tunisia</option>
                        <option value="TUR">Turkey</option>
                        <option value="TKM">Turkmenistan</option>
                        <option value="TCA">Turks and Caicos Islands</option>
                        <option value="TUV">Tuvalu</option>
                        <option value="UGA">Uganda</option>
                        <option value="UKR">Ukraine</option>
                        <option value="ARE">United Arab Emirates</option>
                        <option value="GBR">United Kingdom</option>
                        <option value="USA">United States</option>
                        <option value="UMI">United States Minor Outlying Islands</option>
                        <option value="URY">Uruguay</option>
                        <option value="UZB">Uzbekistan</option>
                        <option value="VUT">Vanuatu</option>
                        <option value="VEN">Venezuela, Bolivarian Republic of</option>
                        <option value="VNM">Viet Nam</option>
                        <option value="VGB">Virgin Islands, British</option>
                        <option value="VIR">Virgin Islands, U.S.</option>
                        <option value="WLF">Wallis and Futuna</option>
                        <option value="ESH">Western Sahara</option>
                        <option value="YEM">Yemen</option>
                        <option value="ZMB">Zambia</option>
                        <option value="ZWE">Zimbabwe</option>
                    </select>
                </label>

                <label class="label">
                    <span>Indirizzo <span class = "required">*</span></span>
                    <input id = "indirizzo" type="text" name = "indirizzo" placeholder = "Via">
                </label>

                <label class="label">
                    <span></span>
                    <input id = "indUlteriore" type="text" name = "indUlteriore"
                           placeholder = "Numero Civico">
                </label>

                <label class="label">
                    <span> Città <span class="required">*</span></span>
                    <input id = "city" type="text" name="city">
                </label>
                <label class="label">
                    <span>Provincia<span class="required">*</span></span>
                    <input id = "provincia" type="text" name = "provincia">
                </label>
                <label class="label">
                    <span>CAP<span class="required">*</span></span>
                    <input id = "cap" type = "text" name = "cap">
                </label>
                <label class="label">
                    <span>Telefono<span class="required">*</span></span>
                    <input id = "telefono" type = "tel" name = "telefono" placeholder = "+XX XXXXXXXXXX">
                </label>
                <label class="label">
                    <span>Email<span class="required">*</span></span>
                    <input id = "email" type = "email" name = "email">
                </label>

                <h2>PAGAMENTO</h2>

                <label class="label">
                    <span> Numero carta <span class = "required"> * </span></span>
                    <input id = "carta" type = "text" name = "carta" placeholder = "XXXX XXXX XXXX XXXX">
                </label>
                <label class="label">
                    <span>Intestatario<span class="required">*</span></span>
                    <input id = "intestatario" type = "text" name = "intestatario">
                </label>

                <label class="label">
                    <span> Scadenza <span class = "required">*</span></span>
                    <select id = "selection_scadenza" name = "selection_scadenza">
                        <option value = "1"> 01 </option>
                        <option value = "2"> 02 </option>
                        <option value = "3"> 03 </option>
                        <option value = "4"> 04 </option>
                        <option value = "5"> 05 </option>
                        <option value = "6"> 06 </option>
                        <option value = "7"> 07 </option>
                        <option value = "8"> 08 </option>
                        <option value = "9"> 09 </option>
                        <option value = "10"> 10 </option>
                        <option value = "11"> 11 </option>
                        <option value = "12"> 12 </option>
                    </select>
                    /
                    <input id = "year_scadenza" name = "year_scadenza" type = "number" min = "2020" max = "2100"
                           value = "2020">
                </label>
                <label class="label">
                    <span> CVC/CVV <span class="required">*</span></span>
                    <input id = "cvc" type = "text" name = "cvc">
                </label>
                <input id = "paga" type = "submit" value = "PAGA" disabled>
                <input id = "diretto" type = "hidden" name = "diretto" value = "${diretto}">
            </form>
        </div>
        <div class="Yorder">
            <table>
                <tr>
                    <th colspan="2">ORDINE</th>
                </tr>

                <c:forEach items = "${requestScope.prodotti}" var = "prodotti">
                    <tr>
                        <td> ${prodotti.nomeAuto} x ${prodotti.quantita}(Qty)</td>
                        <td> ${prodotti.prezzoTotaleString}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td> TOTALE </td>
                    <td> ${prezzoTot} </td>
                </tr>

            </table><br>


        </div><!-- Yorder -->
    </div>

</div>

<%@ include file="/WEB-INF/results/footer.jsp"%>

<script>

    var borderOK = "1px solid green";
    var borderError = '1px solid red';
    var nomeOK = false;
    var cognomeOK = false;
    var indirizzoOK = false;
    var indUlterioreOK = false;
    var cityOK = false;
    var provinciaOK = false;
    var capOK = false;
    var telefonoOK = false;
    var emailOK = false;
    var numeroCartaOK = false;
    var intestatarioOK = false;
    var cvcOK = false;
    var nazioneOK = false;
    var meseOK = false;
    var yearOK = false;

    $(document).ready(function() {

        // Controllo del nome
        $("#nome").on("input", function() {

            var nome = $("#nome");

            if (nome.val().match(/^[a-zA-Z ']+$/)) {
                nomeOK = true;
                $("#nome").css("border-bottom", borderOK);
            }
            else {
                nomeOK = false;
                $("#nome").css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        $("#selection_scadenza").change(function() {

            meseOK = true;
            $("#selection_scadenza").css("border-bottom", borderOK);

            cambiaStatoRegistrami();

        });

        $("#year_scadenza").change(function() {

            yearOK = true;
            $("#year_scadenza").css("border-bottom", borderOK);

            cambiaStatoRegistrami();
        });

        $("#nazione").change(function() {

            var nazione = $("#nazione");

            if (nazione.val() == "select") {
                nazioneOK = false;
                nazione.css("border-bottom", borderError);

            }
            else {
                nazioneOK = true;
                nazione.css("border-bottom", borderOK);
            }
        });

        // Controllo del cognome
        $("#cognome").on("input", function() {

            var cognome = $("#cognome");

            if (cognome.val().match(/^[a-zA-Z ']+$/)) {
                cognomeOK = true;
                cognome.css("border-bottom", borderOK);
            }

            else {
                cognomeOK = false;
                cognome.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo città
        $("#city").on("input", function() {

            var city = $("#city");

            if (city.val().match(/^[a-zA-Z ']+$/)) {
                cityOK = true;
                city.css("border-bottom", borderOK);
            }

            else {
                cityOK = false;
                city.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo provincia
        $("#provincia").on("input", function() {

            var provincia = $("#provincia");

            if (provincia.val().match(/^[a-zA-Z ']+$/)) {
                provinciaOK = true;
                provincia.css("border-bottom", borderOK);
            }

            else {
                provinciaOK = false;
                provincia.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo cap
        $("#cap").on("input", function() {
            var cap = $("#cap");

           if (cap.val().length == 5 && cap.val().match(/^[0-9]+$/)) {
                capOK = true;
                cap.css("border-bottom", borderOK);
            }

            else {
                capOK = false;
                cap.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo numero di carta
        $("#carta").on("input", function() {

            var carta = $("#carta");
            cc_format(carta.val());

            //Controllo per varie tipologia di carta (Mastercard, Visa)
            if ((carta.val().length == 19 && carta.val().match(/^[5][1-5]{3}/)) ||
                (carta.val().length == 19 && carta.val().match(/^[4][04589][0124][345678]/))
                || (carta.val().length == 18 && carta.val().match(/^[3][47]/))) {

                numeroCartaOK = true;
                carta.css("border-bottom", borderOK);
            }

            else {
                numeroCartaOK= false;
                carta.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Funzione che inserisce spazi bianchi dopo ogni 4 numeri, inoltre effettua dei controlli andando ad eliminare
        // numeri aggiuntivi e tutti i caratteri diversi dai numeri
        function cc_format(value) {

            var carta = $("#carta");
            var v = value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
            var matches = v.match(/\d{4,16}/g);
            var match = matches && matches[0] || '';
            var parts = [];
            for (var i = 0, len = match.length; i < len; i+=4)
                parts.push(match.substring(i, i + 4));
            if (parts.length)
                carta.val(parts.join(' '));
            else
                carta.val(value);
        }

        // Controllo intestatario
        $("#intestatario").on("input", function() {

            var intestatario = $("#intestatario");

            if (intestatario.val().match(/^[a-zA-Z ']+$/)) {
                intestatarioOK = true;
                intestatario.css("border-bottom", borderOK);
            }
            else {
                intestatarioOK = false;
                intestatario.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo cvc
        $("#cvc").on("input", function() {

            var cvc = $("#cvc");

            if (cvc.val().length == 3 && cvc.val().match(/^[0-9]+$/)) {
                cvcOK = true;
                cvc.css("border-bottom", borderOK);
            }
            else {
                cvcOK = false;
                cvc.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo numero di telefono
        $("#telefono").on("input", function() {

            var telefono = $("#telefono");

            if (telefono.val().length == 14 && telefono.val().match(/^\+[0-9]{2} [0-9]+$/)) {
                telefonoOK = true;
                telefono.css("border-bottom", borderOK);
            }
            else {
                telefonoOK = false;
                telefono.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo e-mail
        $("#email").on("input", function() {

            var email = $("#email");

            if (email.val().match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
                emailOK = true;
                email.css("border-bottom", borderOK);
            }
            else {
                emailOK = false;
                email.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo indirizzo
        $("#indirizzo").on("input", function() {

            var indirizzo = $("#indirizzo");

            if (indirizzo.val().match(/^[a-zA-Z ']+$/)) {
                indirizzoOK = true;
                indirizzo.css("border-bottom", borderOK);
            }
            else {
                indirizzoOK = false;
                indirizzo.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });

        // Controllo informazioni ulteriori indirizzo
        $("#indUlteriore").on("input", function() {

            var indUlteriore = $("#indUlteriore");

            if (indUlteriore.val().match(/^[0-9a-zA-Z. ]+$/)) {
                indUlterioreOK = true;
                indUlteriore.css("border-bottom", borderOK);
            }
            else {
                indUlterioreOK = false;
                indUlteriore.css("border-bottom", borderError);
            }

            cambiaStatoRegistrami();
        });
    });

    // Abilitare e disabilitare il button PAGA
    function cambiaStatoRegistrami() {

        if (nomeOK && cognomeOK && nazioneOK && indirizzoOK && indUlterioreOK && cityOK && provinciaOK && capOK &&
            telefonoOK && emailOK  && numeroCartaOK && intestatarioOK && cvcOK && yearOK && meseOK) {
            $("#paga").prop("disabled", false);
        }

        else
            $("#paga").prop("disabled", true);
    }

</script>
</body>
</html>