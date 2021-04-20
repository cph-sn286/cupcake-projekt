<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         List of sports
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">

            <h1>Hello ${sessionScope.email} </h1>
            <h3>This is a list of all ordre</h3>

            <form action="${pageContext.request.contextPath}/fc/manageorders" method="post">
                <table class="table">
                    <thead>
                    <th>orderId</th>
                    <th>User_id</th>
                    <th>Pickup-time</th>
                    <th>PriceToPay</th>
                    <th>OrderTime</th>
                    <th></th>
                    </thead>
                    <c:forEach var="orderItem" items="${applicationScope.orderList}">
                        <tr>
                            <td>${orderItem.order_id}</td>
                            <td>${orderItem.user_id}</td>
                            <td>${orderItem.pickuptime}</td>
                            <td>${orderItem.totalprice}</td>
                            <td>${orderItem.created}</td>
                            <td>
                                <button class="btn btn-danger btn-sm " type="submit" name="delete"
                                        value="${orderItem.order_id}">Fjern
                                </button>
                                <button class="btn btn-primary btn-sm " type="submit" name="edit"
                                        value="${orderItem.order_id}">Rediger
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <c:if test="${not empty requestScope.error}">
                    <br/>
                    <p style="coler:red;font-size: large">${requestScope.error}</p>
                </c:if>

            </form>
        </div>
        <div class="col-sm-4">

    </jsp:body>
</t:genericpage>
