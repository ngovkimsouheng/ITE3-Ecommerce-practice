package co.istad.souheng.ecommerce.features.auth;

import co.istad.souheng.ecommerce.features.auth.dto.RegisterRequest;
import co.istad.souheng.ecommerce.features.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);
}
