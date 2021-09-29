package br.com.titcs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.titcs.domain.Usuario;
import br.com.titcs.model.UsuarioDTO;
import br.com.titcs.repository.UsuarioRepository;
import br.com.titcs.util.MapperClass;

@Service
public class UsuarioService implements AbstractService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> listarTodos(){
		return usuarioRepository.listarTodos();
	}
	
	@Transactional
	public UsuarioDTO inserir(UsuarioDTO usuarioDTO) {
		Usuario usuario = MapperClass.converter(usuarioDTO, Usuario.class);
		validateRequest(usuario);
		return MapperClass.converter(usuarioRepository.save(usuario),UsuarioDTO.class);
	}
}
