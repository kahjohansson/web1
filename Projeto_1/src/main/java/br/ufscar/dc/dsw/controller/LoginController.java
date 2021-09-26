package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.POJO.Administrador;
import br.ufscar.dc.dsw.POJO.Cliente;
import br.ufscar.dc.dsw.POJO.Profissional;
import br.ufscar.dc.dsw.DAO.AdministradorDAO;
import br.ufscar.dc.dsw.DAO.ClienteDAO;
import br.ufscar.dc.dsw.DAO.ProfissionalDAO;
import br.ufscar.dc.dsw.DAO.UsuarioDAO;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/login/*")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("login_") != null) {
            login(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Erro erros = new Erro();
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
		String senha = request.getParameter("senha");

        try{
            UsuarioDAO usuarioDao = new UsuarioDAO();
            String cpf = usuarioDao.selectByEmail(email);

            if (cpf != null) {
                ClienteDAO clienteDao = new ClienteDAO();
                Cliente cliente = clienteDao.selectByCpf(cpf);
                if (cliente != null) {
                    if (cliente.getSenha().equals(senha)) {
                        request.getSession().setAttribute("usuarioLogado", cliente);
                        request.getSession().setAttribute("tipoUsuario", "cliente");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); //TODO: mudar rota
						dispatcher.forward(request, response);
                    } else {
                        erros.add("Usuário e/ou senha incorreto(s)!");
                    }
                } else {
                    ProfissionalDAO profissionalDao = new ProfissionalDAO();
                    Profissional profissional = profissionalDao.selectByCpf(cpf);
                    if (profissional != null) {
                        if (profissional.getSenha().equals(senha)) {
                            request.getSession().setAttribute("usuarioLogado", profissional);
                            request.getSession().setAttribute("tipoUsuario", "profissional");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); //TODO: mudar rota
                            dispatcher.forward(request, response);
                        } else {
                            erros.add("Usuário e/ou senha incorreto(s)!");
                        }
                    } else {
                        AdministradorDAO administradorDao = new AdministradorDAO();
                        Administrador administrador = administradorDao.selectByCpf(cpf);
                        if (administrador.getSenha().equals(senha)) {
                            request.getSession().setAttribute("usuarioLogado", administrador);
                            request.getSession().setAttribute("tipoUsuario", "administrador");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); //TODO: mudar rota
                            dispatcher.forward(request, response);
                        } else {
                            erros.add("Usuário e/ou senha incorreto(s)!");
                        }
                    }
                }
            } else {
                erros.add("Usuário não cadastrado!");
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro!");
        }

        request.getSession().invalidate();
		request.setAttribute("mensagens", erros);
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
    }

}
