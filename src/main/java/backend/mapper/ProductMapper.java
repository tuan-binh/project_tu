package backend.mapper;

import backend.exception.CustomException;
import backend.mapper.IGenericMapper;
import backend.model.dto.request.ProductRequest;
import backend.model.dto.request.ProductUpdate;
import backend.model.dto.response.ImageResponse;
import backend.model.dto.response.ProductDetailResponse;
import backend.model.dto.response.ProductResponse;
import backend.model.entity.Category;
import backend.model.entity.Product;
import backend.repository.ICategoryRepository;
import backend.repository.IImageRepository;
import backend.repository.IProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements IGenericMapper<Product, ProductRequest, ProductResponse> {
	
	@Autowired
	private ICategoryRepository categoryRepository;
	@Autowired
	private IImageRepository iImageRepository;
	@Autowired
	private IProductDetailRepository productDetailRepository;
	
	@Override
	public Product toEntity(ProductRequest productRequest) throws CustomException {
		return Product.builder()
				  .productName(productRequest.getProductName())
				  .description(productRequest.getDescription())
				  .bought(productRequest.getBought())
				  .category(findCategoryById(productRequest.getCategoryId()))
				  .status(productRequest.isStatus())
				  .build();
	}
	
	public Product toEntity(ProductUpdate productUpdate) throws CustomException {
		return Product.builder()
				  .productName(productUpdate.getProductName())
				  .description(productUpdate.getDescription())
				  .bought(productUpdate.getBought())
				  .category(findCategoryById(productUpdate.getCategoryId()))
				  .status(productUpdate.isStatus())
				  .build();
	}
	
	@Override
	public ProductResponse toResponse(Product product) {
		return ProductResponse.builder()
				  .id(product.getId())
				  .productName(product.getProductName())
				  .description(product.getDescription())
				  .image(product.getImage())
				  .bought(product.getBought())
				  .category(product.getCategory().getCategoryName())
				  .productDetails(findProductDetailByProductId(product.getId()))
				  .images(findImagesByProductId(product.getId()))
				  .status(product.isStatus())
				  .build();
	}
	
	public Category findCategoryById(Long categoryId) throws CustomException {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		return optionalCategory.orElseThrow(() -> new CustomException("category not found"));
	}
	
	public List<ProductDetailResponse> findProductDetailByProductId(Long productId) {
		return productDetailRepository.findAllByProductId(productId).stream()
				  .map(item -> ProductDetailResponse.builder()
							 .id(item.getId())
							 .price(item.getPrice())
							 .stock(item.getStock())
							 .product(item.getProduct().getProductName())
							 .color(item.getColor().getColorName())
							 .size(item.getSize().getSizeName())
							 .status(item.isStatus())
							 .build())
				  .collect(Collectors.toList());
	}
	
	public List<ImageResponse> findImagesByProductId(Long productId) {
		return iImageRepository.findAllByProductId(productId).stream()
				  .map(item -> ImageResponse.builder()
							 .id(item.getId())
							 .url(item.getUrl())
							 .build())
				  .collect(Collectors.toList());
	}
	
}
