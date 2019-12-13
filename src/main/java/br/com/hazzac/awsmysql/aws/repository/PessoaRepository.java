package br.com.hazzac.awsmysql.aws.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.hazzac.awsmysql.aws.entity.PessoaEntity;

public interface PessoaRepository extends CrudRepository<PessoaEntity, Integer> {

	List<PessoaEntity> findByNome(String nome);
}
