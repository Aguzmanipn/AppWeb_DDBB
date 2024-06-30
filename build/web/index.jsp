<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mrysi.beans.Departamentos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grado</title>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/menu.jspf" %>
    <%request.getRequestDispatcher("/GradoLoad").include(request, response);%>
    <h2Grados Escolares</h2>
    <a href="new.jsp">Agregar</a>
        <table> 
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            <c:forEach var="loc" items="${requestScope.ListaGrados}">
                <tr>
                    <td>${loc.idGrado}</td
                    <td>${loc.nombreGrado}</td>
                    <td>${loc.entidad.nombreEntidad}</td>
                    <td> 
                        <a href="../GradoEdit?id=${loc.idGrado}" >Editar</a>|
                        <a href="../GradoDelete?id=${loc.idGrado}&_METHOD=DELETE"
                           onclick="return confirm('¿Estas seguro de Eliminar el registro?')">
                            Borrar</a>
                        </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
