<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Cupcake
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="container-fluid">
            <h1>Hello ${sessionScope.email} din saldo er ${sessionScope.saldo}kr.</h1>
            You are now logged in as a Customer of our wonderful site.
            Role: ${sessionScope.role}
                 <form action="${pageContext.request.contextPath}/fc/placeorder" method="post">
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
                                    <select class="form-select" name="quantity" id="quantity">
                                        <option value="1" selected disabled hidden>1</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-12 col-lg-2">
                            <div class="row flex-md-row mb-5 h-md-250 position-relative">
                                <div class="col p-4 d-flex flex-column position-static">
                                    <input type="submit" value="Bestil" class="btn btn-primary btn-lg mt-4 text-center">
                                </div>
                            </div>
                        </div>
                    </div>
                 </form>
        </div>

        <div class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Modal body text goes here.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Save changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

                    <div class="col-lg-2">
                        <div class="shadow-sm h-md-250 position-relative">
                                <select name="bund" id="bund">
                                    <option value="" selected disabled hidden>vælg bund</option>
                                        <div class="col p-4 d-flex flex-column position-static">
                                    <c:forEach var="bund" items="${applicationScope.IngridiensBottomList}">
                                    <option value="${bund.bottomId}">${bund.flavor} ${bund.price} kr</option>

                                    </c:forEach>
                                </select>
                        </div>

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


                        </div>

                    <div class="col-md-6">
                        <div class="shadow-sm h-md-250 position-relative">

                            <div class="col-auto d-none d-lg-block">
                                <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                            </div>
                        </div>
                    </div>
                    </div>
                </form>
            </div>



        </div>

    </jsp:body>

</t:genericpage>

