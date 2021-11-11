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

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private IProfissionalService profissionalService;

	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "lista_profissional";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (profissional.getPapel() == null) {
			profissional.setPapel("profissional");
		}

		if (result.hasErrors()) {
			return "/";
		}
		
		profissional.setSenha(encoder.encode(profissional.getSenha()));
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional inserido com sucesso");
		return "redirect:/admin";
	}

	@GetMapping("/editar/{cpf}")
	public String preEditar(@PathVariable("cpf") String cpf, ModelMap model) {
		model.addAttribute("profissional", profissionalService.buscarPorCpf(cpf));
		return "profissional/edicao";
	}

	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		if (profissional.getPapel() == null) {
			profissional.setPapel("profissional");
		}
		if (result.hasErrors()) {
			return "profissional/cadastro";
		}
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/excluir/{cpf}")
	public String excluir(@PathVariable("cpf") String cpf, RedirectAttributes attr) {
		// if (clienteService.clienteTemConsulta(id)) {
		// 	attr.addFlashAttribute("fail", "Cliente não excluído. Possui consultas agendadas.");
		// }
		// else {
		profissionalService.excluir(cpf);
		attr.addFlashAttribute("sucess", "Profissional excluído com sucesso.");
		// }
		return "redirect:/profissionais/listar";
	}
}
