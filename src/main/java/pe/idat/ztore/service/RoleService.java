package pe.idat.ztore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.ztore.model.Role;
import pe.idat.ztore.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Role createNewRole(Role role) {
		return roleRepo.save(role);
	}
}
