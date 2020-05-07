package rs.ftn.pma.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rs.ftn.pma.dto.GoalRequest;
import rs.ftn.pma.dto.GoalResponse;
import rs.ftn.pma.model.Goals;

@Mapper
public interface GoalMapper {
    GoalMapper INSTANCE = Mappers.getMapper(GoalMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dateTime", target = "localDateTime")
    Goals mapToGoal(GoalRequest goalRequest);


    GoalResponse mapToResponse(Goals goal);

}
