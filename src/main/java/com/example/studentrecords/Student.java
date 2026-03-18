package com.example.studentrecords;

/**
 * Represents a student with general academic details.
 * Demonstrates encapsulation through private fields and accessor methods.
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double cgpa;

    /**
     * Creates a student with the provided values.
     *
     * @param id unique student identifier
     * @param name student name
     * @param age student age
     * @param course enrolled course name
     * @param cgpa current CGPA value
     */
    public Student(int id, String name, int age, String course, double cgpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.cgpa = cgpa;
    }

    /**
     * Gets the student ID.
     *
     * @return student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the student ID.
     *
     * @param id student ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the student name.
     *
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student name.
     *
     * @param name student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student age.
     *
     * @return student age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the student age.
     *
     * @param age student age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the enrolled course.
     *
     * @return course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the enrolled course.
     *
     * @param course course name
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Gets the CGPA.
     *
     * @return CGPA value
     */
    public double getCgpa() {
        return cgpa;
    }

    /**
     * Sets the CGPA.
     *
     * @param cgpa CGPA value
     */
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    /**
     * Returns a formatted description of the student.
     * Can be overridden by subclasses to provide specialized output.
     */
    public String display() {
        return String.format(
                "ID: %d | Name: %s | Age: %d | Course: %s | CGPA: %.2f | Type: Undergraduate",
                id,
                name,
                age,
                course,
                cgpa
        );
    }

    /**
     * Converts the student object into a text line for file storage.
     *
     * @return serialized student record
     */
    public String toFileString() {
        return String.join("|",
                "Student",
                String.valueOf(id),
                name,
                String.valueOf(age),
                course,
                String.valueOf(cgpa));
    }
}
