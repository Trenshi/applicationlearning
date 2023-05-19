<%@page import="model.entity.Departamento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% //Including Java code on the HTML body
        List<Departamento> listarDepartamentos = (List<Departamento>) request.getAttribute("listarDepartamentos");
        %>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Exclusão</th>
                <th>Edição</th>
            </tr>
            <%
            for(Departamento departamento : listarDepartamentos) {
            %>
            <tr>
                <td><%=  departamento.getIdDepartamento() %></td>
                <td><%=  departamento.getNomeDepartamento() %></td>
                <td><%=  departamento.getFoneDepartamento() %></td>
                <td><a href="ControleDepartamento?flag=ExcluirDepartamento&idDep=<%= departamento.getIdDepartamento() %>">Excluir</a></td>
                <td><a href="AlterarDepartamento.jsp?idDep=<%= departamento.getIdDepartamento() %>&nomeDep=<%= departamento.getNomeDepartamento() %>&foneDep=<%= departamento.getFoneDepartamento() %>">Alterar</a></td>
            </tr>
            <%
            }
            %>
            
        </table>
    </body>
</html>