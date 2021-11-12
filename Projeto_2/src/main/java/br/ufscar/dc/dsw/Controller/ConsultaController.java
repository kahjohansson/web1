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
import br.ufscar.dc.dsw.domain.Usuario;
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

	private Usuario getUsuarioAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return user.getUsuario();
	}

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

	@GetMapping("/agendar/{cpf_profissional}")
	public String preEditar(@PathVariable("cpf_profissional") String cpf_profissional, ModelMap model) {
		// model.addAttribute("cpfCliente", getClienteAutenticado().getCpf());
		model.addAttribute("cpfProfissional", cpf_profissional);
		Consulta consulta = new Consulta();
		consulta.setCpfCliente(getClienteAutenticado().getCpf());
		consulta.setCpfProfissional(cpf_profissional);
		model.addAttribute("consulta", consulta);
		return "consulta/agendamento";
	}

	private boolean isHorarioOcupado(Consulta consulta) {
		
		List<Consulta> consultas = consultaService.buscarPorCpfProfissional(consulta.getCpfProfissional());
		
		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).getData().equals(consulta.getData())
					&& consultas.get(i).getHorario() == consulta.getHorario()) {
				return true;
			}
		}
		
		consultas = consultaService.buscarPorCpfCliente(consulta.getCpfCliente());
		
		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).getData().equals(consulta.getData()) && consultas.get(i).getHorario() == consulta.getHorario()) {
				return true;
			}
		}
		
		return false;
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/";
		}
		
		if (! isHorarioOcupado(consulta)){
		consultaService.salvar(consulta);
		attr.addFlashAttribute("sucess", "Consulta agendada com sucesso");
		return "redirect:/";
		} else{
			attr.addFlashAttribute("fail", "Não foi possível agendar a consulta, o horário já está ocupado.");
			return "redirect:/profissionais/listar";
		}
	}

    @GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		Usuario usuario = getUsuarioAutenticado();

		List<Consulta> consultas;

		if (usuario.getPapel() == "cliente"){
			consultas = consultaService.buscarPorCpfCliente(getClienteAutenticado().getCpf());
		}
		else {
			consultas = consultaService.buscarPorCpfProfissional(getProfissionalAutenticado().getCpf());
		}
		
		model.addAttribute("consultas", consultas);
		return "consulta/lista";
	}
    
}
