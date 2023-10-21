package backend.mapper;

import backend.exception.CustomException;
import backend.mapper.IGenericMapper;
import backend.model.dto.request.UserRegister;
import backend.model.dto.response.UserResponse;
import backend.model.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements IGenericMapper<Users, UserRegister, UserResponse> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Users toEntity(UserRegister userRegister) throws CustomException {
		return Users.builder()
				  .fullName(userRegister.getFullName())
				  .email(userRegister.getEmail())
				  .password(passwordEncoder.encode(userRegister.getPassword()))
				  .build();
	}
	
	@Override
	public UserResponse toResponse(Users users) {
		return UserResponse.builder()
				  .id(users.getId())
				  .fullName(users.getFullName())
				  .email(users.getEmail())
				  .phone(users.getPhone())
				  .address(users.getAddress())
				  .roles(users.getRoles().stream().map(item -> item.getRoleName().name()).collect(Collectors.toSet()))
				  .status(users.isStatus())
				  .build();
	}
	

}
