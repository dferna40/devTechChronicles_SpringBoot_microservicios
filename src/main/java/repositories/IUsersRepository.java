package repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiRest.autentication.models.Users;


@Repository
public interface IUsersRepository extends JpaRepository<Users, Long>{

	//Método para poder buscar un usuario mediante su nombre
    Optional<Users> findByUsername(String username);

    //Método para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByUsername(String username);
}
