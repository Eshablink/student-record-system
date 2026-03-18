package com.example.studentrecords;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the StudentRecordSystem console application.
 * Provides a menu-driven interface for managing student records.
 */
public class App {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Starts the console application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent(manager);
                    break;
                case 2:
                    viewAllStudents(manager);
                    break;
                case 3:
                    searchStudent(manager);
                    break;
                case 4:
                    updateStudent(manager);
                    break;
                case 5:
                    deleteStudent(manager);
                    break;
                case 6:
                    manager.saveToFile();
                    System.out.println("Student records saved successfully.");
                    break;
                case 7:
                    manager.saveToFile();
                    System.out.println("Exiting StudentRecordSystem. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    /**
     * Prints the main menu options.
     */
    private static void printMenu() {
        System.out.println("\n===== Student Record System =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save Records");
        System.out.println("7. Exit");
    }

    /**
     * Adds either a regular or postgraduate student.
     *
     * @param manager student manager instance
     */
    private static void addStudent(StudentManager manager) {
        System.out.println("\nAdd Student");
        int id = readInt("ID: ");
        String name = readLine("Name: ");
        int age = readInt("Age: ");
        String course = readLine("Course: ");
        double cgpa = readDouble("CGPA: ");
        int typeChoice = readInt("Enter 1 for Student or 2 for PostgraduateStudent: ");

        Student student;
        if (typeChoice == 2) {
            String researchTopic = readLine("Research Topic: ");
            student = new PostgraduateStudent(id, name, age, course, cgpa, researchTopic);
        } else {
            student = new Student(id, name, age, course, cgpa);
        }

        if (manager.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student ID already exists. Record not added.");
        }
    }

    /**
     * Displays all students using polymorphic display behavior.
     *
     * @param manager student manager instance
     */
    private static void viewAllStudents(StudentManager manager) {
        System.out.println("\nAll Students");
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student.display());
        }
    }

    /**
     * Searches for and displays a student by ID.
     *
     * @param manager student manager instance
     */
    private static void searchStudent(StudentManager manager) {
        int id = readInt("Enter student ID to search: ");
        Student student = manager.searchById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println(student.display());
    }

    /**
     * Updates an existing student record.
     *
     * @param manager student manager instance
     */
    private static void updateStudent(StudentManager manager) {
        int id = readInt("Enter student ID to update: ");
        Student existingStudent = manager.searchById(id);
        if (existingStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter updated details.");
        String name = readLine("Name: ");
        int age = readInt("Age: ");
        String course = readLine("Course: ");
        double cgpa = readDouble("CGPA: ");

        Student updatedStudent;
        if (existingStudent instanceof PostgraduateStudent) {
            String researchTopic = readLine("Research Topic: ");
            updatedStudent = new PostgraduateStudent(id, name, age, course, cgpa, researchTopic);
        } else {
            int typeChoice = readInt("Enter 1 for Student or 2 for PostgraduateStudent: ");
            if (typeChoice == 2) {
                String researchTopic = readLine("Research Topic: ");
                updatedStudent = new PostgraduateStudent(id, name, age, course, cgpa, researchTopic);
            } else {
                updatedStudent = new Student(id, name, age, course, cgpa);
            }
        }

        if (manager.updateStudent(id, updatedStudent)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student could not be updated.");
        }
    }

    /**
     * Deletes a student record by ID.
     *
     * @param manager student manager instance
     */
    private static void deleteStudent(StudentManager manager) {
        int id = readInt("Enter student ID to delete: ");
        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Reads an integer value from the console with retry logic.
     *
     * @param prompt prompt shown to the user
     * @return entered integer value
     */
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(SCANNER.nextLine().trim());
                return value;
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    /**
     * Reads a decimal value from the console with retry logic.
     *
     * @param prompt prompt shown to the user
     * @return entered decimal value
     */
    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(SCANNER.nextLine().trim());
                return value;
            } catch (NumberFormatException exception) {
                System.out.println("Invalid decimal number. Please try again.");
            }
        }
    }

    /**
     * Reads a full line of text from the console.
     *
     * @param prompt prompt shown to the user
     * @return entered text
     */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }
}
