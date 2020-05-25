package rs.ftn.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ftn.pma.utils.JwtUtil;

import java.util.ArrayList;

@RestController
@RequestMapping(value ="routes")
public class RouteController {

    @Autowired
    JwtUtil jwtUtil;

    // consider adding later -->`pageable`<-- here
    @GetMapping(value = "")
    public ResponseEntity<?> getRoutes(@RequestHeader("Authorization") String token) {
        // remove `Bearer ` part from token
        String username = jwtUtil.extractUsername(token.substring(7));
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }
}
