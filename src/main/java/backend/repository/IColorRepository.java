package backend.repository;

import backend.model.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColorRepository extends JpaRepository<Color,Long> {
	Page<Color> findAllByColorNameContainingIgnoreCase(Pageable pageable, String colorName);
}
