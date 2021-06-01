import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	private @Getter @Setter Integer employeeId;
	private @Getter @Setter String name;
	private @Getter @Setter String company;
	private @Getter @Setter String emailId;
}
