<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MemoryCare</title>
        <%@include file="librerias.jsp" %>
    </head>
    <body>
        <form action="LoginServlet.do" method="post" >
            <%@include file="menu_superior.jsp" %>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <div class="btn-group anchoMaximo">
                            <button type="button" class="btn btn-info anchoMaximo">
                                Agregar antecedente <span class="glyphicon glyphicon-heart"></span>
                            </button>
                        </div>
                    </div>
                </div>
                <br />
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <button type="button" class="btn btn-info anchoMaximo">
                            Agregar ficha <span class="glyphicon glyphicon-plus"></span>
                        </button>
                    </div>  
                </div>
            </div>
        </div>
    </form>
</body>
</html>
