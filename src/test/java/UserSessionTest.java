import com.example.sleepwell.initialization.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserSessionTest {
    @BeforeEach
    void setUp() {
        // Reset UserSession for each test
        UserSession.cleanUserSession();
    }

    @Test
    void testInitialize() {
        // Testing initialization of the UserSession singleton
        UserSession.initialize("Johnny101", 1);
        UserSession result = UserSession.getInstance();
        assertNotNull(result, "Instance should not be null after initialization");
        assertEquals("Johnny101", result.getUsername(), "Username should return Johnny101");
        assertEquals(1, result.getUserId(), "userId should return 1");
    }

    @Test
    void testGetInstance() {
        // Expect an IllegalStateException when getInstance is called without prior initialization
        assertThrows(IllegalStateException.class, UserSession::getInstance, "Expected an IllegalStateException to be thrown when getInstance is called without initialization");
    }

    @Test
    void testCleanUserSession() {
        // Testing the cleaning of UserSession singleton
        UserSession.initialize("Johnny101", 1);
        assertNotNull(UserSession.getInstance());
        UserSession.cleanUserSession();
        assertThrows(IllegalStateException.class, UserSession::getInstance, "Should throw exception after cleanUserSession");
    }

    @Test
    void testGetUsername() {
        UserSession.initialize("Johnny101", 1);
        UserSession result = UserSession.getInstance();
        assertEquals("Johnny101", result.getUsername(), "Username should return Johnny101");
    }

    @Test
    void getUserId() {
        UserSession.initialize("Johnny101", 1);
        UserSession result = UserSession.getInstance();
        assertEquals(1, result.getUserId(), "Username should return 1");
    }

    @Test
    void testSetBrightness() {
        UserSession.setBrightness(-0.95);
        assertEquals(-0.95, UserSession.getBrightness(), "getBrightness should return -0.95");
    }

    @Test
    void testGetBrightness() {
        UserSession.setBrightness(-0.25);
        assertEquals(-0.25, UserSession.getBrightness(), "getBrightness should return -0.25");
    }
}
