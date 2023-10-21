package backend.service.size;

import backend.exception.CustomException;
import backend.mapper.SizeMapper;
import backend.model.dto.request.SizeRequest;
import backend.model.dto.response.SizeResponse;
import backend.model.entity.Size;
import backend.repository.ISizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeService implements ISizeService {
	
	@Autowired
	private ISizeRepository sizeRepository;
	@Autowired
	private SizeMapper sizeMapper;
	
	@Override
	public Page<SizeResponse> findAll(Pageable pageable, Optional<String> sizeName) {
		List<SizeResponse> list = sizeName.map(s -> sizeRepository.findAllBySizeNameContainingIgnoreCase(pageable, s).stream()
				  .map(item -> sizeMapper.toResponse(item))
				  .collect(Collectors.toList())).orElseGet(() -> sizeRepository.findAll(pageable).stream()
				  .map(item -> sizeMapper.toResponse(item))
				  .collect(Collectors.toList()));
		return new PageImpl<>(list, pageable, list.size());
	}
	
	@Override
	public SizeResponse findById(Long sizeId) throws CustomException {
		Optional<Size> optionalSize = sizeRepository.findById(sizeId);
		return optionalSize.map(item -> sizeMapper.toResponse(item)).orElseThrow(() -> new CustomException("size not found"));
	}
	
	@Override
	public SizeResponse save(SizeRequest sizeRequest) {
		return sizeMapper.toResponse(sizeRepository.save(sizeMapper.toEntity(sizeRequest)));
	}
	
	@Override
	public SizeResponse update(SizeRequest sizeRequest, Long sizeId) {
		Size size = sizeMapper.toEntity(sizeRequest);
		size.setId(sizeId);
		return sizeMapper.toResponse(sizeRepository.save(size));
	}
	
	@Override
	public SizeResponse changeStatus(Long sizeId) throws CustomException {
		Optional<Size> optionalSize = sizeRepository.findById(sizeId);
		if (optionalSize.isPresent()) {
			Size size = optionalSize.get();
			size.setStatus(!size.isStatus());
			return sizeMapper.toResponse(sizeRepository.save(size));
		}
		throw new CustomException("size not found");
	}
	
}
