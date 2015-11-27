<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#listadoDeMenu">
                <span class="sr-only">Toggle de navegacion</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>               
        </div>

        <div class="collapse navbar-collapse" id="listadoDeMenu">
            <ul class="nav nav-pills">
                <li>
                    <a href="<c:url value="/Index"/>">Portada</a>
                </li>
                <c:if test="${not empty sessionScope.rut}">
                    <li>
                        <a href="<c:url value="#"/>">Ficha Personal</a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.rut}">
                    <li>
                        <a href="<c:url value="/IngresarFicha"/>">Ingresar Ficha</a>                        
                    </li>  
                </c:if>
                <c:if test="${not empty sessionScope.rut}">
                    <li>
                        <a href="<c:url value="/EnviarSolicitud"/>">Enviar Solicitud</a>                        
                    </li>  
                </c:if>
                <c:if test="${not empty sessionScope.rut}">
                    <li>
                        <a href="<c:url value="/testServlet"/>">QR CODIGO</a>                        
                    </li>  
                </c:if>
                <c:if test="${not empty sessionScope.rut}"> 
                    <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Mantenedores <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li class="nav navbar-nav"><a href="<c:url value="/Antecedentes"/>">Antecedentes</a></li>
                        <li class="nav navbar-nav"><a href="<c:url value="/Grupos"/>">Grupos</a></li>
                    </ul>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.rut}">
                    <li>
                        <a href="<c:url value="/Logout"/>">Cerrar sesión</a>                        
                    </li> 
                </c:if>                
                <c:if test="${empty sessionScope.rut}">
                    <li>
                        <a href="<c:url value="/Login"/>">Iniciar sesión</a>
                    </li> 
                </c:if>                
            </ul>
        </div>
    </div>
</nav>