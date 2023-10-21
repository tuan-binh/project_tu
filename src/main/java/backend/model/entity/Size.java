package backend.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sizes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Size {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String sizeName;
	
	private boolean status;
	
}
