package br.com.titcs.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_funcionalidade")
public class Funcionalidade implements DomainBase{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -92472441042545709L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "nome", length = 500, nullable = false, unique = true)
	@NotNull(message = "name.required")
	private String nome;
	
	@Column(name = "rota", length = 200, nullable = false)
	@NotNull(message = "route.required")
	private String rota;
	
	@Column(name = "verbo", length = 10, nullable = false)
	@NotNull(message = "verb.required")
	private String verbo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_menu")
	private Menu menu;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_funcionalidade_perfil", joinColumns = @JoinColumn(name = "id_funcionalidade"),inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private Set<Perfil> perfis;
}
