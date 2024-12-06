import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Course class to store course details
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;
    String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
        this.schedule = schedule;
    }

    // Check if course has available slots
    public boolean hasAvailableSlots() {
        return enrolledStudents < capacity;
    }

    // Enroll a student in the course
    public void enrollStudent() {
        if (hasAvailableSlots()) {
            enrolledStudents++;
        }
    }

    // Drop a student from the course
    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nEnrolled Students: " + enrolledStudents
                + "\nSchedule: " + schedule;
    }
}

// Student class to store student details and their registered courses
class Student {
    String studentId;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Register student for a course
    public boolean registerCourse(Course course) {
        if (course.hasAvailableSlots() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            return true;
        }
        return false;
    }

    // Drop a course
    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder courses = new StringBuilder();
        for (Course course : registeredCourses) {
            courses.append(course.courseCode).append(" ");
        }
        return "Student ID: " + studentId + "\nName: " + name + "\nRegistered Courses: " + courses.toString();
    }
}

public class StudentCourseRegistrationSystem {
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Display available courses
    public static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            if (course.hasAvailableSlots()) {
                System.out.println(course);
                System.out.println("---------------");
            }
        }
    }

    // Display student's registered courses
    public static void displayStudentCourses(Student student) {
        System.out.println("\nStudent's Registered Courses:");
        for (Course course : student.registeredCourses) {
            System.out.println(course.courseCode + " - " + course.title);
        }
    }

    // Main menu for course registration system
    public static void main(String[] args) {
        // Sample courses
        courses.add(new Course("CS101", "Computer Science 101", "Introduction to Computer Science", 30, "Mon-Wed 10:00 AM - 12:00 PM"));
        courses.add(new Course("CS102", "Data Structures", "Learn about data structures", 25, "Tue-Thu 2:00 PM - 4:00 PM"));
        courses.add(new Course("CS103", "Algorithms", "Introduction to Algorithms", 20, "Mon-Fri 9:00 AM - 11:00 AM"));

        // Sample students
        students.add(new Student("S001", "John Doe"));
        students.add(new Student("S002", "Jane Smith"));

        while (true) {
            System.out.println("\nWelcome to the Student Course Registration System!");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");

            int choice = -1;  // Default invalid value to handle incorrect input

            // Input validation for choice
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    if (choice >= 1 && choice <= 5) {
                        break;  // Exit the loop if the choice is valid
                    } else {
                        System.out.println("Invalid choice! Please enter a valid number between 1 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 5.");
                    sc.nextLine(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    displayCourses();
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    Student student = findStudentById(studentId);
                    if (student != null) {
                        displayCourses();
                        System.out.print("Enter Course Code to Register: ");
                        String courseCode = sc.nextLine();
                        Course course = findCourseByCode(courseCode);
                        if (course != null) {
                            if (student.registerCourse(course)) {
                                System.out.println("Successfully registered for " + course.title);
                            } else {
                                System.out.println("Registration failed. Course may be full or already registered.");
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextLine();
                    student = findStudentById(studentId);
                    if (student != null) {
                        displayStudentCourses(student);
                        System.out.print("Enter Course Code to Drop: ");
                        String courseCode = sc.nextLine();
                        Course course = findCourseByCode(courseCode);
                        if (course != null) {
                            if (student.dropCourse(course)) {
                                System.out.println("Successfully dropped " + course.title);
                            } else {
                                System.out.println("Drop failed. You are not registered for this course.");
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextLine();
                    student = findStudentById(studentId);
                    if (student != null) {
                        displayStudentCourses(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper function to find a student by ID
    public static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.studentId.equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Helper function to find a course by code
    public static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

