package br.com.hazzac.awsmysql.swa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.hazzac.awsmysql.swa.entity.ProfissaoEntity;

public interface ProfissaoRepository extends CrudRepository<ProfissaoEntity, Integer> {

	List<ProfissaoEntity> findByDescricao(String descricao);
}
