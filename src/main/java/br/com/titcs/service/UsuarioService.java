package br.com.titcs.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.titcs.domain.Usuario;
import br.com.titcs.model.UsuarioDTO;
import br.com.titcs.repository.AuditRepository;
import br.com.titcs.repository.UsuarioRepository;
import br.com.titcs.util.MapperClass;

@Service
public class UsuarioService implements ServiceBase{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MessageSource messageSource;
		
	@Autowired
	private PagedResourcesAssembler<UsuarioDTO> pagedResourcesAssembler;
	
	@Autowired
	private AuditRepository auditRepository;
	
	public PagedModel<EntityModel<UsuarioDTO>> listarTodos(Pageable pageable){
		var usuarios =usuarioRepository.listarTodos(pageable);
		return pagedResourcesAssembler.toModel(usuarios);		
	}
	
	public UsuarioDTO buscarPorId(Long id){
		recuperarHistorico(id);
		
		var optional = usuarioRepository.findById(id) ;
		return MapperClass.converter(optional.get(),UsuarioDTO.class);
	}
	
	@Transactional
	public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
		var usuario = MapperClass.converter(usuarioDTO, Usuario.class);
		validateRequest(usuario,messageSource);
		
		return MapperClass.converter(usuarioRepository.save(usuario),UsuarioDTO.class);
	}	
	
	@Transactional
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public List<?> recuperarHistorico(Long id){
		var criteria = new HashMap<String, Object>();
		criteria.put("id", id);
		return auditRepository.pesquisarHistorico(Usuario.class,criteria);				
	}
}
