package rs.ftn.pma.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import rs.ftn.pma.dto.UserResponse;
import rs.ftn.pma.dto.UserSettingRequest;
import rs.ftn.pma.mappers.UserMapper;
import rs.ftn.pma.mappers.UserSettingsMapper;
import rs.ftn.pma.model.User;
import rs.ftn.pma.model.UserSettings;
import rs.ftn.pma.repository.UserRepository;
import rs.ftn.pma.repository.UserSettingsRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingsRepository userSettingsRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUser(Long id) {
        return  userRepository.findOneById(id);
    }

    public UserResponse createUser(UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setLastName(user.getLastname());
        newUser.setName(user.getFirstname());
        newUser.setNumber(user.getPhone());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser = userRepository.save(newUser);
        return UserMapper.INSTANCE.mapToResponse(newUser);
    }

    public ResponseEntity<?> createSetting(UserSettingRequest userSettingRequest) {
        User user = userRepository.findOneById(userSettingRequest.getUserId());
        if(user.getSettings() != null) {
            HashMap<String, String> map = new HashMap<>();
            map.put("message", "user already has settings");
            map.put("code", "1212");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        UserSettings userSettings = UserSettingsMapper.INSTANCE.mapToUserSettings(userSettingRequest);
        userSettings.setUser(user);
        userSettings = userSettingsRepository.save(userSettings);
        return new ResponseEntity<>(UserSettingsMapper.INSTANCE.mapToUserSettingsResponse(userSettings),HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(s);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
