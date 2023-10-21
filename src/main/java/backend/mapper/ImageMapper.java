package backend.mapper;

import backend.exception.CustomException;
import backend.mapper.IGenericMapper;
import backend.model.dto.request.ImageRequest;
import backend.model.dto.response.ImageResponse;
import backend.model.entity.ImageProduct;
import backend.model.entity.Product;
import backend.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageMapper implements IGenericMapper<ImageProduct, ImageRequest, ImageResponse> {
	
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public ImageProduct toEntity(ImageRequest imageRequest) throws CustomException {
		return ImageProduct.builder()
				  .url(imageRequest.getFiles())
				  .product(findProductById(imageRequest.getProductId()))
				  .build();
	}
	
	@Override
	public ImageResponse toResponse(ImageProduct imageProduct) {
		return ImageResponse.builder()
				  .id(imageProduct.getId())
				  .url(imageProduct.getUrl())
				  .build();
	}
	
	public Product findProductById(Long productId) throws CustomException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		return optionalProduct.orElseThrow(() -> new CustomException("product not found"));
	}
	
}
