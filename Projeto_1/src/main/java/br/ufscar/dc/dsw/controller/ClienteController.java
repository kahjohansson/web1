package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.time.LocalDate;

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
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}

	}

	private void paginaCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaCadastro.jsp");
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
		LocalDate dataNasc = LocalDate.parse(request.getParameter("dataNasc"));

		Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc);
		
		try {
			dao.insere(cliente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro!");
		}
	}

}
