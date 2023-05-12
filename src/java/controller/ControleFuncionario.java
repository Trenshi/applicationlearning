package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;
import model.EmpresaDAO;
import model.Funcionario;

@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String flag = request.getParameter("flag");
        EmpresaDAO dao = new EmpresaDAO();
        Funcionario func = new Funcionario();
        String mensagem;
        if (flag.equalsIgnoreCase("BuscarDepartamento")) {
            // Buscar os departamentos cadastrados para carregar no cadastro dos funcionários
            List<Departamento> departamentos = dao.listarDepartamentos();
            request.setAttribute("departamentos", departamentos);
            RequestDispatcher disp = request.getRequestDispatcher("IncluirFuncionario.jsp");
            disp.forward(request, response);
        } else if (flag.equalsIgnoreCase("IncluirFuncionario")) {
            //Salva os dados do funcionário digitados na página InserirFuncionário
            String email, nome, cargo, idDep;
            Double salario;
            email = request.getParameter("email");
            nome = request.getParameter("nome");
            cargo = request.getParameter("cargo");
            salario = Double.parseDouble(request.getParameter("salario"));
            idDep = request.getParameter("idDep");
            //
            func.setEmailFuncionario(email);
            func.setNomeFuncionario(nome);
            func.setCargoFuncionario(cargo);
            func.setSalarioFuncionario(salario);
            Departamento dep = new Departamento();
            dep.setIdDepartamento(idDep);
            func.setIdDepartamento(dep);
            int resultado = dao.salvarFuncionario(func);
            if (resultado == 1) {
                mensagem = "Funcionário cadastrado com sucesso";
            } else if (resultado == 2) {
                mensagem = "Funcionário já cadastrado";
            } else {
                mensagem = "Erro ao cadastrar funcionário";
            }
            request.setAttribute("m", mensagem);
            RequestDispatcher disp = request.getRequestDispatcher("Mensagens.jsp");
            disp.forward(request, response);
        } else if (flag.equalsIgnoreCase("listarFuncionarios")) {
            List<Funcionario> funcionarios = new EmpresaDAO().listarFuncionarios();
            request.setAttribute("listarFuncionarios", funcionarios);
            RequestDispatcher disp = request.getRequestDispatcher("ListarFuncionarios.jsp");
            disp.forward(request, response);
        } else if (flag.equalsIgnoreCase("ExcluirFuncionario")) {
            //Exclui o funcionário selecionado na listagem
            String emailFuncionario = request.getParameter("emailFuncionario");
            int resultado = dao.excluirFuncionario(emailFuncionario);
            if (resultado == 1) {
                mensagem = "Funcionário excluído com sucesso.";
            } else if (resultado == 2) {
                mensagem = "Funcionário com Email de " + emailFuncionario + " não existe.";
            } else {
                mensagem = "Erro ao tentar excluir o funcionário.";
            }
            request.setAttribute("m", mensagem);
            RequestDispatcher disp = request.getRequestDispatcher("Mensagens.jsp");
            disp.forward(request, response);
        }   
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
