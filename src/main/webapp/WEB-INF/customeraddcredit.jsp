<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
Velkommen til Tank-op-siden
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <h1 class="text-center"> Hej ${sessionScope.email} din saldo er ${sessionScope.saldo}kr.</h1>

        <h3 class="text-center"> Her kan du tanke op </h3>

        <form action="${pageContext.request.contextPath}/fc/creditCalculator" method="POST">
            <div class="text-center card w-25 card border-dark mb-3 justify-content-center mx-auto"style="width: 200px;">
                <div class="card-body">
            <label for="credit">Indtast beløb</label><br>
            <input class="card-text" type="number" id="credit" name="credit"><br> <br>
            <input class="btn btn-primary" type= "submit" value="Godkend">
            </div>
            </div>

        </form>

    </jsp:body>
</t:genericpage>

