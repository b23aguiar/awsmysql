package br.com.hazzac.awsmysql.aws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="sis_pessoa")
public class PessoaEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	public PessoaEntity() {
		super();
	}
	
	public PessoaEntity(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
}
