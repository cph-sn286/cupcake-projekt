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

        <form action="${pageContext.request.contextPath}/fc/placeorderline" method="post">
                <%--            <div class="container">--%>
            <div class="row">
                <div class="col-md-12">

                    <div class="col-md-3">
                        <select name="bund" id="bund">
                            <option value="" selected disabled hidden>vælg bund</option>
                            <c:forEach var="bund" items="${applicationScope.IngridiensBottomList}">
                                <option value="${bund.bottomId}">${bund.flavor} ${bund.price} kr</option>

                            </c:forEach>
                        </select>
                    </div>


                    <div class="col-md-3">
                        <div style="margin-top: 3em;margin-bottom: 3em;">
                            <select name="top" id="top">
                                <option value="" selected disabled hidden>vælg top</option>
                                <c:forEach var="top" items="${applicationScope.IngridiensTopList}">
                                    <option value="${top.topId}"> ${top.flavor} ${top.price} kr</option>
                                </c:forEach>
                            </select>
                        </div>


                        <div class="col-md-3">
                            <select name="quantity" id="quantity">
                                <option value="" selected disabled hidden>vælg antal</option>
                                <option value="1">1</option>
                        </div>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        </select>

                    </div>


                </div>

                <div class="col-md-3">
                    <input type="submit" value="læg i kurv" class="btn btn-primary">

                </div>
            </div>
        </form>
        <%--            </div>--%>


        <table class="table">
            <thead>
            <th>Top</th>
            <th>Bund</th>
            <th>Antal</th>
            <th>pris</th>
                <%--            <th>Ordretidspunkt</th>--%>
            <th></th>
            </thead>

            <c:forEach var="orderline" items="${sessionScope.orderlineList}">
                <tr>
                    <td>${orderline.ingridiensBottom.flavor}</td>
                    <td>${orderline.ingridiensTop.flavor}</td>
                    <td>${orderline.quantity}</td>
                    <td>${orderline.price}</td>
                </tr>
            </c:forEach>

        </table>
        <form action="${pageContext.request.contextPath}/fc/placeorder" method="post">


                <%--        <div class="col-md-3">--%>
            <select name="pickuptime" id="pickuptime">
                <option value="" selected disabled hidden>foreslå tidspunkt</option>

                <option value="ASAP">ASAP</option>
                <option value="11:00">11:00</option>
                <option value="12:00">12:00</option>
                <option value="13:00">13:00</option>
                <option value="14:00">14:00</option>
            </select>

<%--                    <select name="quantity" id="quantity">--%>
<%--                        <option value="" selected disabled hidden>vælg antal</option>--%>
<%--                        <option value="1">1</option>--%>
<%--                        </div>--%>
<%--                        <option value="2">2</option>--%>
<%--                        <option value="3">3</option>--%>
<%--                        <option value="4">4</option>--%>
<%--                        <option value="5">5</option>--%>
<%--                    </select>--%>


            <p>samlet pris: ${sessionScope.samletpris} kr</p>

            <input type="submit" value="Bestil ordre" class="btn btn-primary">
                <%--        udskriv noget --%>
            <c:if test="${not empty sessionScope.errormessage}">
                <p style="font-size: large">${sessionScope.errormessage}</p>
            </c:if>
                <%--        </div>--%>
            </div>

        </form>

    </jsp:body>

</t:genericpage>

