package applicationuser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ApplicationUserService.class)
class ApplicationUserServiceTest {

    @Mock
    private ApplicationUserService applicationUserService;

    @InjectMocks
    private ApplicationUserRepository applicationUserRepository;

    @Test
    @DisplayName("Testing the test to see if it works")
    void test() throws Exception{
        assertThat(applicationUserService).isNotNull();
    }
  
}