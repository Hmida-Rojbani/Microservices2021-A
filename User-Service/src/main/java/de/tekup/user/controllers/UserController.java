package de.tekup.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.user.services.UserService;
import de.tekup.user.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

	private Environment env;
	private UserService userService;
	@GetMapping
	public String works() {
		return "User MS works on port :"+env.getProperty("local.server.port");
	}
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
		UserDTO dto = userService.saveUserToDB(userDTO);
		 if( dto != null)
			 return ResponseEntity.status(HttpStatus.CREATED)
					 			  .body("User is added to DB with id :"
					 					  +dto.getUserId());
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	 			  .body("Something Wrong");
	}

}
