<%-- 
    Document   : ListarDepartamentos
    Created on : 31 de mar. de 2023, 09:42:53
    Author     : victor.hemcruz
--%>

<%@page import="model.Funcionario"%>
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
            List<Funcionario> listarFuncionarios = (List<Funcionario>) request.getAttribute("listarFuncionarios");
        %>
        <table>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Cargo</th>
                <th>Salário</th>
                <th>Nome do departamento</th>
                <th>ID do departamento</th>
                <th>Telefone do departamento</th>
            </tr>
            <%
                for (Funcionario funcionario : listarFuncionarios) {
            %>
            <tr>
                <td><%=  funcionario.getNomeFuncionario()%></td>
                <td><%=  funcionario.getEmailFuncionario()%></td>
                <td><%=  funcionario.getCargoFuncionario()%></td>
                <td><%=  funcionario.getSalarioFuncionario()%></td>
                <td><%=  funcionario.getIdDepartamento().getNomeDepartamento()%></td>
                <td><%=  funcionario.getIdDepartamento().getIdDepartamento()%></td>
                <td><%=  funcionario.getIdDepartamento().getFoneDepartamento()%></td>
                <td><a href="ControleFuncionario?flag=ExcluirFuncionario&emailFuncionario=<%= funcionario.getEmailFuncionario()%>">Excluir</a></td>
                <td><a href="AlterarFuncionario.jsp?emailFuncionario=<%= funcionario.getEmailFuncionario()%>&nomeFuncionario=<%= funcionario.getNomeFuncionario()%>&cargoFuncionario=<%= funcionario.getCargoFuncionario()%>&salarioFuncionario=<%= funcionario.getSalarioFuncionario() %>&nomeDepartamento=<%= funcionario.getIdDepartamento().getNomeDepartamento() %>&idDepartamento=<%= funcionario.getIdDepartamento().getIdDepartamento() %>&foneDepartamentk=<%= funcionario.getIdDepartamento().getFoneDepartamento() %>">Alterar</a></td>
            </tr>
            <%
                }
            %>

        </table>
    </body>
</html>