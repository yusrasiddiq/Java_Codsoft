import java.util.Scanner;

public class Student_grade_calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Display header
        System.out.println("******* Student Grade Calculator *******");

        // Input: Student name and roll number
        System.out.print("Enter the student's name: ");
        String studentName = sc.nextLine();
        System.out.print("Enter the student's roll number: ");
        String rollNumber = sc.nextLine();

        // Input: Number of subjects (limit to a maximum of 10)
        int numSubjects;
        while (true) {
            System.out.print("Enter the number of subjects (1 to 10): ");
            numSubjects = sc.nextInt();
            if (numSubjects >= 1 && numSubjects <= 10) break;
            System.out.println("Invalid input! The number of subjects must be between 1 and 10. Try again.");
        }

        sc.nextLine(); // Consume the newline character

        // Arrays to store subject codes, names, and marks
        String[] subjectCodes = new String[numSubjects];
        String[] subjectNames = new String[numSubjects];
        int[] marks = new int[numSubjects];
        int totalMarks = 0; // Initialize total marks

        // Input: Subject codes, names, and marks
        System.out.println("\nEnter the subject details (code, name, and marks):");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the code of subject " + (i + 1) + ": ");
            subjectCodes[i] = sc.nextLine();
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjectNames[i] = sc.nextLine();

            // Validate marks input
            while (true) {
                System.out.print("Enter marks for " + subjectNames[i] + " (out of 100): ");
                marks[i] = sc.nextInt();
                if (marks[i] >= 0 && marks[i] <= 100) break;
                System.out.println("Invalid input! Marks should be between 0 and 100. Try again.");
            }
            sc.nextLine(); // Consume the newline character after marks
            totalMarks += marks[i]; // Add marks to total
        }

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Determine Grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else if (averagePercentage >= 40) {
            grade = "E";
        } else {
            grade = "Fail (F)";
        }

        // Display Results
        System.out.println("\n******** Results ********");
        System.out.println("Student Name: " + studentName);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("\nSubject-wise Details:");

        // Print table header
        System.out.printf("%-15s %-20s %-10s\n", "Subject Code", "Subject Name", "Marks");
        System.out.println("--------------------------------------------------");

        // Print table rows
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("%-15s %-20s %-10d\n", subjectCodes[i], subjectNames[i], marks[i]);
        }

        // Print summary
        System.out.println("--------------------------------------------------");
        System.out.printf("%-35s %-10d\n", "Total Marks:", totalMarks);
        System.out.printf("%-35s %-10.2f%%\n", "Average Percentage:", averagePercentage);
        System.out.printf("%-35s %-10s\n", "Grade:", grade);
    }
}

