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

        <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">

        <h2 class="text-center">Velkommen Til Olsker Cupcakes</h2>

        <div style="margin-top: 3em;margin-bottom: 3em;">
            <h5 class="text-center">Vi har øens bedste cupcakes. Se hvores udvalg her:</h5>
            </b>
        </div>
        <div style="margin-top: 3em;margin-bottom: 3em;">
            <table class="table table-striped text-center ">
                <thead>
                <th>Flavor</th>
                <th>Price</th>
                </thead>
                <h4 class="text-center">Bottoms</h4>
                <c:forEach var="bottom" items="${applicationScope.IngridiensBottomList}">
                    <tr>
                        <td>${bottom.flavor}</td>
                        <td >${bottom.price} $</td>
                    </tr>
                </c:forEach>
            </table>
            <table class="table table-striped text-center">
                <thead>
                <th>Flavor</th>
                <th>Price</th>
                </thead>
                <h4 class="mt-2 text-center">Toppings</h4>
                <c:forEach var="top" items="${applicationScope.IngridiensTopList}">
                    <tr>
                        <td>${top.flavor}</td>
                        <td>${top.price} $</td>
                    </tr>
                </c:forEach>
            </table>

            <p class="text-center">Du skal være logget ind for at bestille.
                <a href="${pageContext.request.contextPath}/fc/loginpage">Login her</a>
            </p>

            <c:if test="${sessionScope.role == 'employee' }">
            <p style="font-size: larger">This is what you can do,
                since your are logged in as an employee</p>
            <p><a href="fc/employeepage">Employee Page</a>
                </c:if>

                <c:if test="${sessionScope.role == 'customer' }">
            <p style="font-size: larger">This is what you can do, since your
                are logged in as a customer</p>
<%--                skal have opdateret link--%>
            <p><a href="fc/customerpage">Customer Page</a>
                </c:if>

        </div>
        <div class="col-sm-2">
    </jsp:body>
</t:genericpage>