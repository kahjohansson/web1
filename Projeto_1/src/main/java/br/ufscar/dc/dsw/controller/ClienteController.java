package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.DAO.ClienteDAO;
import br.ufscar.dc.dsw.POJO.Cliente;

@WebServlet(urlPatterns = "/cliente/*")
public class ClienteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClienteDAO dao;
	
	@Override
	public void init() {
		dao = new ClienteDAO();
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

		try {
			switch (action) {
			case "/pagina_cadastro":
				paginaCadastro(request, response);
				break;
			case "/cadastra":
				cadastraCliente(request, response);
			case "/editar":
				paginaEdicao(request, response);	
			case "/atualizar":
                atualizar(request, response);
			case "/remover":
				removeCliente(request, response);
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}

	}

	private void paginaCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaCadastro_cliente.jsp");
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
			dao.insert(cliente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/listaCliente");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro!");
		}
	}

	private void removeCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        Cliente cliente = dao.selectByCpf(cpf);
        dao.delete(cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/admin_home.jsp");
        dispatcher.forward(request, response);
    }

	private void paginaEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        Cliente cliente = dao.selectByCpf(cpf);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/edicao_cliente.jsp");
        dispatcher.forward(request, response);
    }

	private void atualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf");
        Cliente cliente = dao.selectByCpf(cpf);

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
            dao.update(clienteNew);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/admin_home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/adm/edicao_cliente.jsp");
            rd.forward(request, response);
        }

       
    }
}
