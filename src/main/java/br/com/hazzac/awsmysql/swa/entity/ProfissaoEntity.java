package br.com.hazzac.awsmysql.swa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="sis_profissao")
public class ProfissaoEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	public ProfissaoEntity() {
		super();
	}
	
	public ProfissaoEntity(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
