package backend.service.size;

import backend.exception.CustomException;
import backend.model.dto.request.SizeRequest;
import backend.model.dto.response.SizeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISizeService {
	Page<SizeResponse> findAll(Pageable pageable, Optional<String> sizeName);
	
	SizeResponse findById(Long sizeId) throws CustomException;
	
	SizeResponse save(SizeRequest sizeRequest);
	
	SizeResponse update(SizeRequest sizeRequest, Long sizeId);
	
	SizeResponse changeStatus(Long sizeId) throws CustomException;
}
