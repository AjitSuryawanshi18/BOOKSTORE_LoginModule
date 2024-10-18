package loginScripts;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {


    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            // Valid Email & Password
            {"ajitsuryawanshi65392@gmail.com", "1234", true},   // Valid email and password
            
            // Invalid Password (valid email, wrong password)
            {"ajitsuryawanshi65392@gmail.com", "wrongPass", false},  
            
            // Invalid Email (valid password)
            {"invalidemail@gmail.com", "1234", false},       
            
            // Invalid Email & Password (both wrong)
            {"invalidemail@gmail.com", "wrongPass", false},    
            
            // Empty Email and Valid Password
            {"", "1234", false},      
            
            // Valid Email and Empty Password
            {"ajitsuryawanshi65392@gmail.com", "", false},  
            
            // Both Fields Empty
            {"", "", false},    
            
            // Invalid Email Formats (boundary & format cases)
            {"invalidemail@", "1234", false},  // Missing domain
            {"@gmail.com", "1234", false},  // Missing local part
            {"invalidemail.com", "1234", false},  // Missing '@' symbol
            {"invalidemail@com", "1234", false},  // Missing top-level domain (TLD)
            {"ajit@.com", "1234", false},  // No domain name
            {"ajit@domain.co1", "1234", false},  // Invalid TLD (no numbers allowed)
            
            // Email Boundary Cases
            {"a@b.co", "1234", true},  // Shortest valid email
            {"a@b.c", "1234", false},  // Invalid: Domain too short
            {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@gmail.com", "1234", true},  // Maximum valid email length (local part = 30)
            {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaa@domain.com", "1234", false},  // Exceeding maximum email length
            
            // Password Boundary Cases
            {"ajitsuryawanshi65392@gmail.com", "123", false},  // Password less than 4 characters
            {"ajitsuryawanshi65392@gmail.com", "123456789012345678901", false},  // Password greater than max length (20 chars)

            // Special Characters in Email
            {"email!@gmail.com", "1234", false},  // Invalid special characters in email
            {"email_@gmail.com", "1234", true},   // Valid special character in email

            // Password with Special Characters
            {"ajitsuryawanshi65392@gmail.com", "pass@123", true},   // Password with special characters
            {"ajitsuryawanshi65392@gmail.com", "pa$$word", true},   // Password with symbols
            {"ajitsuryawanshi65392@gmail.com", "pa ssw ord", false},   // Password with spaces
            
            // Leading/Trailing Whitespaces
            {" ajitsuryawanshi65392@gmail.com", "1234", false},  // Leading space in email
            {"ajitsuryawanshi65392@gmail.com ", "1234", false},  // Trailing space in email
            {"ajitsuryawanshi65392@gmail.com", " 1234", false},  // Leading space in password
            {"ajitsuryawanshi65392@gmail.com", "1234 ", false},  // Trailing space in password

            // SQL Injection-like Inputs (security check)
            {"ajitsuryawanshi65392@gmail.com", "' OR 1=1 --", false},  // SQL injection attempt
            {"ajitsuryawanshi65392@gmail.com", "<script>alert('hack')</script>", false},  // XSS attempt

            // Cross-Site Scripting (XSS) Edge Cases (email and password)
            {"<script>alert('hack')</script>", "1234", false},  // XSS in email field
            {"ajitsuryawanshi65392@gmail.com", "<script>alert('hack')</script>", false},  // XSS in password field
            
            // Extremely long email/password for buffer overflow test
            {"verylongemailthatexceedstheexpectedlimit@domain.com", "1234", false},  // Email too long
            {"ajitsuryawanshi65392@gmail.com", "verylongpasswordthatexceedsthemaxlimitofpasswordfield", false}  // Password too long
        };
    }
}
