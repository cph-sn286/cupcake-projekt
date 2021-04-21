<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h2>Velkommen ombord</h2>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                Øens bedste cupcakes. Vælg og bestil her:
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

            <c:forEach var="bottom" items="${applicationScope.IngridiensBottomList}">
                <p>"${bottom.flavor}" koster ${bottom.price}</p>

            </c:forEach>

            <c:forEach var="top" items="${applicationScope.IngridiensTopList}">
                <p> "${top.flavor}" ${top.flavor} ${top.price} kr </p>
            </c:forEach>


            <c:if test="${sessionScope.role == 'employee' }">
            <p style="font-size: larger">This is what you can do,
                since your are logged in as an employee</p>
            <p><a href="fc/employeepage">Employee Page</a>
                </c:if>

                <c:if test="${sessionScope.role == 'customer' }">
            <p style="font-size: larger">This is what you can do, since your
                are logged in as a customer</p>
            <p><a href="fc/customerpage">Customer Page</a>
                </c:if>

        </div>

    </jsp:body>
</t:genericpage>