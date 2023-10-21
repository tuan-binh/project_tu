package backend.service.role;

import backend.exception.CustomException;
import backend.model.entity.RoleName;
import backend.model.entity.Roles;
import backend.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public Roles findByRoleName(RoleName roleName) throws CustomException {
		Optional<Roles> optionalRoles = roleRepository.findByRoleName(roleName);
		return optionalRoles.orElseThrow(()->new CustomException("role not found"));
	}
}
