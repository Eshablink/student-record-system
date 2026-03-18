package com.example.studentrecords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages student records in memory and persists them to a text file.
 */
public class StudentManager {
    private static final String FILE_NAME = "students.txt";
    private final List<Student> students = new ArrayList<>();

    /**
     * Loads student records from disk when the manager is created.
     */
    public StudentManager() {
        loadFromFile();
    }

    /**
     * Adds a new student if the ID does not already exist.
     *
     * @param student student to add
     * @return true if added, otherwise false
     */
    public boolean addStudent(Student student) {
        if (searchById(student.getId()) != null) {
            return false;
        }
        students.add(student);
        return true;
    }

    /**
     * Returns all student records.
     *
     * @return list of students
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * Searches for a student by ID.
     *
     * @param id student ID
     * @return matching student or null if not found
     */
    public Student searchById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /**
     * Updates an existing student record by replacing it with the supplied object.
     *
     * @param id student ID to update
     * @param updatedStudent replacement student object
     * @return true if updated, otherwise false
     */
    public boolean updateStudent(int id, Student updatedStudent) {
        for (int index = 0; index < students.size(); index++) {
            if (students.get(index).getId() == id) {
                students.set(index, updatedStudent);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a student record by ID.
     *
     * @param id student ID to remove
     * @return true if deleted, otherwise false
     */
    public boolean deleteStudent(int id) {
        Student student = searchById(id);
        if (student == null) {
            return false;
        }
        students.remove(student);
        return true;
    }

    /**
     * Saves all student records to the text file.
     */
    public void saveToFile() {
        Path path = Paths.get(FILE_NAME);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Failed to save student records: " + exception.getMessage());
        }
    }

    /**
     * Loads student records from the text file into memory.
     */
    public final void loadFromFile() {
        students.clear();
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException exception) {
            System.out.println("Failed to load student records: " + exception.getMessage());
        }
    }

    /**
     * Converts a line from the storage file into a Student or PostgraduateStudent object.
     *
     * @param line line from the file
     * @return parsed student object or null if invalid
     */
    private Student parseStudent(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 6) {
            return null;
        }

        try {
            String type = parts[0];
            int id = Integer.parseInt(parts[1]);
            String name = parts[2];
            int age = Integer.parseInt(parts[3]);
            String course = parts[4];
            double cgpa = Double.parseDouble(parts[5]);

            if ("PostgraduateStudent".equalsIgnoreCase(type) && parts.length >= 7) {
                return new PostgraduateStudent(id, name, age, course, cgpa, parts[6]);
            }
            return new Student(id, name, age, course, cgpa);
        } catch (NumberFormatException exception) {
            System.out.println("Skipped invalid record: " + line);
            return null;
        }
    }
}
