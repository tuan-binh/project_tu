package backend.model.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRegister {
	private String fullName;
	private String email;
	private String password;
	private List<String> roles = new ArrayList<>();
}
