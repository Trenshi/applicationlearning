/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Acesso;
import model.Departamento;
import model.EmpresaDAO;

/**
 *
 * @author victor.hemcruz
 */
@WebServlet(name = "Controle", urlPatterns = {"/Controle"})
public class Controle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String flag, mensagem = null;
        flag = request.getParameter("flag");
        if (flag.equalsIgnoreCase("login")) {
            String user, password;
            user = request.getParameter("usuario");
            password = request.getParameter("senha");
            EmpresaDAO dao = new EmpresaDAO();
            Acesso acesso = dao.validarLogin(user, password);
            if (acesso == null) {
                request.setAttribute("m", "Usuário não encontrado");
                RequestDispatcher disp = request.getRequestDispatcher("Mensagens.jsp");
                disp.forward(request, response);
            } else {
                String nome, cargo;
                nome = acesso.getFuncionario().getNomeFuncionario();
                cargo = acesso.getFuncionario().getCargoFuncionario();
                mensagem = "Bem vindo, " + nome + " (" + cargo + ")";
                request.setAttribute("m", mensagem);
                if (cargo.equalsIgnoreCase("Gerente")) {
                    RequestDispatcher disp = request.getRequestDispatcher("AcessoGerente.jsp");
                    disp.forward(request, response);
                } else if (cargo.equalsIgnoreCase("Vendedor")) {
                    RequestDispatcher disp = request.getRequestDispatcher("AcessoVendedor.jsp");
                    disp.forward(request, response);
                } else {
                    RequestDispatcher disp = request.getRequestDispatcher("AcessoOutro.jsp");
                    disp.forward(request, response);
                }
            }
        } else if (flag.equalsIgnoreCase("CadastroDepartamento")) {
            Departamento dep = new Departamento();
            dep.setIdDepartamento(request.getParameter("idDepartamento"));
            dep.setNomeDepartamento(request.getParameter("nomeDepartamento"));
            dep.setFoneDepartamento(request.getParameter("telefoneDepartamento"));
            int resultado = new EmpresaDAO().salvarDepartamento(dep);
            switch (resultado) {
                case 1:
                    mensagem = "Departamento salvo com sucesso.";
                    break;
                case 2:
                    mensagem = "Departamento já cadastrado.";
                    break;
                case 3:
                    mensagem = "Erro ao cadastrar departamento, contate um administrador.";
                    break;
                default:
                    break;
            }
            request.setAttribute("m", mensagem);
            RequestDispatcher disp = request.getRequestDispatcher("Mensagens.jsp");
            disp.forward(request, response);
        }
    } // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
