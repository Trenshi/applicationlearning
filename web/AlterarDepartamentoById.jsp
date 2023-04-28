<%@page import="model.Departamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Departamento dep = (Departamento) request.getAttribute("dep");
        %>
        <form method="POST" action="Controle">
            <input type="hidden" name="flag" value="AlterarDepartamento">
            <p>
                <label for="idDep" style="margin-bottom: 30px"> Código: </label>
                <input type="text" name="idDep" id="idDep" value="<%= dep.getIdDepartamento()%>" readonly style="display: block">
            </p>
            <p>
                <label for="idDep" style="margin-bottom: 30px"> Nome: </label>
                <input type="text" name="nomeDep" id="nomeDep" value="<%= dep.getNomeDepartamento() %>" style="display: block">
            </p>
            <p>
                <label for="idDep" style="margin-bottom: 30px"> Telefone: </label>
                <input type="text" name="foneDep" id="foneDep" value="<%= dep.getFoneDepartamento() %>" style="display: block">
            </p>
            <p>
                <input type="submit" value="Salvar alteração" style="display: block">
            </p>
        </form>
    </body>
</html>
