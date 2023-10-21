package backend.mapper;

import backend.mapper.IGenericMapper;
import backend.model.dto.request.ColorRequest;
import backend.model.dto.response.ColorResponse;
import backend.model.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper implements IGenericMapper<Color, ColorRequest, ColorResponse> {
	@Override
	public Color toEntity(ColorRequest colorRequest) {
		return Color.builder()
				  .colorName(colorRequest.getColorName())
				  .status(colorRequest.isStatus())
				  .build();
	}
	
	@Override
	public ColorResponse toResponse(Color color) {
		return ColorResponse.builder()
				  .id(color.getId())
				  .colorName(color.getColorName())
				  .status(color.isStatus())
				  .build();
	}
}
