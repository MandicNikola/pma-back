package rs.ftn.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ftn.pma.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
