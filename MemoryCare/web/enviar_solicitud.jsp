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
                    <div class="col-sm-6 col-sm-offset-3">
                        <div class="input-group">
                            <span class="input-group-addon">¿A quien deseas solicitar unirse a uno de tus grupos?</span>
                            <input type="text" class="form-control" name="txt_idSolicitado" placeholder="Ingresa su Id">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <div class="input-group">
                            <span class="input-group-addon">¿A qué grupo?</span>
                            <input type="text" class="form-control" name="txt_idSolicitado" placeholder="Selecciona uno de tus grupos">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <br />
                        <button type="submit" class="btn btn-primary anchoMaximo">Enviar solicitud</button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
