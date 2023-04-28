<%@page import="java.util.List"%>
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
            List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
        %>
        <form method="post" action="ControleFuncionario">
            <input type="hidden" name="flag" value="IncluirFuncionario">
            <p>
                <label for="email">Email:</label>
                <input type="email" size="50" maxlength="50" name="email" id="email" required>
            </p>
            <p>
                <label for="nome">Nome:</label>
                <input type="text" size="70" maxlength="70" name="nome" id="nome" required>
            </p>
            <p>
                <label for="cargo">Cargo:</label>
                <input type="text" size="45" maxlength="45" name="cargo" id="cargo" required>
            </p>
            <p>
                <label for="cargo">Salário:</label>
                <input type="number" name="salario" id="salario" required>
            </p>
            <p>
                <label for="idDep"> Nome do departamento: </label>
                <select name="idDep" id="idDep">
                    <%
                        for (Departamento dep : departamentos) {
                    %>
                    <option value="<%= dep.getIdDepartamento()%>"> <%= dep.getNomeDepartamento()%> </option>
                    <%
                        }
                    %>
                </select>
            </p>
            <p>
                <input type="submit" value="Salvar">
            </p>
        </form>
    </body>
</html>
