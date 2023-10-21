package backend.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ImageResponse {
	private Long id;
	private String url;
}
