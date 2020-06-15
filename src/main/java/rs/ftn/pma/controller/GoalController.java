package rs.ftn.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ftn.pma.dto.GoalRequest;
import rs.ftn.pma.services.GoalService;
import rs.ftn.pma.utils.JwtUtil;

@RestController
@RequestMapping(value = "goals")
public class GoalController {

    @Autowired
    GoalService goalService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(value = "")
    public ResponseEntity<?> createGoal(@RequestBody GoalRequest goalRequest,@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));

        System.out.println("dosao u kontroler");
        try {
            System.out.println(" u try");
            return new ResponseEntity<>(goalService.createGoal(username, goalRequest), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/updateGoal")
    public ResponseEntity<?> updateGoal(Double currentValue, Long id, int flag){

        try {
            return new ResponseEntity<>(goalService.updateGoal(currentValue, id, flag), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
