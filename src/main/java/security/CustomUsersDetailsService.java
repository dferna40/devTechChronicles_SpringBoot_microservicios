package security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apiRest.autentication.models.Roles;
import com.apiRest.autentication.models.Users;

import repositories.IUsersRepository;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

	private IUsersRepository usersRepo;

	@Autowired
	public CustomUsersDetailsService(IUsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	//Metodo para traernos una lista de autoridades por medio de una lista de roles
	public Collection<GrantedAuthority> mapToAuthorities(List<Roles> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	//Metodo para traernos un usuario con todos sus datos por medio de su username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return new User(users.getUsername(),users.getPassword(), mapToAuthorities(users.getRoles()));
	}

}
