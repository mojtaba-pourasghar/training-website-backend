package ir.brandimo.training.shop;

import ir.brandimo.training.shop.entity.RoleEntity;
import ir.brandimo.training.shop.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class TrainingShopApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TrainingShopApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TrainingShopApplication.class, args);
    }
    @Value("${welcome.message}")
    private String welcomeMessage;
    @GetMapping("/")
    public String hello(){
        return welcomeMessage;
    }

//    @Bean
//    CommandLineRunner runner(RoleService roleService) {
//        return args -> {
//            RoleEntity role = new RoleEntity();
//            role.setName("test");
//            role.setCreateDate(14011208);
//            role.setDescription("test");
////            role.setId(null);
//
//            roleService.createRole(role);
//        };
//    }
}
