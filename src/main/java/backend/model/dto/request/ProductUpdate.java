package backend.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductUpdate {
	private String productName;
	private String description;
	private int bought;
	private Long categoryId;
	private boolean status;
}
