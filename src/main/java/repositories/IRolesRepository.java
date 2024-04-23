package repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiRest.autentication.models.Roles;

public interface IRolesRepository extends JpaRepository<Roles, Long>{

	//Metodo para buscar un rol por su nombre en la BD
	Optional<Roles> findByName(String name);
}
