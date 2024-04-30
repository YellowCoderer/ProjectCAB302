package com.example.sleepwell.database;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsTest {  // Naming for a test class

    private Accounts account; // Use the Accounts model

    @BeforeEach
    public void setUp() {
        // Instantiate the Accounts object
        account = new Accounts("John", "Doe", "john.doe@example.com", "password123");
    }

    @Test
    public void testGetId() {
        account.setId(1);
        assertEquals(1, account.getId(), "ID should be 1");
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", account.getFirstName(), "First name should be John");
    }

    @Test
    public void testSetFirstName() {
        account.setFirstName("Jane");
        assertEquals("Jane", account.getFirstName(), "First name should be changed to Jane");
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", account.getLastName(), "Last name should be Doe");
    }

    @Test
    public void testSetLastName() {
        account.setLastName("Smith");
        assertEquals("Smith", account.getLastName(), "Last name should be changed to Smith");
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", account.getEmail(), "Email should match");
    }

    @Test
    public void testSetEmail() {
        account.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", account.getEmail(), "Email should be updated");
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", account.getPassword(), "Password should match");
    }

    @Test
    public void testSetPassword() {
        account.setPassword("newpassword");
        assertEquals("newpassword", account.getPassword(), "Password should be updated");
    }

    @Test
    public void testGetFullName() {
        assertEquals("John Doe", account.getFullName(), "Full name should be 'John Doe'");
    }
}
