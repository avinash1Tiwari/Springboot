package comjpaintro.example.demojpaintro;

import comjpaintro.example.demojpaintro.Entities.Users;
import comjpaintro.example.demojpaintro.Repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemojpaintroApplication {

	public static void main(String[] args) {

//		SpringApplication.run(DemojpaintroApplication.class, args);
		ApplicationContext context = SpringApplication.run(DemojpaintroApplication.class, args);
		UserRepository userRepository =  context.getBean(UserRepository.class);    // making bean

		Users user = new Users();
		user.setId(2);
		user.setName("ram");
		user.setCity("everywhere");

		Users user1 = userRepository.save(user);
		System.out.println(user1);

		System.out.println("user-details");
		System.out.println(user);
	}





}