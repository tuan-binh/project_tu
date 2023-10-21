package backend.mapper;

import backend.exception.CustomException;
import backend.mapper.IGenericMapper;
import backend.model.dto.request.RatingRequest;
import backend.model.dto.response.RatingResponse;
import backend.model.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper implements IGenericMapper<Rating, RatingRequest, RatingResponse> {
	
	@Override
	public Rating toEntity(RatingRequest ratingRequest) throws CustomException {
		return Rating.builder()
				  .rate(ratingRequest.getRate())
				  .content(ratingRequest.getContent())
				  .created(ratingRequest.getCreated())
				  .build();
	}
	
	@Override
	public RatingResponse toResponse(Rating rating) {
		return RatingResponse.builder()
				  .id(rating.getId())
				  .rate(rating.getRate())
				  .content(rating.getContent())
				  .created(rating.getCreated())
				  .build();
	}
}
