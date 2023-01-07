package ir.brandimo.training.shop.event.listeners;


import ir.brandimo.training.shop.entity.User;
import ir.brandimo.training.shop.event.RegisterationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegisterationCompleteEventListener implements ApplicationListener<RegisterationCompleteEvent> {

   // @Autowired
   // private UserService userService;


    @Override
    public void onApplicationEvent(RegisterationCompleteEvent event) {
        // Create the verification token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        //userService.saveVerificationTokenForUser(token, user);
        //Send email to user
        String url = event.getApplicaionUrl() + "/verifyRegistration?token=" + token;
        log.info("Click the link to verify your account: {}",url);
    }
}
