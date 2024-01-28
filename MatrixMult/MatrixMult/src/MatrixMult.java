import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class MatrixMult {
    public static void main(String[] args) throws IOException {
        // Checking the number of command-line arguments.
        if (args.length == 2) {
            // If two arguments are provided, read matrices from files, multiply them, and write the result to a file.
            String filePath1 = args[0];
            String filePath2 = args[1];
            int[][] matrix1 = readMatrixFromFile(filePath1);
            int[][] matrix2 = readMatrixFromFile(filePath2);
            int[][] result = multiplyMatrix(matrix1, matrix2);
            writeMatrixToFile(result, "matrix3.txt");
        } else if (args.length == 1) {
            // If one argument is provided, generate random matrices, multiply them, and write the result to a file.
            int size = Integer.parseInt(args[0]);
            int[][] matrix1 = generateRandomMatrix(size);
            int[][] matrix2 = generateRandomMatrix(size);
            writeMatrixToFile(matrix1, "matrix1.txt");
            writeMatrixToFile(matrix2, "matrix2.txt");
            int[][] result = multiplyMatrix(matrix1, matrix2);
            writeMatrixToFile(result, "matrix3.txt");
        } else {
            // If no or invalid arguments are provided, prompt user for input.
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter file names or an integer: ");
            String input = scanner.nextLine().trim();
            scanner.close();

            try {
                // Try to parse the input as an integer.
                int size = Integer.parseInt(input);
                System.out.println("Integer input detected: " + size);
                // Generate random matrices, multiply them, and write the result to a file
                int[][] matrix1 = generateRandomMatrix(size);
                int[][] matrix2 = generateRandomMatrix(size);
                writeMatrixToFile(matrix1, "matrix1.txt");
                writeMatrixToFile(matrix2, "matrix2.txt");
                int[][] result = multiplyMatrix(matrix1, matrix2);
                writeMatrixToFile(result, "matrix3.txt");
            } catch (NumberFormatException e) {
                // If input cannot be parsed as an integer, assume it's file names.
                String[] fileNames = input.split(" ");
                if (fileNames.length != 2) {
                    System.out.println("Invalid input!");
                    return;
                }
            
                System.out.println("File names detected: " + fileNames[0] + ", " + fileNames[1]);
                // Read matrices from files, multiply, and write the result to file.
                int[][] matrix1 = readMatrixFromFile(fileNames[0]);
                int[][] matrix2 = readMatrixFromFile(fileNames[1]);
                int[][] result = multiplyMatrix(matrix1, matrix2);
                writeMatrixToFile(result, "matrix3.txt");
            }
        }
    }

    // Read a matrix from file.
    public static int[][] readMatrixFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;
        int cols = 0;

        // Determine the number of rows/columns in the matrix.
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            cols = elements.length;
            rows++;
        }

        // Reset the reader to read from the beginning of the file.
        reader.close();
        reader = new BufferedReader(new FileReader(filePath));

        // Creating the matrix with the determined size.
        int[][] matrix = new int[rows][cols];

        // Reading the matrix values from the file.
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(elements[col]);
            }
            row++;
        }

        reader.close();
        return matrix;
    }

    // Writing a matrix to a file.
    public static void writeMatrixToFile(int[][] matrix, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        // Writing the matrix values to the file.
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                writer.write(matrix[row][col] + " ");
            }
            writer.newLine();
        }

        writer.close();
    }

    // Generate a random square matrix of the given size.
    public static int[][] generateRandomMatrix(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];
    
        // Initialize square matrix with random values.
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = random.nextInt(5); // Adjust if necessary
            }
        }
    
        return matrix;
    }    

    // Multiply two matrices.
    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        // Check if the two matrices can be multiplied.
        if (cols1 != rows2) {
            System.out.println("The matrices cannot be multiplied!");
            return null;
        }

        // Creating the resultant matrix.
        int[][] result = new int[rows1][cols2];

        // Multiplying the matrices.
        for (int row = 0; row < rows1; row++) {
            for (int col = 0; col < cols2; col++) {
                for (int i = 0; i < cols1; i++) {
                    result[row][col] += matrix1[row][i] * matrix2[i][col];
                }
            }
        }

        return result;
    }
}
