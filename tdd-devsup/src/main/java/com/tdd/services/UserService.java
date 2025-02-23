package com.tdd.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tdd.entities.User;
import com.tdd.repositories.RoleRepository;
import com.tdd.repositories.UserRepository;




@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository RoleRepository;
	
//	@Transactional(readOnly = true)
//	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
//		Page<User> list = repository.findAll(pageRequest);
//		return list.map(x -> new UserDTO(x));
//	}
//	
//	@Transactional(readOnly = true)
//	public UserDTO findById(Long id) {
//		Optional<User> obj = repository.findById(id);
//		User entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
//		return new UserDTO(entity);
//	}
//	
//	@Transactional
//	public UserDTO insert(UserInsertDTO dto) {
//		User entity = new User();
//		copyDtoToEntity(dto, entity);
//		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
//		entity = repository.save(entity);
//		return new UserDTO(entity);
//	}
//
//	@Transactional
//	public UserDTO update(Long id, UserUpdateDTO dto) {
//		try {		
//			User entity = repository.getOne(id);
//			copyDtoToEntity(dto, entity);		
//			entity = repository.save(entity);
//			return new UserDTO(entity);
//			
//		} catch(EntityNotFoundException e) {
//			throw new ResourceNotFoundException("Id not found " + id);
//		}		
//	}
//	
//
//	public void delete(Long id) {
//		try {
//		repository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException("Id not found " +  id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DatabaseException("Integrity violantion");
//		}
//		
//	}
//	private void copyDtoToEntity(UserDTO dto, User entity) {
//		entity.setFirstName(dto.getFirstName());
//		entity.setLastName(dto.getLastName());
//		entity.setEmail(dto.getEmail());
//				
//		entity.getRoles().clear();		
//		for(RoleDTO roleDTO : dto.getRoles()) {
//			Role role =  RoleRepository.getOne(roleDTO.getId());
//			entity.getRoles().add(role);
//		}
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("email não encontrado");
		}
		logger.info("User found: " + username);
		return user;
	}
}
