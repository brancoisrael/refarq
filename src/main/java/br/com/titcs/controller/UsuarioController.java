package br.com.titcs.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.titcs.model.UsuarioDTO;
import br.com.titcs.service.UsuarioService;
import br.com.titcs.util.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de usuários", value = "Manutenção de usuários")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
		
	@ApiOperation(value="listar todos",notes="listar todos",protocols="Accept=application/json",response=UsuarioDTO.class,responseContainer = "List")	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class, responseContainer = "List")})
	@GetMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PagedModel<EntityModel<UsuarioDTO>>> listarTodos(@PageableDefault(size = Constantes.PAGEABLE_DEFAULT) Pageable pageable){
		var usuarios = usuarioService.listarTodos(pageable);
		
		usuarios.forEach(u->
			u.getContent().add(linkTo(methodOn(UsuarioController.class).buscarPorId(u.getContent().getId())).withSelfRel())
			.add(linkTo(methodOn(UsuarioController.class).excluir(u.getContent().getId())).withRel("delete"))
			.add(linkTo(methodOn(UsuarioController.class).listarTodos(pageable)).withRel(IanaLinkRelations.COLLECTION))
		);		
		
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@ApiOperation(value="por id",notes="por id",protocols="Accept=application/json",response=UsuarioDTO.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class)})
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("id") Long id){
		var usuario = usuarioService.buscarPorId(id);
		usuario.add(linkTo(methodOn(UsuarioController.class).excluir(usuario .getId())).withRel("delete"));
		usuario.add(linkTo(methodOn(UsuarioController.class).listarTodos(Pageable.ofSize(Constantes.PAGEABLE_DEFAULT))).withRel(IanaLinkRelations.COLLECTION));		
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@ApiOperation(value="inserir",notes="inserir",protocols="Accept=application/json",response = UsuarioDTO.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class)})
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioDTO usuarioDTO){
		var usuario = usuarioService.salvar(usuarioDTO);
		
		usuario.add(linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.getId())).withSelfRel());
		usuario.add(linkTo(methodOn(UsuarioController.class).excluir(usuario .getId())).withRel("delete"));
		usuario.add(linkTo(methodOn(UsuarioController.class).listarTodos(null)).withRel(IanaLinkRelations.COLLECTION));
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@ApiOperation(value="atualizar",notes="atualizar",protocols="Accept=application/json",response = UsuarioDTO.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class)})
	@PutMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioDTO> atualizar(@RequestBody UsuarioDTO usuarioDTO){
		var usuario = usuarioService.salvar(usuarioDTO);		
		
		usuario.add(linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.getId())).withSelfRel());
		usuario.add(linkTo(methodOn(UsuarioController.class).excluir(usuario .getId())).withRel("delete"));
		usuario.add(linkTo(methodOn(UsuarioController.class).listarTodos(Pageable.ofSize(Constantes.PAGEABLE_DEFAULT))).withRel(IanaLinkRelations.COLLECTION));
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@ApiOperation(value="excluir",notes="excluir",protocols="Accept=application/json",response = UsuarioDTO.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class)})
	@DeleteMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> excluir(@PathVariable("id") Long id){
		usuarioService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
