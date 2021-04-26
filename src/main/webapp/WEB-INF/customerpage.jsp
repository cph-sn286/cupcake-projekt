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
                <div class="row mb-2">
                    <div class="col-sm-12 col-md-6 col-lg-4">
                        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                            <div class="col p-4 d-flex flex-column position-static">
                                <h3 class="text-center">Vælg Bund</h3>
                                <select class="form-select" name="bund" id="bund">
                                    <option value="" selected disabled hidden>vælg bund</option>
                                    <c:forEach var="bund" items="${applicationScope.IngridiensBottomList}">
                                        <option value="${bund.bottomId}">${bund.flavor} ${bund.price} kr</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4">
                        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                            <div class="col p-4 d-flex flex-column position-static">
                                <h3 class="text-center">Vælg Top</h3>
                                <select class="form-select mx-auto" name="top" id="top">
                                    <option class="text-center" value="" selected disabled hidden>vælg top</option>
                                    <c:forEach var="top" items="${applicationScope.IngridiensTopList}">
                                        <option class="text-center" value="${top.topId}"> ${top.flavor} ${top.price} kr</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-2">
                        <div class="row g-0 flex-md-row mb-4 h-md-250 position-relative">
                            <div class="col p-4 d-flex flex-column position-static">
                                <h3 class="text-center">Antal</h3>
                                <input type="range" class="form-range" name="quantity" id="quantity" value="1" min="1" max="5" oninput="this.nextElementSibling.value = this.value">
                                <output>1</output>

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-2">
                        <div class="row flex-md-row mb-5 h-md-250 position-relative">
                            <div class="col p-4 d-flex flex-column position-static">
                                <input type="submit" value="Læg i kurv" class="btn btn-primary btn-lg mt-4 text-center">
                            </div>
                        </div>
                    </div>
                </div>

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
                        <td>
                            <button class="btn btn-danger " type="submit" name="delete"
                                    value="${orderline.id}">Slet
                            </button>
                        </td>
                    </tr>
                </c:forEach>

        </table>
        </form>

        <form action="${pageContext.request.contextPath}/fc/placeorder" method="post">

            <div class="col-sm-12 col-md-12 col-lg-2">
                <div class="row flex-md-row mb-5 h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <select class="form-select mx-auto" name="pickuptime" id="pickuptime">
                            <option value="ASAP" selected disabled hidden>foreslå tidspunkt</option>
                            <option value="ASAP" selected>ASAP</option>
                            <option value="11:00">11:00</option>
                            <option value="12:00">12:00</option>
                            <option value="13:00">13:00</option>
                            <option value="14:00">14:00</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="col-sm-12 col-md-12 col-lg-2">
                <div class="row flex-md-row mb-5 h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">

            <p>samlet pris: ${sessionScope.samletpris} kr</p>

            <input type="submit" value="Bestil ordre" class="btn btn-success">

                <c:if test="${not empty sessionScope.errormessage}">
                    <p style="font-size: large">${sessionScope.errormessage}</p>
                </c:if>
                <%--        </div>--%>
            </div>
                </div>
            </div>
            </div>
        </form>

    </jsp:body>

</t:genericpage>

