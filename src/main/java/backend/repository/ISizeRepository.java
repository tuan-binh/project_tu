package backend.repository;

import backend.model.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISizeRepository extends JpaRepository<Size,Long> {
	Page<Size> findAllBySizeNameContainingIgnoreCase(Pageable pageable, String sizeName);
}
