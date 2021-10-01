package br.com.titcs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.titcs.domain.Usuario;
import br.com.titcs.model.UsuarioDTO;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{

	@Query(value="select new br.com.titcs.model.UsuarioDTO(id,nome,idade) from Usuario us")
	Page<UsuarioDTO> listarTodos(Pageable pageable);
}
