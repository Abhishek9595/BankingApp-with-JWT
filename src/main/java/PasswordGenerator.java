import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
		System.out.println(bEncoder.encode("123"));
	}

}
