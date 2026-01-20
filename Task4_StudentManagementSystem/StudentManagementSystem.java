import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("✅ Student added successfully");
    }

    public void removeStudent(int rollNo) {
        students.removeIf(s -> s.getRollNo() == rollNo);
        saveToFile();
        System.out.println("🗑 Student removed");
    }

    public Student searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s);
            }
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading data");
        }
    }
}
