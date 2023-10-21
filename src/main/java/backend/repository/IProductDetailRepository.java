package backend.repository;

import backend.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDetailRepository extends JpaRepository<ProductDetail,Long> {
	
	List<ProductDetail> findAllByProductId(Long productId);
	
}
