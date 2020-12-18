var timer; //variabile che indica il timer per effettuare switch immagini di sfondo
var n = 0; //indica quante immagini devono effettuare la rotazione
var bgImages= new Array(); //array contente il path delle immagini di sfondo generiche

// Caricamento path immagini nell'array
bgImages.push("./images/home3.jpg");
bgImages.push("./images/home5.jpg");
bgImages.push("./images/home6.jpg");
bgImages.push("./images/home8.jpg");
bgImages.push("./images/home9.jpg");
bgImages.push("./images/home10.jpg");

var bgImagesCars = new Array(); //array per le immagini di auto di una determinata marca (:hover su nome marca)
//caricamento path immagini nell'array

bgImagesCars.push("./images/rotanti/bugatti1.jpeg");
bgImagesCars.push("./images/rotanti/astonmartin1.jpg");
bgImagesCars.push("./images/rotanti/ferrari10.jpg");
bgImagesCars.push("./images/rotanti/lamborghini10.jpg");
bgImagesCars.push("./images/rotanti/mclaren5.jpg");
bgImagesCars.push("./images/rotanti/tesla.jpg");
bgImagesCars.push("./images/rotanti/koenigsegg1.jpg");
bgImagesCars.push("./images/rotanti/bentley1.jpg");


/*
    Questa funzione va a cambiare l'immagine di sfondo in base all'over su una determinata marca;
    quando si verifica onmouseover ="" viene invocata questa funzione, a cui viene passato l'id dell'elemento che ha provocato l'evento, l'id sarà la marca specifica.
 */
function setNewImage(id) {

    //cambio dell'immagine di background
    document.body.style.backgroundSize = "cover"; //ridimensiona l'immagine di sfondo per ricoprire tutto il contenitore (nel nostro caso tuto il body)
    document.body.style.backgroundRepeat = "no-repeat"; //non ripete l'immagine di sfondo se questa ha una grandezza minore rispetto al contenitore di dove si trova
    document.body.style.backgroundAttachment ="fixed";
    document.body.style.backgroundPosition = "50% 50%"; //centrata
    document.body.style.transition = "background 0.5s ease-in-out"; //transizione nel cambio dell'immagine

    //switch su id per selezionare la giusta immagine di quella marca
    switch (id) {

        case "bugatti": document.body.background = bgImagesCars[0]; break;
        case "astonmartin": document.body.background = bgImagesCars[1]; break;
        case "ferrari": document.body.background = bgImagesCars[2]; break;
        case "lamborghini": document.body.background = bgImagesCars[3]; break;
        case "mclaren": document.body.background = bgImagesCars[4]; break;
        case "tesla": document.body.background = bgImagesCars[5]; break;
        case "koenigsegg": document.body.background = bgImagesCars[6]; break;
        case "bentley": document.body.background = bgImagesCars[7]; break;
        case "bugatti1": document.body.background = bgImagesCars[0]; break;
        case "astonmartin1": document.body.background = bgImagesCars[1]; break;
        case "ferrari1": document.body.background = bgImagesCars[2]; break;
        case "lamborghini1": document.body.background = bgImagesCars[3]; break;
        case "mclaren1": document.body.background = bgImagesCars[4]; break;
        case "tesla1": document.body.background = bgImagesCars[5]; break;
        case "koenigsegg1": document.body.background = bgImagesCars[6]; break;
        case "bentley1": document.body.background = bgImagesCars[7]; break;
    }
}

// Funzione che mostra le immagini di sfondo generiche
function LoadRandomBackground() {

    // Settaggio dello style dell'immagine di sfondo
    document.body.style.backgroundSize= "cover";  //ridimensiona l'immagine di sfondo per ricoprire tutto il contenitore (nel nostro caso tuto il body)
    document.body.style.backgroundRepeat= "no-repeat";
    document.body.style.backgroundAttachment="fixed";
    document.body.style.backgroundPosition= "50% 50%";
    document.body.style.transition= "background 1s ease-in-out";

    // 6 immagini che vengono selezionate una alla volta; il numero n deve essere minore o uguale alla dimensione
    // dell'array delle immagini generiche

    if (n == 6){

        document.body.background = bgImages[0]; //settaggio prima immagine;
        n = 1; //parte dalla successiva immagine;
    }

    else {

        document.body.background = bgImages[n]; //settaggio dell'immagine che si trova in posizione n
        n++; //incremento numero per andare a settare successivamente l'immagine successiva;
    }
}

//funzione che permette di settare o avviare il timer, che servirà per cambiare le immagini di sfondo generiche;
function StartBackgroundRefreshTimer() {

    /*
        settaggio timer, ed ogni 4secondi viene invocata la funzione LoadRandomBacground()
        per cambiare l'immagine di sfondo generica
    */
    timer = setInterval('LoadRandomBackground()', 4000);
}

// Funzione per pulire il timer; quindi di terminare la selezione casuale delle immagini di sfondo generiche;
function StopBackgroundRefreshTimers() {

    clearInterval(timer);
}