<%-- 
    Document   : ListarDepartamentos
    Created on : 31 de mar. de 2023, 09:42:53
    Author     : victor.hemcruz
--%>

<%@page import="model.Departamento"%>
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
            </tr>
            <%
            for(Departamento departamento : listarDepartamentos) {
            %>
            <tr>
                <td><%=  departamento.getIdDepartamento() %></td>
                <td><%=  departamento.getNomeDepartamento() %></td>
                <td><%=  departamento.getFoneDepartamento() %></td>
            </tr>
            <%
            }
            %>
            
        </table>
    </body>
</html>