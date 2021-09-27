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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }    	
    	
    	Erro erros = new Erro();
		
	    try {
	        switch (action) {
	            case "/listaCliente":
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
    
    

    private void listaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = daoCliente.selectAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/lista_clientes.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaProfissional(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> listaProfissional = daoProfissional.selectAll();
        request.setAttribute("listaProfissional", listaProfissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adm/lista_profissionais.jsp");
        dispatcher.forward(request, response);
    }
    
    
   
}