package backend.service.user;

import backend.exception.CustomException;
import backend.model.dto.request.UserLogin;
import backend.model.dto.request.UserRegister;
import backend.model.dto.response.JwtResponse;

import javax.servlet.http.HttpSession;

public interface IUserService {
	
	JwtResponse login(UserLogin userLogin, HttpSession session) throws CustomException;
	
	void register(UserRegister userRegister) throws CustomException;
	
}
