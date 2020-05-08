package rs.ftn.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ftn.pma.dto.GoalRequest;
import rs.ftn.pma.services.GoalService;

@RestController
@RequestMapping(value = "goals")
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping(value = "")
    public ResponseEntity<?> createGoal(@RequestBody GoalRequest goalRequest) {
        try {
            return new ResponseEntity<>(goalService.createGoal(goalRequest), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
