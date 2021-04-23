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
            <h3>This is a list of all customers</h3>


                <%--            <form action="${pageContext.request.contextPath}/fc/managecustomers" method="post">--%>
            <table class="table">
                <thead>
                <th>UserId</th>
                <th>Email</th>
                <th>Passwd</th>
                <th>role</th>
                <th>Saldo</th>
                <th></th>
                <th></th>
                <th></th>
                </thead>
                <c:forEach var="userItem" items="${applicationScope.userList}">
                    <tr>
                        <td>${userItem.id}</td>
                        <td>${userItem.email}</td>
                        <td>${userItem.password}</td>
                        <td>${userItem.role}</td>
                        <td>${userItem.saldo}</td>
                        <td>
                            <button class="btn btn-danger " type="submit" name="delete"
                                    value="${userItem.id}">slet kunde!
                            </button>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/fc/seecustomerhistorik" method="post">
                                <button class="btn btn-danger" type="submit" id="userId" value="${userItem.id}"
                                        name="RedigerBruger">Rediger Bruger </button>
                            </form>
                        </td>

                    </tr>
                </c:forEach>
            </table>

            <c:if test="${not empty requestScope.error}">
                <br/>
                <p style="coler:red;font-size: large">${requestScope.error}</p>
            </c:if>

                <%--            </form>--%>
        </div>
        <div class="col-sm-2">

    </jsp:body>
</t:genericpage>
