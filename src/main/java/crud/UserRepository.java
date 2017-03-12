package crud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameStartsWithIgnoreCase(String name);
}
