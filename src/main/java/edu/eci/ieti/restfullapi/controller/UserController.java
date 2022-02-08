package edu.eci.ieti.restfullapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.ieti.restfullapi.data.User;
import edu.eci.ieti.restfullapi.dto.UserDto;
import edu.eci.ieti.restfullapi.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(@Autowired UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> response;
		try {
			response = userService.getAll();
		} catch (Exception e) {
			response = new ArrayList<User>();
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<List<User>>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<User>>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping( "/{id}" )
    public ResponseEntity<User> findById( @PathVariable String id ) {
		User user = null;
		try {
			user = userService.findById(id);
		} catch (Exception e) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
	
	@PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto ) {
		User user = null;
        try {
        	user = userService.create(new User(userDto));
        } catch (Exception e) {
        	Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        	return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
   
    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id ) {
    	User user = null;
        try {
        	user = userService.update(userDto, id);
        } catch (Exception e) {
        	Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        	return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        };
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
    	Boolean response = false;
        try {
        	response = userService.deleteById(id);
        } catch (Exception e) {
        	Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        	return new ResponseEntity<Boolean>(response, HttpStatus.BAD_REQUEST);
        };
        return new ResponseEntity<Boolean>(response, HttpStatus.ACCEPTED);
    }
    
}