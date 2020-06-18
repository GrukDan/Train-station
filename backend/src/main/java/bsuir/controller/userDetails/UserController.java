package bsuir.controller.userDetails;


import bsuir.model.pageModel.UserPage;
import bsuir.model.userDetails.User;
import bsuir.service.userDetails.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private HttpHeaders createHeaders(String header, String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, content);
        return headers;
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResponseEntity<String> save(@RequestBody User user) {
        return userService.save(user) != null ?
                ResponseEntity.ok(null)
                : new ResponseEntity<>(
                null,
                createHeaders("Custom-Header", "can not create user"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return updatedUser != null ?
                ResponseEntity.ok(updatedUser)
                : ResponseEntity.badRequest().body(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> update(@RequestParam("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/get-by-id", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@RequestParam("id") long id) {
        User user = userService.getById(id);
        return user != null ?
                ResponseEntity.ok(user)
                : ResponseEntity.badRequest().body(user);
    }

    @RequestMapping(value = "/get-parameters", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getSortParameters() {
        return ResponseEntity.ok(userService.getSortParameters());
    }

    @RequestMapping(value = "/get-page", method = RequestMethod.GET)
    public ResponseEntity<UserPage> getPageSorted(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("parameter") String parameter,
            @RequestParam("direction") boolean direction) {
        return ResponseEntity.ok(userService.getPage(page, size, direction, parameter));
    }
}
