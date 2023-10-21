package backend.repository;

import backend.model.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<ImageProduct,Long> {
	List<ImageProduct> findAllByProductId(Long productId);
}
