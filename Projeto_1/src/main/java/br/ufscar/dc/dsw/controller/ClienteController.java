// package br.ufscar.dc.dsw.controller;

// import java.io.IOException;

// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import br.ufscar.dc.dsw.DAO.ClienteDAO;
// import br.ufscar.dc.dsw.POJO.Cliente;

// @WebServlet(urlPatterns = "/cliente/*")
// public class ClienteController extends HttpServlet {

// 	private static final long serialVersionUID = 1L;
// 	private ClienteDAO dao;
	
// 	@Override
// 	public void init() {
// 		dao = new ClienteDAO();
// 	}

// 	@Override
// 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
// 			throws ServletException, IOException {
// 		doGet(request, response);
// 	}

// 	@Override
// 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
// 			throws ServletException, IOException {
// 		String action = request.getPathInfo();
// 		if (action == null) {
// 			action = "";
// 		}

// 	}


// }
