package edu.eci.ieti.restfullapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.ieti.restfullapi.data.User;
import edu.eci.ieti.restfullapi.dto.UserDto;
import edu.eci.ieti.restfullapi.service.UserService;

@Service
public class UserServiceHashMap implements UserService {
	
	private Map<String, User> users;
	
	public UserServiceHashMap() {
		users = new HashMap<String, User>();
	}
	
	@Override
	public User create(User user) {
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public User findById(String id) {
		return users.get(id);
	}

	@Override
	public List<User> getAll() {
		List<User> allUsers = new ArrayList<>(users.values());
		return allUsers;
	}

	@Override
	public Boolean deleteById(String id) {
		Boolean wasDeleted;
		User deletedUser = users.remove(id);
		if (deletedUser != null) {
			wasDeleted = true;
		}
		else {
			wasDeleted = false;
		}
		return wasDeleted;
	}

	@Override
	public User update(UserDto userDto, String userId) {
		User user = users.get(userId);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setLastName(userDto.getLastName());
		users.put(userId, user);
		return users.get(userId);
	}
	
}