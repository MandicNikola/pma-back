package rs.ftn.pma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ftn.pma.enums.GOAL_KEYS;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {

    private LocalDateTime dateTime;

    private GOAL_KEYS goalKey;

    private double goalValue;

    private Long userId;
}
