package rs.ftn.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ftn.pma.dto.UserDto;
import rs.ftn.pma.model.User;
import rs.ftn.pma.repository.UserRepository;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUser(Long id) {
        return  userRepository.findOneById(id);
    }

    public User createUser(UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setLastName(user.getLastname());
        newUser.setName(user.getFirstname());
        newUser.setNumber(user.getPhone());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(s);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
