package backend.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
	private Long id;
	private String url;
	private String categoryName;
	private boolean status;
}
