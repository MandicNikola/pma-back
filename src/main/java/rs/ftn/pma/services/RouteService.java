package rs.ftn.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.pma.dto.RouteRequest;
import rs.ftn.pma.dto.RouteResponse;
import rs.ftn.pma.mappers.RouteMapper;
import rs.ftn.pma.model.Route;
import rs.ftn.pma.model.User;
import rs.ftn.pma.repository.RouteRepository;
import rs.ftn.pma.repository.UserRepository;

@Service
public class RouteService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RouteRepository routeRepository;

    public RouteResponse saveRoute(String username, RouteRequest request){
        User user = userRepository.findOneByUsername(username);
        Route route = RouteMapper.INSTANCE.mapRequestToRoute(request);
        route.setUser(user);
        route = routeRepository.save(route);
        RouteResponse routeResponse = RouteMapper.INSTANCE.mapToResponse(route);
        return  routeResponse;
    }
}
