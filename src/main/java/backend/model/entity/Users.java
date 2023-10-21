package backend.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private String password;
	
	private String phone;
	
	private String address;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "role_detail",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			  name = "favourite",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private Set<Product> favourite = new HashSet<>();
	
	// status user ( activity or blocked )
	private boolean status;
	
}
