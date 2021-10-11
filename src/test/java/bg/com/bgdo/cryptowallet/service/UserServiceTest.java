package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.controller.request.RegisterRequest;
import bg.com.bgdo.cryptowallet.model.User;
import bg.com.bgdo.cryptowallet.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder bcryptEncoder;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateNewUserInstaceInactiveToSave() {
    when(userRepository.save(any()))
      .thenReturn(null);

    RegisterRequest user = new RegisterRequest();
    user.setUsername("testUser");
    user.setPassword("testPass");

    User newUser = userService.newUser(user);
    assertThat(newUser.getUsername()).isEqualTo("testUser");
    assertThat(newUser.getId()).isNotEmpty();
    assertThat(newUser.getActive()).isFalse();
  }
}
