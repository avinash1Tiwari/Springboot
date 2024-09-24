package comjpaintro.example.demojpaintro.Repositories;

import comjpaintro.example.demojpaintro.Entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
}
