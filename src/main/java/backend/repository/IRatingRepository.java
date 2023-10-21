package backend.repository;

import backend.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRatingRepository extends JpaRepository<Rating,Long> {
}
