<%-- 
    Document   : AlterarDepartamento
    Created on : 20 de abr. de 2023, 10:31:36
    Author     : victor.hemcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="Controle">
            <input type="hidden" name="flag" value="AlterarDepartamento">
            <p>
                <label for="idDep" style="margin-bottom: 30px"> Código: </label>
                <input type="text" name="idDep" id="idDep" value="<%= request.getParameter("idDep") %>" readonly style="display: block">
            </p>
            <p>
                <label for="idDep" style="margin-bottom: 30px"> Nome: </label>
                <input type="text" name="nomeDep" id="nomeDep" value="<%= request.getParameter("nomeDep") %>" style="display: block">
            </p>
            <p>
                <label for="idDep" style="margin-bottom: 30px"> Telefone: </label>
                <input type="text" name="foneDep" id="foneDep" value="<%= request.getParameter("foneDep") %>" style="display: block">
            </p>
            <p>
                <input type="submit" value="Salvar alteração" style="display: block">
            </p>
        </form>
    </body>
</html>
