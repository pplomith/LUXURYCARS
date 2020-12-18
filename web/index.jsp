<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <link href = "./css/style_homepage.css" type = "text/css" rel = "stylesheet" />
    <link href = "./css/style_nav.css" type = "text/css" rel = "stylesheet" />
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script> <!--Icone font awesome-->
    <script type = "text/javascript" src = "./js/index.js"></script>
    <title>Luxury Cars</title>
</head>
<body onload = "LoadRandomBackground(); StartBackgroundRefreshTimer()"> <!--ogni volta che viene caricata la paggina si avvia il caricamento casuale
                                                                            delle immagini, con settaggio del timer-->

<%@ include file="WEB-INF/results/header.jsp"%>

<div class="table1"> <!--tabella visibile da width maggiore di 858px; row = 2 && column = 6;-->
    <table id="auto" name="auto">
        <tr>
            <td></td> <!--celle vuote (quelle a dx e sx); effetto grafico-->
            <form action = "show-auto-marca" method = "get"> <!--Form per andare a vedere le auto di una determinata marca (MostraAutoMarca) che è indicata dal name del button-->
                <td id ="lamborghini" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <!-- onmouseover chiama una funzione per settare immagine di sfondo di una determinata marca e pulisce il timer per il settaggio casuale delle immagini di
                immagini di sfondo generiche; onmouseleave viene invocata la funzione per il settaggio delle immagini generiche e viene settato di nuovo il timer; -->
                    <button type = "submit" name = "Lamborghini"> LAMBORGHINI </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "ferrari" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Ferrari"> FERRARI </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "mclaren" onmouseover = "setNewImage(id);StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "McLaren"> MCLAREN </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "bugatti" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Bugatti"> BUGATTI </button>
                </td>
            </form>
            <td></td>
        </tr>

        <tr>
            <td></td>

            <form action = "show-auto-marca" method = "get">
                <td id = "astonmartin" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Aston Martin"> ASTON MARTIN </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "bentley" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Bentley"> BENTLEY </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "tesla" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Tesla"> TESLA </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "koenigsegg" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Koenigsegg"> KOENIGSEGG </button>
                </td>
            </form>

            <td></td>
        </tr>
    </table>
</div>
<div class="table2"> <!--tabella visibile per dimensione max 858px; row= 4 && column = 2-->
    <table id="auto1" name="auto">
        <tr>

            <form action = "show-auto-marca" method = "get">
                <td id ="lamborghini1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Lamborghini"> LAMBORGHINI </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "ferrari1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Ferrari"> FERRARI </button>
                </td>
            </form>
        </tr>
        <tr>
            <form action = "show-auto-marca" method = "get">
                <td id = "mclaren1" onmouseover = "setNewImage(id);StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "McLaren"> MCLAREN </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "bugatti1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Bugatti"> BUGATTI </button>
                </td>
            </form>

        </tr>

        <tr>


            <form action = "show-auto-marca" method = "get">
                <td id = "astonmartin1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Aston Martin"> ASTON MARTIN </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "bentley1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Bentley"> BENTLEY </button>
                </td>
            </form>
        </tr>
        <tr>
            <form action = "show-auto-marca" method = "get">
                <td id = "tesla1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Tesla"> TESLA </button>
                </td>
            </form>

            <form action = "show-auto-marca" method = "get">
                <td id = "koenigsegg1" onmouseover = "setNewImage(id); StopBackgroundRefreshTimers()"
                    onmouseleave = "LoadRandomBackground(); StartBackgroundRefreshTimer()">
                    <button type = "submit" name = "Koenigsegg"> KOENIGSEGG </button>
                </td>
            </form>

        </tr>
    </table>
</div>

</body>

</html>