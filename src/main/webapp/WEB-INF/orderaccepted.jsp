<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Ordre:
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${true}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>


            <div style="margin-top: 3em;margin-bottom: 3em;">
                <h2>Du har bestilt disse lækre cupcakes</h2>

                <select name="bund" id="bund">
                    <c:forEach var="bund" items="${applicationScope.IngridiensBottomList}">
                    <option value="${bund.flavor}">${bund.flavor} ${bund.price} kr</option>

                    </c:forEach>
            </div>
            </select>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                <select name="top" id="top">
                    <c:forEach var="top" items="${applicationScope.IngridiensTopList}">
                        <option value="${top.flavor}"> ${top.flavor} ${top.price} kr</option>
                    </c:forEach>
                </select>
            </div>
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