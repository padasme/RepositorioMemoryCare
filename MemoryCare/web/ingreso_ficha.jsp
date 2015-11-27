<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MemoryCare</title>
        <%@include file="librerias.jsp" %>
        <style>
            h3 {
                text-align: center;
            }
            table {
                width:50%;
                margin: 10px auto;
            }
        </style>
    </head>
    <body>
        <form action="<c:url value='/IngresarFicha'/>" method="post" >
            <%@include file="menu_superior.jsp" %>
            <h3>Ingreso de Ficha</h3>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Rut:
                    </div>
                    <div class="col-sm-2">
                        <input type="text" name="rut" class="form-control input-sm" />
                    </div>
                    <div class="col-sm-1">
                        <input type="text" name="dv" class="form-control input-sm"/> 
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Nombre:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="nombre" class="form-control input-sm" value="<c:out value='${nombre}'/>" />
                    </div>
                </div>   
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Apellido Paterno:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="ap_paterno" class="form-control input-sm" value="<c:out value='${ap_paterno}'/>"  />
                    </div>
                </div>   
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Apellido Materno:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="ap_materno" class="form-control input-sm"  value="<c:out value='${ap_materno}'/>"  />
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Fecha de nacimiento:
                    </div>
                    <div class="col-sm-3">
                        <input type="date" name="fecha_nac" class="form-control input-sm"/>
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Sexo:
                    </div>
                    <div class="col-sm-2">
                        <input type="radio" name="sexo" value="M" checked="checked"/>Masculino
                    </div> 
                    <div class="col-sm-2">
                        <input type="radio" name="sexo" value="F"/>Femenino
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4" >
                        Actividad:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="actividad" class="form-control input-sm" value="<c:out value='${actividad}'/>"  />
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Estado civil:
                    </div>
                    <div class="col-sm-2">
                        <input type="radio" name="estado_civil" value="S" checked="checked"/>Soltero
                    </div>
                    <div class="col-sm-2">
                        <input type="radio" name="estado_civil" value="C"/>Casado
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Telefono:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="telefono" class="form-control input-sm" value="<c:out value='${telefono}'/>"  />
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Dirección:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="direccion" class="form-control input-sm" value="<c:out value='${direccion}'/>" />
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Ciudad
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="ciudad" class="form-control input-sm" value="<c:out value='${ciudad}'/>"  />
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Peso:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="peso" class="form-control input-sm" value="<c:out value='${peso}'/>"  />
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-4">
                        Estatura:
                    </div>
                    <div class="col-sm-3">
                        <input type="text" name="estatura" class="form-control input-sm" value="<c:out value='${estatura}'/>" />
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-2 col-sm-offset-6">
                        <input type="reset" value="Limpiar" name="limpiar" class="btn btn-info"/>
                        <input type="submit" value="Ingresar" name="ingresar" class="btn btn-success"/>
                    </div>
                </div>
            </div>  
        </form>

        <script type="text/javascript">
            $("body").ready(function () {
            
            <c:set var="rut" value="${mapMensajes['rut']}" />
            <c:if test="${not empty rut}">
                toastr.error("<c:out value="${mapMensajes['rut']}" />");
            </c:if>
            <c:set var="dv" value="${mapMensajes['dv']}" />
            <c:if test="${not empty dv}">
                toastr.error("<c:out value="${mapMensajes['dv']}" />");
            </c:if>
            <c:set var="nombre" value="${mapMensajes['nombre']}" />
            <c:if test="${not empty nombre}">
                toastr.error("<c:out value="${mapMensajes['nombre']}" />");
            </c:if>
            <c:set var="ap_paterno" value="${mapMensajes['ap_paterno']}" />
            <c:if test="${not empty ap_paterno}">
                toastr.error("<c:out value="${mapMensajes['ap_paterno']}" />");
            </c:if>
            <c:set var="ap_materno" value="${mapMensajes['ap_materno']}" />
            <c:if test="${not empty ap_materno}">
                toastr.error("<c:out value="${mapMensajes['ap_materno']}" />");
            </c:if>
            <c:set var="fecha_nac" value="${mapMensajes['fecha_nac']}" />
            <c:if test="${not empty fecha_nac}">
                toastr.error("<c:out value="${mapMensajes['fecha_nac']}" />");
            </c:if>
            <c:set var="actividad" value="${mapMensajes['actividad']}" />
            <c:if test="${not empty actividad}">
                toastr.error("<c:out value="${mapMensajes['actividad']}" />");
            </c:if>
            <c:set var="telefono" value="${mapMensajes['telefono']}" />
            <c:if test="${not empty telefono}">
                toastr.error("<c:out value="${mapMensajes['telefono']}" />");
            </c:if>
            <c:set var="direccion" value="${mapMensajes['direccion']}" />
            <c:if test="${not empty direccion}">
                toastr.error("<c:out value="${mapMensajes['direccion']}" />");
            </c:if>
            <c:set var="ciudad" value="${mapMensajes['ciudad']}" />
            <c:if test="${not empty ciudad}">
                toastr.error("<c:out value="${mapMensajes['ciudad']}" />");
            </c:if>
            <c:set var="peso" value="${mapMensajes['peso']}" />
            <c:if test="${not empty peso}">
                toastr.error("<c:out value="${mapMensajes['peso']}" />");
            </c:if>
            <c:set var="estatura" value="${mapMensajes['estatura']}" />
            <c:if test="${not empty estatura}">
                toastr.error("<c:out value="${mapMensajes['estatura']}" />");
            </c:if>
            <c:set var="existe" value="${mapMensajes['existe']}" />
            <c:if test="${not empty existe}">
                toastr.error("<c:out value="${mapMensajes['existe']}" />");
            </c:if>
            <c:set var="fracaso" value="${mapMensajes['fracaso']}" />
            <c:if test="${not empty fracaso}">
                toastr.error("<c:out value="${mapMensajes['fracaso']}" />");
            </c:if>
            <c:set var="exito" value="${mapMensajes['exito']}" />
            <c:if test="${not empty exito}">
                toastr.success("<c:out value="${mapMensajes['exito']}" />");
            </c:if>
            
            });
        </script>         
    </body>
</html>
