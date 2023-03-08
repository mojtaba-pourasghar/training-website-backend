package ir.brandimo.training.shop.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;

}
