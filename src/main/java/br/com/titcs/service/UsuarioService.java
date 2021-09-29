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
public class UsuarioService implements ServiceBase{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> listarTodos(){
		return usuarioRepository.listarTodos();
	}
	
	public UsuarioDTO buscarPorId(Long id){
		var optional = usuarioRepository.findById(id) ;
		return MapperClass.converter(optional.get(),UsuarioDTO.class);
	}
	
	@Transactional
	public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
		var usuario = MapperClass.converter(usuarioDTO, Usuario.class);
		validateRequest(usuario);
		return MapperClass.converter(usuarioRepository.save(usuario),UsuarioDTO.class);
	}	
	
	@Transactional
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
}
