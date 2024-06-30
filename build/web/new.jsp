<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="mrysi.beans.Departamentos"%>
<%@page import="mrysi.beans.Entidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    request.getRequestDispatcher("/GradoLoad").include(request, response);
  if(request.getAttribute("ListaGrados")==null){
      response.sendRedirect("index.jsp");
  }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Agregar Localidad</h2>
        <form action="../GradoLoad" method="POST">
            Nombre: <input type="text" name="nombreGrado" value=""/><br/>
            Grado:
            <select name="idGrado">
                <c:forEach var="grado" items="${requestScope.ListaGrados}">
                    <option> value="${grado.idGrado}">
                        ${grado.nombreGrado}
                    </option>
                </c:forEach>
            </select>
            
            <p>
                <input type="submit" value="Guardar"/>
                <input type="button" value="Cancelar" onclick="window.history.back();"/>
            </p>
        </form>
    </body>
</html>
