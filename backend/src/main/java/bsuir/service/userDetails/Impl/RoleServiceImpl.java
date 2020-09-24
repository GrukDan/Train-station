package bsuir.service.userDetails.Impl;

import bsuir.model.userDetails.Role;
import bsuir.repository.userDetails.RoleRepository;
import bsuir.service.userDetails.RoleService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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

    @Override
    public Role findByRole(String role) throws ChangeSetPersister.NotFoundException {
        return roleRepository.findByRole(role).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
