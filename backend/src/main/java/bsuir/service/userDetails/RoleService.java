package bsuir.service.userDetails;

import bsuir.model.userDetails.Role;

import java.util.List;


public interface RoleService {

    Role save(Role role);

    void delete(long id);

    List<Role> getAll();

    Role getById(long id);

}
