package ir.brandimo.training.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TrainingShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingShopApplication.class, args);
    }
    @Value("${welcome.message}")
    private String welcomeMessage;
    @GetMapping("/")
    public String hello(){
        return welcomeMessage;
    }
}
