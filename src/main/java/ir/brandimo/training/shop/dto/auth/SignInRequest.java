package ir.brandimo.training.shop.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SignInRequest {
    private String email;
    private String password;
}
