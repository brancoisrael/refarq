package br.com.titcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.titcs.domain.Usuario;
import br.com.titcs.model.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("select new br.com.titcs.model.UsuarioDTO(id,nome,idade) from Usuario")
	List<UsuarioDTO> listarTodos();
}
