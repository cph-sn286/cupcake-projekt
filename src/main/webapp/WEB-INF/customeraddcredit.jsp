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
        <h1>Hej ${sessionScope.email} din saldo er ${sessionScope.saldo}kr.</h1>
        Her kan du tanke op

        <form action="${pageContext.request.contextPath}/fc/creditCalculator" method="POST">
            <label for="credit">Add credit:</label><br>
            <input type="number" id="credit" name="credit"><br>
            <input type="submit" value="Godkend">
        </form>

    </jsp:body>
</t:genericpage>

