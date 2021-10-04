package br.com.titcs.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_perfil")
public class Perfil implements DomainBase{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -92472441042545709L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;	
	
	@Column(name = "nome", length = 50, nullable = false, unique = true)
	@NotNull(message = "name.required")
	private String nome;
	
	@Column(name = "descricao", length = 500, nullable = true)
	private String descricao;

	@ManyToMany(mappedBy = "perfis")
	private Set<Funcionalidade> funcionalidades;
}
