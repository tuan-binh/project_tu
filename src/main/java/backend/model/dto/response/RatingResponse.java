package backend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RatingResponse {
	private Long id;
	private int rate;
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
	private Date created;
}
