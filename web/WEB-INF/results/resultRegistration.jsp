<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!------ Include the above in your HEAD tag ---------->


<!DOCTYPE html>
<head>
    <title> Registration</title>
    <link rel = "stylesheet" type="text/css" href = "./css/style_nav.css">
    <link rel = "stylesheet" type="text/css" href = "./css/style_footer.css">
    <link rel = "stylesheet" type="text/css" href = "./css/style_result_registration.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body style=" background-color: rgba(153,153,153,0.44);">
<div class="hero">
    <%@ include file="/WEB-INF/results/header.jsp"%>

    <div class="central" id="central"> <!-- div contenente il risultato della registrazione-->
        <c:choose>
            <c:when test="${not empty customer}"> <!-- se il customer non è vuoto significa che la registrazione è andata
                a buon fine-->
                <div class="ok-message">
                    <span class="ok-text"><i class="fas fa-check"></i> ${text_ok}</span> <!--Notifica positiva-->
            </div>
            </c:when>

            <c:otherwise> <!--Altrimenti significa che la registrazione è fallita, customer vuoto-->
                <div class="error-message">
                    <span class="error-text"><i class="fas fa-times"></i>  ${text_error}</span> <!--Notifica negativa-->
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <%@ include file="/WEB-INF/results/footer.jsp"%>
</div>

</body>
</html>






































