
import java.util.logging.Logger;
import java.util.Scanner;

public class MyBigNumber {
    private static final Logger LOGGER = Logger.getLogger(MyBigNumber.class.getName());

    public static String sum(String stn1, String stn2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        int i = stn1.length() - 1;
        int j = stn2.length() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = (i >= 0) ? Character.getNumericValue(stn1.charAt(i--)) : 0;
            int digit2 = (j >= 0) ? Character.getNumericValue(stn2.charAt(j--)) : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);

            LOGGER.info(String.format("Added digits: %d + %d = %d, Carry: %d", digit1, digit2, sum % 10, carry));
        }

        if (carry > 0) {
            result.insert(0, carry);
            LOGGER.info("Final carry: " + carry);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LOGGER.info("Enter the first number (stn1): ");
        String stn1 = scanner.nextLine();

        LOGGER.info("Enter the second number (stn2): ");
        String stn2 = scanner.nextLine();

        String result = sum(stn1, stn2);
        LOGGER.info("Sum of " + stn1 + " and " + stn2 + " is: " + result);

        scanner.close();
    }
}
