// funzione per attuare i filtri tramite utilizzo di AJAX
function Filter(id) {

    if(id=='search'){ // verifica se il submit che ha provocato l'evento search (applica), settaggio filtri

        // prendo i valori di tutte le selection
        var categoria = document.getElementById("categoria").value;
        var prezzoMin = document.getElementById("prezzoMin").value;
        var prezzoMax = document.getElementById("prezzoMax").value;
        var velocitaMin = document.getElementById("velocitaMin").value;
        var velocitaMax = document.getElementById("velocitaMax").value;
        var ordinePrezzo = document.getElementById("ordinePrezzo").value;
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function () {
            if(this.readyState == 4 && this.status == 200){
                myFunction(this);
            }
        };
        var casaAut = document.title;

        // invio richiesta asincrona alla servlet XMLFiltri, ricevendo le auto con i parametri che rientrano nei filtri
        // applicati
        xmlHttpReq.open("GET","Filtri?categoria="+categoria+"&prezzoMin="+prezzoMin+"&prezzoMax="+prezzoMax
            +"&velocitaMin="+velocitaMin+"&velocitaMax="+velocitaMax+"&casaAuto="+casaAut+"&ordinePrezzo="+ordinePrezzo,
            true);
        xmlHttpReq.send();
    }
    else if(id=='allcars'){  //verifico se il submit che ha provocato l'evento è allCars (in questo caso si devono vedere tutte le auto disponibili)
        document.title = 'AllCars'; //cambio del titolo, non ci troviamo più nel caso di singola marca
        //settaggio di tutti gli elementi a "null", "pulendo" i filtri
        document.getElementById('categoria').getElementsByTagName('option')[0].selected = 'selected';
        document.getElementById('prezzoMin').getElementsByTagName('option')[0].selected = 'selected';
        document.getElementById('prezzoMax').getElementsByTagName('option')[0].selected = 'selected';
        document.getElementById('velocitaMin').getElementsByTagName('option')[0].selected = 'selected';
        document.getElementById('velocitaMax').getElementsByTagName('option')[0].selected = 'selected';
        document.getElementById('ordinePrezzo').getElementsByTagName('option')[0].selected = 'selected';
        var categoria = "error";
        var prezzoMin = "error";
        var prezzoMax = "error";
        var velocitaMin = "error";
        var velocitaMax = "error";
        var ordinePrezzo = "error";
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function () {
            if(this.readyState == 4 && this.status == 200){
                myFunction(this);
            }
        };
        var casaAut = document.title;

        //invio richiesta asincrona alla servlet XMLFiltri, ricevendo tutte le auto
        xmlHttpReq.open("GET","Filtri?categoria="+categoria+"&prezzoMin="+prezzoMin+"&prezzoMax="+prezzoMax+"&velocitaMin="+velocitaMin+"&velocitaMax="+velocitaMax+"&casaAuto="+casaAut+"&ordinePrezzo="+ordinePrezzo,true);
        xmlHttpReq.send();
    }
}

// Creazione dei div che verranno inseriti nel div della vista auto
function myFunction(xml){
    var i;
    var xmlDoc = xml.responseXML;
    var div="";
    var x = xmlDoc.getElementsByTagName("auto");
    if(x.length==0) {
        div = "<h2>NON CI SONO AUTO DISPONIBILI CON QUESTI PARAMETRI</h2></br></br></br></br></br>";
    }
    else{
        for(i=0 ; i < x.length;i++){
            div += "<div class='card'>";
            div += "<div class='card-image'><img src='" +
                x[i].getElementsByTagName("path")[0].childNodes[0].nodeValue+
                "'></div>";
            div += "<div class='card_content'><h2 class='card_title'>" +
                x[i].getElementsByTagName("nome")[0].childNodes[0].nodeValue+"</h2>";
            div += "<p class='card_text'>" +
                x[i].getElementsByTagName("prezzo")[0].childNodes[0].nodeValue + "</p>";
            div += "<p class='card_text'>" +
                x[i].getElementsByTagName("velocitaMax")[0].childNodes[0].nodeValue +" km/h</p>";
            div += "<form action = 'visualizza-auto'>";
            div += "<input type = 'hidden' name = 'marcaAuto' value = '"+
                x[i].getElementsByTagName("casaAuto")[0].childNodes[0].nodeValue +"'>";
            div +=  "<button class='btn card_btn' name = '"+
                x[i].getElementsByTagName("nome")[0].childNodes[0].nodeValue+"'><i class='fas fa-arrow-right'></i></button></form> </div> </div>";

        }
    }
    document.getElementById("card_auto").innerHTML = div;
}