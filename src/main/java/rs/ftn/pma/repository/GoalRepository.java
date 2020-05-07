package rs.ftn.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ftn.pma.model.Goals;

@Repository
public interface GoalRepository extends JpaRepository<Goals, Long> {
}
