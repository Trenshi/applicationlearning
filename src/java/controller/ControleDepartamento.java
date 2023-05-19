package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Departamento;
import model.dao.EmpresaDAO;

/**
 *
 * @author victor.hemcruz
 */
@WebServlet(name = "ControleDepartamento", urlPatterns = {"/ControleDepartamento"})
public class ControleDepartamento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String flag, mensagem = null;
        flag = request.getParameter("flag");
        EmpresaDAO dao = new EmpresaDAO();
        RequestDispatcher disp = null;
        if (flag.equalsIgnoreCase("CadastroDepartamento")) {
            Departamento dep = new Departamento();
            dep.setIdDepartamento(request.getParameter("idDepartamento"));
            dep.setNomeDepartamento(request.getParameter("nomeDepartamento"));
            dep.setFoneDepartamento(request.getParameter("telefoneDepartamento"));
            int resultado = dao.salvar(dep);
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
            disp = request.getRequestDispatcher("Mensagens.jsp");
        } else if (flag.equalsIgnoreCase("listarDepartamentos")) {
            request.setAttribute("listarDepartamentos", dao.listarDepartamentos());
            disp = request.getRequestDispatcher("ListarDepartamentos.jsp");
        } else if (flag.equalsIgnoreCase("ConsultarDepartamento")) {
            request.setAttribute("listarDepartamentos", dao.consultarDepartamentos(request.getParameter("nomeDepartamento")));
            disp = request.getRequestDispatcher("ListarDepartamentos.jsp");
        } else if (flag.equalsIgnoreCase("ExcluirDepartamento")) {
            int resultado = dao.excluirDepartamento(request.getParameter("idDep"));
            switch (resultado) {
                case 1:
                    mensagem = "Departamento excluído com sucesso.";
                    break;
                case 2:
                    mensagem = "Departamento com ID de " + request.getParameter("idDep") + " não existe.";
                    break;
                default:
                    mensagem = "Erro ao tentar excluir o departamento.";
                    break;
            }
            request.setAttribute("m", mensagem);
            disp = request.getRequestDispatcher("Mensagens.jsp");
        } else if (flag.equalsIgnoreCase("AlterarDepartamento")) {
            int resultado = dao.alterarDepartamento(request.getParameter("idDep"), request.getParameter("nomeDep"), request.getParameter("foneDep"));
            if (resultado == 1) {
                mensagem = "Departamento alterado com sucesso.";
            } else {
                mensagem = "Erro ao tentar alterar o departamento.";
            }
            request.setAttribute("m", mensagem);
            disp = request.getRequestDispatcher("Mensagens.jsp");
        } else if (flag.equalsIgnoreCase("BuscarDepartamento")) {
            // Buscar o departamento pelo ID para alteração.
            if (dao.buscarDepartamento(request.getParameter("idDep")) == null) {
                request.setAttribute("m", "Departamento não encontrado");
                disp = request.getRequestDispatcher("Mensagens.jsp");
            } else {
                request.setAttribute("dep", dao.buscarDepartamento(request.getParameter("idDep")));
                disp = request.getRequestDispatcher("AlterarDepartamentoById.jsp");
            }
        }
        disp.forward(request, response);
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
