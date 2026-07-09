package co.istad.souheng.ecommerce.features.userprofile;

import co.istad.souheng.ecommerce.features.userprofile.dto.UserProfileMapper;
import co.istad.souheng.ecommerce.features.userprofile.dto.UserProfileResponse;
import co.istad.souheng.ecommerce.security.KeycloakAdminProps;
import co.istad.souheng.ecommerce.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final Keycloak keycloak;
    private final KeycloakAdminProps props;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse me() {
        // 1. Profile from Keycloak by userId
        String userId = SecurityUtils.extractUserId();
        UserRepresentation keycloakUser = keycloak.realm(props.getTargetRealm())
                .users()
                .get(userId)
                .toRepresentation();

        // 2. Profile from Database by userId
        return userProfileMapper.mapUserRepresentationToUserProfileResponse(keycloakUser);
    }

}