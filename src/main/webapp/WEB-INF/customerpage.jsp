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
        <h1>Hello ${sessionScope.email} din saldo er ${sessionScope.saldo}kr.</h1>
        You are now logged in as a Customer of our wonderful site.
        Role: ${sessionScope.role}

        <form action="${pageContext.request.contextPath}/fc/placeorder" method="post">
            <select name="bund" id="bund">
                <option value="" selected disabled hidden>vælg bund</option>
                <c:forEach var="bund" items="${applicationScope.IngridiensBottomList}">
                    <option value="${bund.bottomId}">${bund.flavor} ${bund.price} kr</option>

                </c:forEach>
                </div>
            </select>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                <select name="top" id="top">
                    <option value="" selected disabled hidden>vælg top</option>
                    <c:forEach var="top" items="${applicationScope.IngridiensTopList}">
                        <option value="${top.topId}"> ${top.flavor} ${top.price} kr</option>
                    </c:forEach>
                </select>

            </div>

            <select name="quantity" id="quantity">
                <option value="" selected disabled hidden>vælg antal</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>

            <input type="submit" value="Bestil" class="btn btn-primary">
        </form>

    </jsp:body>

</t:genericpage>

