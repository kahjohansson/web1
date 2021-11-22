package br.ufscar.dc.dsw.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "cliente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (cliente.getPapel() == null) {
			cliente.setPapel("cliente");
		}

		if (result.hasErrors()) {
			return "/";
		}
		
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
		return "redirect:/admin";
	}

	@GetMapping("/editar/{cpf}")
	public String preEditar(@PathVariable("cpf") String cpf, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorCpf(cpf));
		return "cliente/edicao";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (cliente.getPapel() == null) {
			cliente.setPapel("cliente");
		}
		if (result.hasErrors()) {
			return "cliente/cadastro";
		}
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/excluir/{cpf}")
	public String excluir(@PathVariable("cpf") String cpf, RedirectAttributes attr) {
		// if (clienteService.clienteTemConsulta(id)) {
		// 	attr.addFlashAttribute("fail", "Cliente não excluído. Possui consultas agendadas.");
		// }
		// else {
		clienteService.excluir(cpf);
		attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
		// }
		return "redirect:/clientes/listar";
	}
}
