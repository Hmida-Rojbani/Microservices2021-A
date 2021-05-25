package de.tekup.user.services;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.tekup.user.data.models.UserEntity;
import de.tekup.user.data.repos.UserRepository;
import de.tekup.user.ui.models.UserDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
	
	private UserRepository repository;
	private ModelMapper mapper;
	
	public UserDTO saveUserToDB(UserDTO userDTO) {
		UserEntity user = mapper.map(userDTO, UserEntity.class);
		user.setUserID(UUID.randomUUID().toString());
		UserEntity userSaved = repository.save(user);
		return mapper.map(userSaved, UserDTO.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		return new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true,
				new ArrayList<>());
	}

	public UserDTO findUserByEmail(String email) {
		UserEntity userEntity = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		return mapper.map(userEntity, UserDTO.class);
	}

}
