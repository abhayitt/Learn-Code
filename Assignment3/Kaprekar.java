import java.util.Scanner;

public class Kaprekar {

    public static int[] getDigitsArray(int number) {
        int numDigits = 4; 
        int[] digits = new int[numDigits];

        for (int i = numDigits - 1; i >= 0; i--) {
            digits[i] = number % 10;
            number /= 10;
        }

        return digits;
    }
    

    public static int convertAscending(int number) {
        // Convert number----->array of digits
        int[] digits = getDigitsArray(number);

        //sort in ascending order
        for (int i = 0; i < digits.length - 1; i++) {
            for (int j = 0; j < digits.length - i - 1; j++) {
                if (digits[j] > digits[j + 1]) {

                    int temp = digits[j];
                    digits[j] = digits[j + 1];
                    digits[j + 1] = temp;
                }
            }
        }

        // Convert sorted array------>integer
        int result = 0;
        for (int digit : digits) {
            result = result * 10 + digit;
        }

        return result;
    }

    public static int convertDescending(int number) {
        // Convert number--->array of digits
        int[] digits = getDigitsArray(number);

        //sort in descending order
        for (int i = 0; i < digits.length - 1; i++) {
            for (int j = 0; j < digits.length - i - 1; j++) {
                if (digits[j] < digits[j + 1]) {
                    int temp = digits[j];
                    digits[j] = digits[j + 1];
                    digits[j + 1] = temp;
                }
            }
        }

        // Convert sorted array-->integer
        int result = 0;
        for (int digit : digits) {
            result = result * 10 + digit;
        }

        return result;
    }

    public static int kaprekarConstant(int number) {
        int steps = 0;

        while (number != 6174) {
            int ascending = convertAscending(number);
            int descending = convertDescending(number);
            number = descending - ascending;
            steps++;
        }

        return steps;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 4 digit Number: ");
        int number = input.nextInt();

        if (number < 1000 || number > 9999) {
            System.out.println("Invalid input. Please enter a 4-digit number");
        } else {
            int totalSteps = kaprekarConstant(number);
            System.out.println("Total Steps are: " + totalSteps);
        }
    }
}
