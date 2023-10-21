package backend.mapper;

import backend.mapper.IGenericMapper;
import backend.model.dto.request.CategoryRequest;
import backend.model.dto.response.CategoryResponse;
import backend.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements IGenericMapper<Category, CategoryRequest, CategoryResponse> {
	@Override
	public Category toEntity(CategoryRequest categoryRequest) {
		return Category.builder()
				  .categoryName(categoryRequest.getCategoryName())
				  .url(categoryRequest.getUrl())
				  .status(categoryRequest.isStatus())
				  .build();
	}
	
	@Override
	public CategoryResponse toResponse(Category category) {
		return CategoryResponse.builder()
				  .id(category.getId())
				  .categoryName(category.getCategoryName())
				  .url(category.getUrl())
				  .status(category.isStatus())
				  .build();
	}
}
