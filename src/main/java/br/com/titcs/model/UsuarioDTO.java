package br.com.titcs.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Relation(collectionRelation = "usuarios")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1976570782402173141L;

	private Long id;
	
	private String nome;
	
	private Integer idade;
	
}
