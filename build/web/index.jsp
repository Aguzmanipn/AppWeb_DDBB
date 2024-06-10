<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="mrysi.beans.Departamentos"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <%getServletContext().getRequestDispatcher("/ServletEscuela").include(request, response);%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table> 
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            <c:forEach items="${requestScope.ListaDepartamentos}" var="e">
                <tr>
                    <td><c:out value="${e.id}"/></td>
                    <td><c:out value="${e.nombre}"/></td>
                    <td> <a href="#">Editar</a> | <a href="#">Eliminar</a> </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
