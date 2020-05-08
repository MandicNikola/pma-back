package rs.ftn.pma.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rs.ftn.pma.dto.UserDto;
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

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "firstname", target = "name")
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "phone", target = "number")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password", qualifiedByName = "passwordPrepare")
    User mapRequestToUser(UserDto userDto);

    @Named("passwordPrepare")
    static String preparePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
