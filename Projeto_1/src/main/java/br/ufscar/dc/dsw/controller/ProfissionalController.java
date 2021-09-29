package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

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
			case "/lista":
				lista(request, response);
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}

	}	

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> listaProfissionais = dao.selectAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_profissionais.jsp");
        dispatcher.forward(request, response);
    }
	
}
