package backend.repository;

import backend.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
	
	Page<Category> findAllByCategoryNameContainingIgnoreCase(Pageable pageable, String categoryName);
	
}
