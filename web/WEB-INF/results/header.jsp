<header class="header" >
    <div class = "container">
        <nav class="nav_homepage">
            <input type = "checkbox" id = "check" name = "checkbox">
            <label for = "check" class = "checkbtn"> <!-- label che diventerà visibile per rendere la navbar responsive; saranno 3 le barre orizzontali -->
                <i class="fas fa-bars"></i>
            </label>
            <label class="logo" style="font-family: 'Poppins', sans-serif;">LUXURYCARS</label> <!--Nome del sito-->
            <ul> <!-- lista contente gli elementi per navigare nel sito-->
                <li> <!-- rimanda alla homepage-->
                    <form action = "index.html">
                        <a><button class = "nav-btn" id = "btn-h">Home</button></a>
                    </form>
                </li>
                <li> <!--rimanda al carrello-->
                    <form action = "servlet-accesso-carrello">
                        <a><button class = "nav-btn" id = "btn-s"><i class="fas fa-shopping-cart"></i></button></a>
                    </form>
                </li>
                <li> <!-- rimanda al login o all'area personale-->
                    <form action = "login-registration">
                        <a><button class = "nav-btn" id = "btn-l"><i class="fa fa-user"></i></button></a>
                    </form>
                </li>
            </ul>
        </nav>
    </div>
</header>