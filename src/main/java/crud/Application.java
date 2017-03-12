package crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    UserRepository repository;
    
    public static void main(String[] args) {

        SpringApplication.run(Application.class);
    }


    @Override
    public void run(String... strings) throws Exception {

    }

}
