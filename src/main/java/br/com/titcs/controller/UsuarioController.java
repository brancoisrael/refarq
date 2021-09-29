package br.com.titcs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de usuários", value = "Manutenção de usuários")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@ApiOperation(value="usuario",notes="usuario",protocols="Accept=application/json",response=String.class)	
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class)})
	@GetMapping()
	@ResponseBody
	public ResponseEntity<String> listarUsuarios(){
		Object obj = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(obj);
		return new ResponseEntity<>("teste", HttpStatus.OK);
	}
}
