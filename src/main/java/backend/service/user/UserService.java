package backend.service.user;

import backend.exception.CustomException;
import backend.mapper.UserMapper;
import backend.model.dto.request.UserLogin;
import backend.model.dto.request.UserRegister;
import backend.model.dto.response.JwtResponse;
import backend.model.entity.RoleName;
import backend.model.entity.Roles;
import backend.model.entity.Users;
import backend.repository.IUserRepository;
import backend.security.jwt.JwtProvider;
import backend.security.user_principal.UserPrincipal;
import backend.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public JwtResponse login(UserLogin userLogin, HttpSession session) throws CustomException {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					  new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
			);
		} catch (AuthenticationException e) {
			Integer count = (Integer) session.getAttribute("count");
			if (count == null) {
				// lần đầu tiền sai mat khau
				session.setAttribute("count", 1);
			} else {
				// khong phai lan dau tien sai
				if (count == 3) {
					// khoa tai khoan
					Users users = findUserByUserName(userLogin.getEmail());
					users.setStatus(false);
					userRepository.save(users);
					throw new CustomException("your account is blocked");
				} else {
					// thuc hien tang count
					session.setAttribute("count", count + 1);
				}
			}
			throw new CustomException("Username or Password is incorrect");
		}
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		return JwtResponse.builder()
				  .token(jwtProvider.generateToken(userPrincipal))
				  .fullName(userPrincipal.getFullName())
				  .email(userPrincipal.getEmail())
				  .phone(userPrincipal.getPhone())
				  .address(userPrincipal.getAddress())
				  .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				  .status(userPrincipal.isStatus())
				  .build();
	}
	
	@Override
	public void register(UserRegister userRegister) throws CustomException {
		if (userRepository.existsByEmail(userRegister.getEmail())) {
			throw new CustomException("email is exists");
		}
		Set<Roles> roles = new HashSet<>();
		if (userRegister.getRoles() == null || userRegister.getRoles().isEmpty()) {
			roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
		} else {
			userRegister.getRoles().forEach(
					  role -> {
						  switch (role) {
							  case "admin":
								  try {
									  roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
								  } catch (CustomException e) {
									  throw new RuntimeException(e);
								  }
							  case "user":
								  try {
									  roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
								  } catch (CustomException e) {
									  throw new RuntimeException(e);
								  }
								  break;
							  default:
								  try {
									  throw new CustomException("role not found");
								  } catch (CustomException e) {
									  throw new RuntimeException(e);
								  }
						  }
					  }
			);
		}
		userRepository.save(Users.builder()
				  .fullName(userRegister.getFullName())
				  .email(userRegister.getEmail())
				  .password(passwordEncoder.encode(userRegister.getPassword()))
				  .roles(roles)
				  .build());
	}
	
	
	public Users findUserByUserName(String username) throws CustomException {
		Optional<Users> optionalUsers = userRepository.findByEmail(username);
		return optionalUsers.orElseThrow(() -> new CustomException("username not found"));
	}
	
}
