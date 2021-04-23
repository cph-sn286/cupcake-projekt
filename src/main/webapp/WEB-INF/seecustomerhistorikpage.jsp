<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         List of Orders
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">

            <h1>Hello ${sessionScope.email} </h1>
            <h3>du er det rigtige sted</h3>

            <table class="table">
                <thead>
                <th>UserId</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Saldo</th>
                <th></th>
                </thead>
                    <tr>
                        <td>${requestScope.valgtBruger.id}</td>
                        <td>${requestScope.valgtBruger.email}</td>
                        <td>${requestScope.valgtBruger.password}</td>
                        <td>${requestScope.valgtBruger.role}</td>
                        <td>${requestScope.valgtBruger.saldo}</td>
                    </tr>
            </table>

                <c:if test="${not empty requestScope.error}">
                    <br/>
                    <p style="coler:red;font-size: large">${requestScope.error}</p>
                </c:if>

            </form>
        </div>
        <div class="col-sm-2">

    </jsp:body>
</t:genericpage>
