package rs.ftn.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.pma.dto.GoalRequest;
import rs.ftn.pma.dto.GoalResponse;
import rs.ftn.pma.mappers.GoalMapper;
import rs.ftn.pma.model.Goals;
import rs.ftn.pma.model.User;
import rs.ftn.pma.repository.GoalRepository;
import rs.ftn.pma.repository.UserRepository;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserRepository userRepository;

    public GoalResponse createGoal(String username, GoalRequest goal) {
        User user = userRepository.findOneByUsername(username);
        Goals newGoal = GoalMapper.INSTANCE.mapToGoal(goal);
        newGoal.setUser(user);
        newGoal = goalRepository.save(newGoal);
        return GoalMapper.INSTANCE.mapToResponse(newGoal);
    }

}
