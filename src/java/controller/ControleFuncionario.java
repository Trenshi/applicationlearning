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
import model.entity.Funcionario;

@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String flag = request.getParameter("flag");
        EmpresaDAO dao = new EmpresaDAO();
        Funcionario func = new Funcionario();
        RequestDispatcher disp = null;
        String mensagem;
        if (flag.equalsIgnoreCase("BuscarDepartamento")) {
            // Buscar os departamentos cadastrados para carregar no cadastro dos funcionários
            request.setAttribute("departamentos", dao.listarDepartamentos());
            disp = request.getRequestDispatcher("IncluirFuncionario.jsp");
        } else if (flag.equalsIgnoreCase("IncluirFuncionario")) {
            //Salva os dados do funcionário digitados na página InserirFuncionário
            func.setEmailFuncionario(request.getParameter("email"));
            func.setNomeFuncionario(request.getParameter("nome"));
            func.setCargoFuncionario(request.getParameter("cargo"));
            func.setSalarioFuncionario(Double.parseDouble(request.getParameter("salario")));
            Departamento dep = new Departamento();
            dep.setIdDepartamento(request.getParameter("idDep"));
            func.setIdDepartamento(dep);
            int resultado = dao.salvar(func);
            switch (resultado) {
                case 1:
                    mensagem = "Funcionário cadastrado com sucesso.";
                    break;
                case 2:
                    mensagem = "Funcionário já cadastrado.";
                    break;
                default:
                    mensagem = "Erro ao cadastrar funcionário.";
                    break;
            }
            request.setAttribute("m", mensagem);
            disp = request.getRequestDispatcher("Mensagens.jsp");
        } else if (flag.equalsIgnoreCase("listarFuncionarios")) {
            request.setAttribute("listarFuncionarios", dao.listarFuncionarios());
            disp = request.getRequestDispatcher("ListarFuncionarios.jsp");
        } else if (flag.equalsIgnoreCase("ExcluirFuncionario")) {
            //Exclui o funcionário selecionado na listagem
            int resultado = dao.excluir(new Funcionario(), request.getParameter("emailFuncionario"));
            switch (resultado) {
                case 1:
                    mensagem = "Funcionário excluído com sucesso.";
                    break;
                case 2:
                    mensagem = "Funcionário com Email de " + request.getParameter("emailFuncionario") + " não existe.";
                    break;
                default:
                    mensagem = "Erro ao tentar excluir o funcionário.";
                    break;
            }
            request.setAttribute("m", mensagem);
            disp = request.getRequestDispatcher("Mensagens.jsp");
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
