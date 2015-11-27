<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MemoryCare</title>
        <%@include file="librerias.jsp" %>
    </head>
    <body>
        <form action="<c:url value="/IndexServlet"/>" method="post" >
            <%@include file="menu_superior.jsp" %>
            <div class="contenidoCentrado">
                <img class="ancho30" src="img/logo.png" />
            </div>
        </form>
        <script type="text/javascript">
            $("body").ready(function () {

            <c:set var="exito" value="${mapMensajes['exito']}" />
            <c:if test="${not empty exito}">
                toastr.success("<c:out value="${mapMensajes['exito']}" />");
            </c:if>
            });
        </script>  



    </body>
</html>
