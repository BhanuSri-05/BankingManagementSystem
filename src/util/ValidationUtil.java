package util;

public final class ValidationUtil {

    // Prevent object creation
    private ValidationUtil() {
    }

    // Validate Phone Number (Exactly 10 digits)
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    // Validate Email
    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // Validate Aadhaar Number (Exactly 12 digits)
    public static boolean isValidAadhaar(String aadhaar) {
        return aadhaar != null && aadhaar.matches("\\d{12}");
    }

    // Validate Amount (Greater than 0)
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    // Validate Customer ID
    public static boolean isValidCustomerId(int customerId) {
        return customerId > 0;
    }

    // Validate Account Number
    public static boolean isValidAccountNumber(int accountNumber) {
        return accountNumber > 0;
    }

    // Validate Customer Name
    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 3;
    }
}
