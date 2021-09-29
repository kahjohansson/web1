package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.DAO.AdministradorDAO;
import br.ufscar.dc.dsw.DAO.ClienteDAO;
import br.ufscar.dc.dsw.DAO.ProfissionalDAO;
import br.ufscar.dc.dsw.POJO.Administrador;
import br.ufscar.dc.dsw.POJO.Cliente;
import br.ufscar.dc.dsw.POJO.Profissional;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdministradorDAO dao;

    private ProfissionalDAO daoProfissional;
    private ClienteDAO daoCliente;

    @Override
    public void init() {
        dao = new AdministradorDAO();
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        Erro erros = new Erro();

        try {
            switch (action) {
                case "/cliente":
                    homeCliente(request, response);
                    break;
                case "/cliente/pagina_cadastro":
                    paginaCadastroCliente(request, response);
                    break;
                case "/cliente/cadastra":
                    cadastraCliente(request, response);
                    break;
                case "/cliente/editar":
                    paginaEdicaoCliente(request, response);
                    break;
                case "/cliente/atualizar":
                    atualizarCliente(request, response);
                    break;
                case "/cliente/remover":
                    removeCliente(request, response);
                    break;
                case "/cliente/lista":
                    listaCliente(request, response);
                    break;
                case "/listaProfissional":
                    listaProfissional(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

        request.getSession().invalidate();
        request.setAttribute("mensagens", erros);

        RequestDispatcher rd = request.getRequestDispatcher("/adm/admin_home.jsp");
        rd.forward(request, response);

    }

    private void homeCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/clientes_home.jsp");
        dispatcher.forward(request, response);
    }

    private void listaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> listaClientes = daoCliente.selectAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/lista_clientes.jsp");
        dispatcher.forward(request, response);
    }

    private void paginaCadastroCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/pagina_cadastro_cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void cadastraCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String dataNasc = request.getParameter("dataNasc");

        Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);

        try {
            daoCliente.insert(cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/listaCliente");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro!");
        }
    }

    private void removeCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        Cliente cliente = daoCliente.selectByCpf(cpf);
        daoCliente.delete(cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/admin_home.jsp");
        dispatcher.forward(request, response);
    }

    private void paginaEdicaoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        Cliente cliente = daoCliente.selectByCpf(cpf);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/edicao_cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf");
        Cliente cliente = daoCliente.selectByCpf(cpf);

        String nome = request.getParameter("nome");
        if (nome == "") {
            nome = cliente.getNome();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = cliente.getEmail();
        }
        String senha = request.getParameter("senha");
        if (senha == "") {
            senha = cliente.getSenha();
        }
        String sexo = request.getParameter("sexo");
        if (sexo == "") {
            sexo = cliente.getSexo();
        }

        String telefone = request.getParameter("telefone");
        if (telefone == "") {
            telefone = cliente.getTelefone();
        }

        String dataNasc = request.getParameter("dataNascimento");
        if (dataNasc == "") {
            dataNasc = cliente.getDataNascimento();
        }

        Cliente clienteNew = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);
        try {
            daoCliente.update(clienteNew);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/admin_home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/adm/edicao_cliente.jsp");
            rd.forward(request, response);
        }

    }

    private void listaProfissional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profissional> listaProfissional = daoProfissional.selectAll();
        request.setAttribute("listaProfissional", listaProfissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/lista_profissionais.jsp");
        dispatcher.forward(request, response);
    }

}