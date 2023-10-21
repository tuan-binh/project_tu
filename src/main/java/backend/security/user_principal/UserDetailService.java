package backend.security.user_principal;

import backend.model.entity.Users;
import backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = findByUsername(username);
		return UserPrincipal.build(users);
	}
	
	public Users findByUsername(String username) {
		Optional<Users> optionalUsers = userRepository.findByEmail(username);
		return optionalUsers.orElseThrow(() -> new RuntimeException("email not found"));
	}
	
}
