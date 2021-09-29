package br.com.titcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.titcs.model.UsuarioDTO;
import br.com.titcs.service.UsuarioService;
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
	
	@ApiOperation(value="listar todos",notes="listar todos",protocols="Accept=application/json",response=String.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class)})
	@GetMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
		return new ResponseEntity<>(usuarioService.listarTodos(), HttpStatus.OK);
	}
	
	@ApiOperation(value="inserir",notes="inserir",protocols="Accept=application/json",response = UsuarioDTO.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class)})
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioDTO usuarioDTO){
		return new ResponseEntity<>(usuarioService.inserir(usuarioDTO), HttpStatus.OK);
	}
}
