<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Customer Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Vi takker for din bestilling </h1><br>



<h3>indhold af din ordre </h3><br>
        <table class="table">
            <thead>
            <th>Top</th>
            <th>Bund</th>
            <th>Antal</th>
            <th>pris</th>
            <th></th>
            </thead>

            <c:forEach var="orderline" items="${sessionScope.orderlineRecieptList}">
                <tr>
                    <td>${orderline.ingridiensBottom.flavor}</td>
                    <td>${orderline.ingridiensTop.flavor}</td>
                    <td>${orderline.quantity}</td>
                    <td>${orderline.price}</td>
                </tr>
            </c:forEach>

        </table>
        <p>faktureret beløb: ${sessionScope.ordre.totalPrice} kr. </p>
        <p>Du kan hente din ordre kl.: ${sessionScope.ordre.pickupTime} kr. </p>
        <p>Vi ser om vi kan finde noget mel. Det bliver nok lidt ulækkert. </p>

    </jsp:body>

</t:genericpage>

