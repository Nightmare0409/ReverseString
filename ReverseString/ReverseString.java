// Importing the Scanner class.
import java.util.Scanner;

// Class that contains the main method.
public class ReverseString {

  // The string is taken as a parameter and then returned as a reversed string.
  public static String reverseString(String input) {

    // Converting the input string to a character array.
    char[] chars = input.toCharArray();

    // Initializing a new string builder to store the reversed string.
    StringBuilder reversed = new StringBuilder();

    // Loop through the character array from the end to the beginning.
    for (int i = chars.length - 1; i >= 0; i--) {

      // Append each character to the string builder.
      reversed.append(chars[i]);
    }
    // Return reversed string.
    return reversed.toString();
  }

  // Main method that tests the reverseString method.
  public static void main(String[] args) {

    // Creating a Scanner object that reads from System.in.
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter a string.
    System.out.println("Enter a string: ");

    // Getting the user input as a String.
    String inputString = input.nextLine();

    // Calling the reverseString method and print the result.
    System.out.println(reverseString(inputString)); 
    // Closing the Scanner object.
    input.close();
  }

}