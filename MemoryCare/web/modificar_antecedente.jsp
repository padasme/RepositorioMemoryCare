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
    <center>
        <form action="<c:url value="/Antecedentes"/>" method="post">
        <%@include file="menu_superior.jsp" %>
        <h3>TIPOS ANTECEDENTES</h3>        
            <table>
                <tr>
                    <td>Id:</td>
                    <td>
                        <input type="text" name="id" value="${antecedente.id}" disabled="true"/>
                        
                    </td>
                </tr>
            <tr>
                <td>
                    Descripción:
                </td>
                <td>                    
                        <input type="hidden" name="id" value="<c:out value="${antecedente.id}"/>" />
                        <input type="text" name="descripcion" value="<c:out value="${antecedente.descripcion}"/>" />
                        </br>
                        <input type="submit" name="btn" value="Modificar"/>
                        <input type="submit" name="btn" value="Cancelar"/>
                </td>
            </tr>            
            </table>
        </form>
    </center>   
                
    <script type="text/javascript">
    $(document).ready(function () {        
        <c:set var="ErrorMensaje" value="${ErrorMensaje}"/>
        <c:if test ="${not empty ErrorMensaje}">
            toastr.error("<c:out value="${ErrorMensaje}"/>");    
        </c:if>
        
        <c:set var = "descripcion_lista" value = "${mapMensajes['descripcion_lista']}" />
        <c:if test = "${not empty descripcion_lista}" >
            toastr.error("<c:out value="${descripcion_lista}" />");
        </c:if>
        
        <c:set var="mensaje" value="${mensaje}"/>
        <c:if test ="${not empty mensaje}">
            toastr.success("<c:out value="${mensaje}"/>");    
        </c:if>
    });
</script> 


    </body>
</html>