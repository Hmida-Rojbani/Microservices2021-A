package de.tekup.user.data.repos;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import de.tekup.user.data.models.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUserId(String userId);

}
