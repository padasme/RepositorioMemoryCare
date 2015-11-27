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
        <form action="<c:url value="/Login"/>" method="post" >
            <%@include file="menu_superior.jsp" %>

            <div class="container"> <c:out value="${mapMensajes['idCliente']}"/>
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-4">
                        <div class="input-group">
                            <span class="input-group-addon">Rut</span>
                            <input type="text" class="form-control" name="txt_rut" placeholder="Sin puntos">
                            <span class="input-group-addon">-</span>
                            <input type="text" class="form-control" name="txt_dv_rut" style="max-width: 50px;" maxlength="1">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-4">
                        <div class="input-group">
                            <span class="input-group-addon">Password:</span>
                            <input type="password" class="form-control" name="txt_password">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-4">
                        <br />
                        <button type="submit" class="btn btn-primary anchoMaximo">Ingresar</button>
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript">
            $(document).ready(function () {
                
            <c:set var="txt_rut" value="${mapMensajes['txt_rut']}"/>
            <c:if test="${not empty txt_rut}" >
                toastr.error("<c:out value="${txt_rut}" />");
            </c:if>

            <c:set var="txt_password" value="${mapMensajes['txt_password']}" />
            <c:if test="${not empty txt_password}" >
                toastr.error("<c:out value="${txt_password}" />");
            </c:if>

            <c:set var="login_invalido" value="${mapMensajes['login_invalido']}" />
            <c:if test="${not empty login_invalido}" >
                toastr.error("<c:out value="${login_invalido}" />");
            </c:if>
            });
        </script> 
    </body>
</html>
