package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Acesso;
import model.dao.EmpresaDAO;

@WebServlet(name = "ControleAcesso", urlPatterns = {"/ControleAcesso"})
public class ControleAcesso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String flag, mensagem = null;
        flag = request.getParameter("flag");
        EmpresaDAO dao = new EmpresaDAO();
        RequestDispatcher disp = null;
        if (flag.equalsIgnoreCase("login")) {
            Acesso acesso = dao.validarLogin(request.getParameter("usuario"), request.getParameter("senha"));
            if (acesso == null) {
                request.setAttribute("m", "Usuário não encontrado");
                disp = request.getRequestDispatcher("Mensagens.jsp");
            } else {
                mensagem = "Bem vindo, " + acesso.getFuncionario().getNomeFuncionario() + " (" + acesso.getFuncionario().getCargoFuncionario() + ")";
                request.setAttribute("m", mensagem);
                if (acesso.getFuncionario().getCargoFuncionario().equalsIgnoreCase("Gerente")) {
                    disp = request.getRequestDispatcher("AcessoGerente.jsp");
                } else if (acesso.getFuncionario().getCargoFuncionario().equalsIgnoreCase("Vendedor")) {
                    disp = request.getRequestDispatcher("AcessoVendedor.jsp");
                } else {
                    disp = request.getRequestDispatcher("AcessoOutro.jsp");
                }
            }
        }
        disp.forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
