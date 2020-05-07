package rs.ftn.pma.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rs.ftn.pma.dto.UserResponse;
import rs.ftn.pma.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "name", target = "firstname")
    @Mapping(source = "lastName", target = "lastname")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    UserResponse mapToResponse(User user);
}
