package ir.brandimo.training.shop.dto.auth;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
}
