package netology.cloudservice;

import netology.cloudservice.dto.LoginInRequest;
import netology.cloudservice.entity.UserEntity;
import netology.cloudservice.repository.TokenRepository;
import netology.cloudservice.repository.UserRepository;
import netology.cloudservice.service.AuthorizationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

class AuthorizationServiceTest {
    public static final String AUTH_TOKEN = "Start";
    public static final String UNKNOWN_AUTH_TOKEN = "1234";
    public static final String EXISTING_USER = "user1";
    public static final String NOT_EXISTING_USER = "user2";
    public static final String CORRECT_PASSWORD = "password1";

    private final UserRepository userRepository = createUserRepositoryMock();
    private final TokenRepository tokenRepository = createTokenRepositoryMock();

    private UserRepository createUserRepositoryMock() {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findById(EXISTING_USER)).thenReturn(Optional.of(new UserEntity(EXISTING_USER, CORRECT_PASSWORD)));
        when(userRepository.findById(NOT_EXISTING_USER)).thenReturn(Optional.empty());
        return userRepository;
    }

    private TokenRepository createTokenRepositoryMock() {
        final TokenRepository tokenRepository = Mockito.mock(TokenRepository.class);
        when(tokenRepository.existsById(AUTH_TOKEN.split(" ")[1].trim())).thenReturn(true);
        when(tokenRepository.existsById(UNKNOWN_AUTH_TOKEN)).thenReturn(false);
        return tokenRepository;
    }

//    @Test
//    void login() {
//        final AuthorizationService authorizationService = new AuthorizationService(userRepository, tokenRepository);
//        Assertions.assertDoesNotThrow(() -> authorizationService.login(new LoginInRequest(EXISTING_USER, CORRECT_PASSWORD)));
//    }
//
//    @Test
//    void login_userNotFound() {
//        final AuthorizationService authorizationService = new AuthorizationService(userRepository, tokenRepository);
//        Assertions.assertThrows(RuntimeException.class, () -> authorizationService.login(new LoginInRequest(NOT_EXISTING_USER, CORRECT_PASSWORD)));
//    }
//
//    @Test
//    void login_incorrectPassword() {
//        final AuthorizationService authorizationService = new AuthorizationService(userRepository, tokenRepository);
//        Assertions.assertThrows(RuntimeException.class, () -> authorizationService.login(new LoginInRequest(EXISTING_USER, "qwerty")));
//    }
//
//    @Test
//    void logout() {
//        final AuthorizationService authorizationService = new AuthorizationService(userRepository, tokenRepository);
//        Assertions.assertDoesNotThrow(() -> authorizationService.logout(AUTH_TOKEN));
//    }
//
//    @Test
//    void checkToken() {
//        final AuthorizationService authorizationService = new AuthorizationService(userRepository, tokenRepository);
//        Assertions.assertDoesNotThrow(() -> authorizationService.checkToken(AUTH_TOKEN));
//    }
//
//    @Test
//    void checkToken_failed() {
//        final AuthorizationService authorizationService = new AuthorizationService(userRepository, tokenRepository);
//        Assertions.assertThrows(RuntimeException.class, () -> authorizationService.checkToken(UNKNOWN_AUTH_TOKEN));
//    }
}