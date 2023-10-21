package backend.repository;

import backend.model.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISizeRepository extends JpaRepository<Size,Long> {
}
