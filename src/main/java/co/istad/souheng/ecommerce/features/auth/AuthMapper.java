package co.istad.souheng.ecommerce.features.auth;

import co.istad.souheng.ecommerce.features.auth.dto.RegisterResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AuthMapper {
    RegisterResponse mapUserRepresentationtoRegisterResponse(UserRepresentation userRepresentation) {
        return RegisterResponse.builder()
                .userid(userRepresentation.getId())
                .username(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstname(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .phoneNumber(userRepresentation.firstAttribute("phoneNumber"))
                .build();
    }

    ;

}
