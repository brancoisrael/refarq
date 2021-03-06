package br.com.titcs.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Menu implements DomainBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7776211624808554920L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "nome", length = 200, nullable = false)
	@NotNull(message = "name.required")
	private String nome;
	
	@Column(name = "idade", nullable = true)
	private Integer idade;
	
	@OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
	private Set<Funcionalidade> funcionalidades;
}
