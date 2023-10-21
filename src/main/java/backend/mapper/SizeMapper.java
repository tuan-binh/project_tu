package backend.mapper;

import backend.exception.CustomException;
import backend.mapper.IGenericMapper;
import backend.model.dto.request.SizeRequest;
import backend.model.dto.response.SizeResponse;
import backend.model.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeMapper implements IGenericMapper<Size, SizeRequest, SizeResponse> {
	
	@Override
	public Size toEntity(SizeRequest sizeRequest) throws CustomException {
		return Size.builder()
				  .sizeName(sizeRequest.getSizeName())
				  .status(sizeRequest.isStatus())
				  .build();
	}
	
	@Override
	public SizeResponse toResponse(Size size) {
		return SizeResponse.builder()
				  .id(size.getId())
				  .sizeName(size.getSizeName())
				  .status(size.isStatus())
				  .build();
	}
}
