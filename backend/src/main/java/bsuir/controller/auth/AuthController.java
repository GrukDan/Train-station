package bsuir.controller.auth;


import bsuir.model.auth.JwtRequest;
import bsuir.model.userDetails.User;
import bsuir.security.jwt.JwtProvider;
import bsuir.service.userDetails.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public ResponseEntity auth(@RequestBody JwtRequest request) {
        log.info(request.getUsername());
        User user = userService.findByEmailAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }
}
