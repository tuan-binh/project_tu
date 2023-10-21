package backend.mapper;

import backend.mapper.IGenericMapper;
import backend.model.dto.request.CouponRequest;
import backend.model.dto.response.CouponResponse;
import backend.model.entity.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper implements IGenericMapper<Coupon, CouponRequest, CouponResponse> {
	@Override
	public Coupon toEntity(CouponRequest couponRequest) {
		return Coupon.builder()
				  .coupon(couponRequest.getCoupon())
				  .percent(couponRequest.getPercent())
				  .startDate(couponRequest.getStartDate())
				  .endDate(couponRequest.getEndDate())
				  .stock(couponRequest.getStock())
				  .status(couponRequest.isStatus())
				  .build();
	}
	
	@Override
	public CouponResponse toResponse(Coupon coupon) {
		return CouponResponse.builder()
				  .id(coupon.getId())
				  .coupon(coupon.getCoupon())
				  .percent(coupon.getPercent())
				  .startDate(coupon.getStartDate())
				  .endDate(coupon.getEndDate())
				  .stock(coupon.getStock())
				  .status(coupon.isStatus())
				  .build();
	}
}
