package backend.model.dto.response;

import backend.model.entity.OrderStatus;
import backend.model.entity.Rating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
	private Long id;
	private OrderStatus orderStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
	private Date time;
	private String location;
	private String phone;
	private double total;
	private Rating rating;
	private boolean status;
}
