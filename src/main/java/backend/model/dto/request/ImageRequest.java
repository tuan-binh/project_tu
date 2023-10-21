package backend.model.dto.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ImageRequest {
	private String files;
	private Long productId;
}
