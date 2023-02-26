package ir.brandimo.training.shop.event;


import ir.brandimo.training.shop.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegisterationCompleteEvent extends ApplicationEvent {

    private UserEntity user;
    private String applicaionUrl;

    public RegisterationCompleteEvent(UserEntity user, String applicaionUrl) {
        super(user);
        this.user = user;
        this.applicaionUrl = applicaionUrl;
    }
}
