package co.istad.souheng.ecommerce.features.auth.dto;

import lombok.Builder;

@Builder
public record RegisterResponse (

        String userid,
        String username,

        String email,

        String firstname,

        String lastName,

        String phoneNumber

){
}
