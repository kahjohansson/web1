package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.POJO.Consulta;
import br.ufscar.dc.dsw.POJO.Cliente;
import br.ufscar.dc.dsw.POJO.Profissional;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.DAO.ConsultaDAO;
import br.ufscar.dc.dsw.DAO.ProfissionalDAO;


import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;

import java.sql.Timestamp;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/consultas/*")
public class ConsultaController extends HttpServlet {

    private static final long serialVersionUID = 1L; 
    private ConsultaDAO dao;
    private ProfissionalDAO daoProfissional;

   
    @Override
    public void init() {
        dao = new ConsultaDAO();
        daoProfissional = new ProfissionalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
            	case "/agendar":
            		agendar(request,response);
            		break;
                case "/x":
            		RequestDispatcher dispatcher = request.getRequestDispatcher("/x.jsp");
            		dispatcher.forward(request, response);
            	// case "/insere":
            	// 	insereConsulta(request, response);
            	// 	break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
        

        RequestDispatcher rd = request.getRequestDispatcher("/profissional/lista");
        rd.forward(request, response);

    }
	private void agendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	Erro erros = new Erro();
    
    Cliente clienteLogado = (Cliente) request.getSession().getAttribute("usuarioLogado");
    
    
	if (request.getSession().getAttribute("tipoUsuario") != "cliente" || clienteLogado == null) {
		erros.add("Precisa estar logado em uma conta de cliente para acessar essa página.");

        request.setAttribute("mensagens", erros);
        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
	    rd.forward(request, response);
        return;
	}
	
	try {
        
        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf");
        Profissional profissional = daoProfissional.selectByCpf(cpf);

        String dataInput = request.getParameter("data");
        String horario = request.getParameter("horario");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date data = dateFormat.parse(dataInput + " " + horario + ":00");
        
       

        String cpfCliente = clienteLogado.getCpf();
        
        Consulta consulta = new Consulta(cpfCliente, profissional.getCpf() , data);

       List<Consulta> listaConsulta =  dao.getByDate(consulta);
       boolean existe = false;
       
       for(Consulta consultaX: listaConsulta) {
    	   if(consultaX.getCpfCliente().equals(cpfCliente)){
    		   existe = true;
    		   
    	   }
           if(consultaX.getCpfProfissional().equals(profissional.getCpf())){
        	   existe = true;
           }
       }
       
        if (!existe) {
        	dao.insert(consulta);
        } else {
        	
        	erros.add("O horário escolhido já está ocupado por você ou pelo profissional.");
    		request.setAttribute("mensagens", erros);
            String URL = "/consultas/x";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
            rd.forward(request, response);
            return;
        }
	}
	catch (Exception e) {
		System.out.print(e.toString());
		
		erros.add("Erro nos dados preenchidos.");
		
		request.setAttribute("mensagens", erros);
        String URL = "/consultas/x";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
	    rd.forward(request, response);
        return;
	}

	String URL = "/index.jsp"; 
	RequestDispatcher rd = request.getRequestDispatcher(URL);
	rd.forward(request, response);

   
	}
}
	