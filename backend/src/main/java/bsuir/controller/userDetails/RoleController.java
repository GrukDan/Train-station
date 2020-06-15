package bsuir.controller.userDetails;

import bsuir.model.userDetails.Role;
import bsuir.service.userDetails.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(roleService.getAll());
    }
}
