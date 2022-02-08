package edu.eci.ieti.restfullapi.service;

import java.util.List;

import edu.eci.ieti.restfullapi.data.User;
import edu.eci.ieti.restfullapi.dto.UserDto;

public interface UserService {
	
	User create( User user );

    User findById( String id );
    
    List<User> getAll();

    Boolean deleteById( String id );

    User update( UserDto userDto, String userId );
    
}