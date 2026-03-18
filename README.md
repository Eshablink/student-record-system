# StudentRecordSystem

## What the project does
StudentRecordSystem is a Java console application for managing student records. It allows users to add, view, search, update, and delete student entries from a menu-driven interface. The application also persists records in a `students.txt` file so data is loaded automatically on startup and can be saved for future sessions.

## How to run it
1. Compile the project:
   ```bash
   javac -d out src/main/java/com/example/studentrecords/*.java
   ```
2. Run the application:
   ```bash
   java -cp out com.example.studentrecords.App
   ```
3. Use the numbered menu options in the console to manage student records.

## OOP concepts demonstrated
- **Encapsulation:** `Student` and `PostgraduateStudent` use private fields with getters and setters.
- **Inheritance:** `PostgraduateStudent` extends `Student` and adds a `researchTopic` field.
- **Polymorphism:** `PostgraduateStudent` overrides the `display()` method to provide specialized output.
- **Abstraction through management logic:** `StudentManager` centralizes data storage, record operations, and file persistence.
