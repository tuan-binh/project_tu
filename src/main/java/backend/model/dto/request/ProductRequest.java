package backend.model.dto.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
	private String productName;
	private String description;
	private int bought;
	private Long categoryId;
	private List<String> files;
	private boolean status;
}
