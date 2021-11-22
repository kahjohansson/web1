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
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;


@RestController
public class ClienteRestController {

	@Autowired
	private IClienteService serviceCliente;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Cliente cliente, JSONObject json) {

		cliente.setCpf((String) json.get("cpf"));
		cliente.setNome((String) json.get("nome"));
		cliente.setEmail((String) json.get("email"));
		cliente.setTelefone((String) json.get("telefone"));
		cliente.setSenha((String) json.get("senha"));
		cliente.setPapel((String) json.get("papel"));
		cliente.setSexo((String) json.get("sexo"));
		cliente.setDataNascimento((String) json.get("dataNascimento"));
	}

	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> cria(@RequestBody JSONObject json, BCryptPasswordEncoder encoder) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				cliente.setSenha(encoder.encode(cliente.getSenha()));

				// String sDate6 = cliente.getDataNascimento();
				// SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy");
				// Date date6 = formatter6.parse(sDate6);  

				// cliente.setDataNascimento(date6);
				serviceCliente.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> lista = serviceCliente.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/clientes/{cpf}")
	public ResponseEntity<Cliente> listaPorId(@PathVariable("cpf") String cpf) {
		Cliente cliente = serviceCliente.buscarPorCpf(cpf);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@PutMapping(path = "/clientes/{cpf}")
	public ResponseEntity<Cliente> atualiza(@PathVariable("cpf") String cpf, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = serviceCliente.buscarPorCpf(cpf);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					serviceCliente.salvar(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/clientes/{cpf}")
	public ResponseEntity<Boolean> remove(@PathVariable("cpf") String cpf) {
		Cliente cliente = serviceCliente.buscarPorCpf(cpf);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} 
		// else if (!cliente.getConsultas().isEmpty()) {
		// 	return ResponseEntity.ok(false);
		else {
			serviceCliente.excluir(cpf);
			return ResponseEntity.ok(true);
		}
	}
}