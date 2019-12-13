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

import br.com.hazzac.awsmysql.swa.entity.ProfissaoEntity;
import br.com.hazzac.awsmysql.swa.repository.ProfissaoRepository;

@RestController
@RequestMapping ( "/profissao" )
public class ProfissaoController {

	@Autowired
	private ProfissaoRepository repository;

	@GetMapping ( value = "/{profissao}" )
	public String exemplo ( @PathVariable ( "profissao" ) String profissao ) {
		return "Você é um(a) " + profissao ;
	}

	@PostMapping
	public @ResponseBody String addProfissao (@RequestParam String name) {

		ProfissaoEntity n = new ProfissaoEntity(name);
		repository.save(n);
		return "Profissão " + n.getId() + " gravada!";
	}
	
	@DeleteMapping
	public @ResponseBody String removerPessoa (@RequestParam String descricao) {

		List<ProfissaoEntity> n = repository.findByDescricao(descricao);
		repository.deleteAll(n);
		return "Profissão " + n.get(0).getId() + " excluída!";
	}
}
