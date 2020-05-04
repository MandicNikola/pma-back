package rs.ftn.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ftn.pma.dto.AuthRequest;
import rs.ftn.pma.dto.AuthResponse;
import rs.ftn.pma.dto.UserDto;
import rs.ftn.pma.model.User;
import rs.ftn.pma.services.UserService;
import rs.ftn.pma.utils.JwtUtil;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping(value="/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="")
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        try {
            return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest credentials) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        System.out.println("PPPPP");
        final UserDetails userDetails = userService
                .loadUserByUsername(credentials.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
