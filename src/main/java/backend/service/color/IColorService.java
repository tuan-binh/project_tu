package backend.service.color;

import backend.exception.CustomException;
import backend.model.dto.request.ColorRequest;
import backend.model.dto.response.ColorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IColorService {
	
	Page<ColorResponse> findAll(Pageable pageable, Optional<String> colorName);
	
	ColorResponse findById(Long colorId) throws CustomException;
	
	ColorResponse save(ColorRequest colorRequest);
	
	ColorResponse update(ColorRequest colorRequest, Long colorId);
	
	ColorResponse changeStatus(Long colorId) throws CustomException;
	
}
