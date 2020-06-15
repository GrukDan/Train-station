package bsuir.service.userDetails.Impl;

import bsuir.model.userDetails.Role;
import bsuir.repository.userDetails.RoleRepository;
import bsuir.service.userDetails.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role getById(long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
