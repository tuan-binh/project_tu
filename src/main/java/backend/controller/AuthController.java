package backend.controller;

import backend.exception.CustomException;
import backend.model.dto.request.UserLogin;
import backend.model.dto.request.UserRegister;
import backend.model.dto.response.JwtResponse;
import backend.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody UserLogin userLogin, HttpSession session) throws CustomException {
		return new ResponseEntity<>(userService.login(userLogin,session), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegister userRegister) throws CustomException {
		userService.register(userRegister);
		return new ResponseEntity<>("String",HttpStatus.CREATED);
	}
	
}
