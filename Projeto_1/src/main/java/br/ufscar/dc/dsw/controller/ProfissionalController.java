package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.DAO.ProfissionalDAO;
import br.ufscar.dc.dsw.POJO.Profissional;

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProfissionalDAO dao;
	
	@Override
	public void init() {
		dao = new ProfissionalDAO();
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
				cadastraProfissional(request, response);
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}

	}

	private void paginaCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaCadastro_profissional.jsp");
		dispatcher.forward(request, response);
	}

	private void cadastraProfissional(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String area = request.getParameter("area");
		String especialidade = request.getParameter("especialidade");
		String curriculo = request.getParameter("curriculo");

		Profissional profissional = new Profissional(cpf, nome, email, senha, area, especialidade, curriculo);
		
		try {
			dao.insert(profissional);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro!");
		}
	}

}
