<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="mrysi.beans.Departamentos"%>
<%@page import="mrysi.beans.Entidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if(request.getAttribute("ListaGrados")=null||
       request.getAttribute("Grado")==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Grado</title>
    </head>
    <body>
        <h2>Editar Grado</h2>
        <form action="../GradoEdit" method="POST">
            <input type="hidden" name=" METHOD" value="PUT"/>
            <input type="hidden" name="idGrado"
                   value="${requestScope.Grado.nombreGrado}"/>
            Nombre: <input type="text" name="nombreGrado"
                           value="${requestScope.Grado.nombreGrado}"/>br/>
            Grado:
            <select name="idGrado">
                <c:forEach var="grado" items="${request.Scope.ListaGrados}">
                    <c:choose>
                        <c:when test="${grado.idGrado==requestScope.Grado.idGrado}">
                            <option value="${grado.idGrado}" selected>
                               ${grado.nombreGrado}
                           </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${grado.idGrado}">
                                ${grado.nombreGrado}
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <p>
                <input
            </p>
    </body>
</html>
