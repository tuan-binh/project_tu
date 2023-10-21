package backend.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String url;
	
	private String categoryName;
	
	private boolean status;
	
}
