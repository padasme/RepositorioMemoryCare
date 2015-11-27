<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="librerias.jsp" %>
    </head>
    <body>
        <form action="<c:url value="/Grupos"/>" method="post" >
            <%@include file="menu_superior.jsp" %>
            <center>             
            <h3>Mantenedor de Grupos</h3>
            </br>            
            <div class="container-fluid">
            <!--Inicio campo descripción-->            
                <div class="row">
                    <div class="col-sm-3 col-sm-offset-2" style="text-align: right">
                        Descripción:
                    </div>
            <!--Fin campo descripción-->     
            <!--Inicio campo descripción-->
                    <div class="col-sm-2">
                        <input type="text" name="descripcion" class="form-control input-sm" value="<c:out value="${grupo.descripcion}"/>" />
                    </div>
            <!--Fin campo descripción-->
            </div>
            </div></br>        
            <!--Inicio botón-->
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <input type="reset" value="Limpiar" name="limpiar" class="btn btn-info"/>
                    <input type="submit" value="Agregar" name="btn" class="btn btn-success"/>
                </div>
            </div>           
            <!--Fin botón-->                                
        </form>
        </p>
        <!--Inicio creación tabla-->
        <div style="width: 450px; height: 250px !important; overflow-y: scroll;">
        <table class="table table-striped" style="table-layout: fixed; width: 100%">
        <tr  style="display: block; position: absolute;">
            <th style="width: 350px;">Descripción</th>
            <th style="width: 84px;text-align:right">Acción</th>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <c:forEach var="p" items="${lstGrupos}">
            <tr>
                <td style="width: 350px;"><c:out value="${p.descripcion}"/></td>            
                <td style="width: 84px;text-align: right">
                    <form action="<c:url value="/Grupos"/>" method="post">
                        <input type="hidden" name="id" value="${p.id}"/>                    
                        <input type="hidden" name="descripcion_lista" value="${p.descripcion}"/>                            
                        <input type="submit" name="btn" value="Editar"/>
                    </form>
                </td>            
            </tr>
        </c:forEach>
        </table>
        </div>           
        <!--Fin creación tabla-->    
       </center>    
            
            
<script type="text/javascript">
    $(document).ready(function () {
        <c:set var = "descripcion" value = "${mapMensajes['descripcion']}" />
        <c:if test = "${not empty descripcion}" >
            toastr.error("<c:out value="${descripcion}" />");
        </c:if>                        
        
        <c:set var="ErrorMensaje" value="${ErrorMensaje}"/>
        <c:if test ="${not empty ErrorMensaje}">
            toastr.error("<c:out value="${ErrorMensaje}"/>");    
        </c:if>
        
        <c:set var="mensaje" value="${mensaje}"/>
        <c:if test ="${not empty mensaje}">
            toastr.success("<c:out value="${mensaje}"/>");    
        </c:if>                            
    });
</script>     
</body>
</html>
