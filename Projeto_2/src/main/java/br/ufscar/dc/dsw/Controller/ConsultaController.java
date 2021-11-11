package br.ufscar.dc.dsw.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.impl.ClienteService;
import br.ufscar.dc.dsw.service.impl.ConsultaService;
import br.ufscar.dc.dsw.service.impl.ProfissionalService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ClienteService clienteService;

    @Autowired
	private ProfissionalService profissionalService;

    private Cliente getClienteAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return clienteService.buscarPorEmail(user.getUsername());
	}
	
	private Profissional getProfissionalAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return profissionalService.buscarPorEmail(user.getUsername());
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Consulta consulta, ModelMap model) {
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "consulta/cadastro";
	}

    @GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		List<Consulta> consultas = consultaService.buscarPorCliente(getClienteAutenticado());
		
		if(consultas.isEmpty()) {
			consultas = consultaService.buscarPorProfissional(getProfissionalAutenticado());
		}
		
		model.addAttribute("consultas", consultas);
		return "consulta/lista";
	}
    
}
