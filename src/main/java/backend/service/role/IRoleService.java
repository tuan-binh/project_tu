package backend.service.role;

import backend.exception.CustomException;
import backend.model.entity.RoleName;
import backend.model.entity.Roles;

public interface IRoleService {
	Roles findByRoleName(RoleName roleName) throws CustomException;
}
