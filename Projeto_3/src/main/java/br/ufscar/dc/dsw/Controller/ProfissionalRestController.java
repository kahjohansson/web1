package br.ufscar.dc.dsw.Controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;


@RestController
public class ProfissionalRestController {
		
	@Autowired
	private IProfissionalService serviceProfissional;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Profissional profissional, JSONObject json) {
		Object cpf = json.get("cpf");
		if (cpf != null) {
			profissional.setCpf((String) cpf);
		}

		profissional.setCpf((String) json.get("cpf"));
		profissional.setNome((String) json.get("name"));
		profissional.setEmail((String) json.get("email"));
		profissional.setSenha((String) json.get("password"));
		profissional.setPapel((String) json.get("papel"));
		profissional.setEspecialidade((String) json.get("especialidade"));
		profissional.setCurriculo((String) json.get("curriculo"));
	}

	@PostMapping(path = "/profissionais")
	@ResponseBody
	public ResponseEntity<Profissional> cria(@RequestBody JSONObject json, BCryptPasswordEncoder encoder) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = new Profissional();
				parse(profissional, json);
				profissional.setSenha(encoder.encode(profissional.getSenha()));
				serviceProfissional.salvar(profissional);
				return ResponseEntity.ok(profissional);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@GetMapping(path = "/profissionais")
	public ResponseEntity<List<Profissional>> lista() {
		List<Profissional> lista = serviceProfissional.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/profissionais/{cpf}")
	public ResponseEntity<Profissional> listaPorId(@PathVariable("cpf") String cpf) {
		Profissional profissional = serviceProfissional.buscarPorCpf(cpf);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profissional);
	}
	
	@GetMapping(path = "/profissionais/especialidades/{especialidade}")
		public ResponseEntity<List<Profissional>> listaPorEspecialidade(@PathVariable("especialidade") String esp) {
			List<Profissional> profissionais = serviceProfissional.buscarPorEspecialidade(esp);
			if (profissionais == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(profissionais);
	}
	
	@PutMapping(path = "/profissionais/{cpf}")
	public ResponseEntity<Profissional> atualiza(@PathVariable("cpf") String cpf, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = serviceProfissional.buscarPorCpf(cpf);
				if (profissional == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(profissional, json);
					serviceProfissional.salvar(profissional);
					return ResponseEntity.ok(profissional);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/profissionais/{cpf}")
	public ResponseEntity<Boolean> remove(@PathVariable("cpf") String cpf) {
		Profissional profissional = serviceProfissional.buscarPorCpf(cpf);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		} else {
			serviceProfissional.excluir(cpf);
			return ResponseEntity.ok(true);
		}
	}
}
