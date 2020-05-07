package rs.ftn.pma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ftn.pma.enums.GOAL_KEYS;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalResponse {

    private Long id;

    private GOAL_KEYS goalKey;

    private double goalValue;

    private LocalDateTime localDateTime;
}
