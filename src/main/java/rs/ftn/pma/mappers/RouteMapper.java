package rs.ftn.pma.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import rs.ftn.pma.dto.RouteRequest;
import rs.ftn.pma.dto.RouteResponse;
import rs.ftn.pma.model.Point;
import rs.ftn.pma.model.Route;

import java.time.LocalDateTime;
import java.util.*;

@Mapper
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(source = "startTime",target ="startTime", qualifiedByName = "datePrepare")
    @Mapping(source = "endTime",target ="endTime", qualifiedByName = "datePrepare")
    @Mapping(source = "points", target = "points", qualifiedByName = "pointsPrepare")
    @Mapping(source = "distance", target = "distance")
    Route mapRequestToRoute(RouteRequest request);

    @Named("datePrepare")
    static LocalDateTime prepareDate(String date){
        return LocalDateTime.parse(date);
    }

    @Named("pointsPrepare")
    static Set<Point> preparePoints(HashMap<String, List<Double>> points){
        Set<Point> pointsSet = new HashSet<>();
        TreeMap<String, List<Double>> treeMap = new TreeMap<>(new DateComparator());
        treeMap.putAll(points);
        for(String key : treeMap.keySet()){
            Point point = new Point();
            point.setLat(treeMap.get(key).get(0));
            point.setLng(treeMap.get(key).get(1));
            point.setTime(LocalDateTime.parse(key));
            pointsSet.add(point);
        }

        return  pointsSet;
    }

    RouteResponse mapToResponse(Route route);
}

class DateComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        LocalDateTime dateTime1 = LocalDateTime.parse(o1);
        LocalDateTime dateTime2 = LocalDateTime.parse(o2);
        return dateTime1.compareTo(dateTime2);
    }
}
