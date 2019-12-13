package br.com.hazzac.awsmysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.hazzac.awsmysql.aws.entity.PessoaEntity;
import br.com.hazzac.awsmysql.aws.repository.PessoaRepository;

@RestController
@RequestMapping ( "/pessoa" )
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@GetMapping ( value = "/{nome}" )
	public String exemplo ( @PathVariable ( "nome" ) String nome ) {
		return "Olá " + nome ;
	}

	@PostMapping
	public @ResponseBody String addPessoa (@RequestParam String name) {

		PessoaEntity n = new PessoaEntity(name);
		repository.save(n);
		return "Pessoa " + n.getId() + " gravada!";
	}
	
	@DeleteMapping
	public @ResponseBody String removerPessoa (@RequestParam String name) {

		List<PessoaEntity> n = repository.findByNome(name);
		repository.deleteAll(n);
		return "Pessoa " + n.get(0).getId() + " excluída!";
	}
}
