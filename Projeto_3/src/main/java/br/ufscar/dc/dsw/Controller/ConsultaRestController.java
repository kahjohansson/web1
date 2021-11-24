package br.ufscar.dc.dsw.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@RestController
public class ConsultaRestController {
	
	@Autowired
	private IConsultaService serviceConsulta;
	
	@GetMapping(path = "/consultas")
	public ResponseEntity<List<Consulta>> lista() {
		List<Consulta> lista = serviceConsulta.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> listaPorId(@PathVariable("id") Long id) {
		Consulta consulta = serviceConsulta.buscarPorId(id);
		if (consulta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}
	
	@GetMapping(path = "/consultas/clientes/{cpf}")
	public ResponseEntity<List<Consulta>> listaPorCpfCliente(@PathVariable("cpf") String cpf) {
		List<Consulta> lista = serviceConsulta.buscarPorCpfCliente(cpf);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/consultas/profissionais/{cpf}")
	public ResponseEntity<List<Consulta>> listaPorIdProfissional(@PathVariable("cpf") String cpf) {
		List<Consulta> lista = serviceConsulta.buscarPorCpfProfissional(cpf);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
}
