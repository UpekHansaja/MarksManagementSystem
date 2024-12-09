import java.util.Scanner;

public class MarksManagementSystem {
    public static final int totalStudents = 100;
    public static String[] studentId = new String[totalStudents];
    public static String[] studentName = new String[totalStudents];
    public static int[] progMarks = new int[totalStudents];
    public static int[] dbmsMarks = new int[totalStudents];
    public static int studentCount = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner != null) {
            System.out.println("");
            System.out.println("------------------------------------------------------");
            System.out.println("|         Welcome to Marks Management System         |");
            System.out.println("------------------------------------------------------");
            System.out.println("");
            System.out.println("[1] Add New Student");
            System.out.println("[2] Add New Student With Marks");
            System.out.println("[3] Add Marks");
            System.out.println("[4] Update Student Details");
            System.out.println("[5] Update Marks");
            System.out.println("[6] Delete Student");
            System.out.println("[7] Print Student Details");
            System.out.println("[8] Print Student Ranks");
            System.out.println("[9] Best in Programming Fundamentals");
            System.out.println("[10] Best in Database Management System");
            System.out.println("[11] Exit");
            System.out.println("");
            System.out.print("Enter an option to continue: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                addNewStudent(scanner);
                break;
            } else if (choice == 2) {
                addNewStudentWithMarks(scanner);
                break;
            } else if (choice == 3) {
                addMarks(scanner);
                break;
            } else if (choice == 4) {
                updateStudentDetails(scanner);
                break;
            } else if (choice == 5) {
                updateMarks(scanner);
                break;
            } else if (choice == 6) {
                deleteStudent(scanner);
                break;
            } else if (choice == 7) {
                printStudentDetails(scanner);
                break;
            } else if (choice == 8) {
                printStudentRanks();
                break;
            } else if (choice == 9) {
                bestInProgrammingFundamentals();
                break;
            } else if (choice == 10) {
                bestInDBMS();
                break;
            } else if (choice == 11) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid option. Try again.");
                break;
            }
        }
    }

    static void addNewStudent(Scanner scanner) {

        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            if (findStudentIndexById(id) != -1) {
                System.out.println("Student ID already exists. Try again.");
                continue;
            }
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            studentId[studentCount] = id;
            studentName[studentCount] = name;
            progMarks[studentCount] = -1; // -1 indicates marks not added
            dbmsMarks[studentCount] = -1;
            studentCount++;

            System.out.println("Student added successfully. Do you want to add a new student? (Y/n): ");

            String choice = scanner.nextLine();
            if (choice.equals("n")) {
                main(null);
            } else {
                continue;
            }
        }
    }

    static void addNewStudentWithMarks(Scanner scanner) {
        addNewStudent(scanner);
        addMarksForStudent(scanner, studentCount - 1);
    }

    static void addMarks(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Invalid Student ID. Try again.");
            return;
        }
        if (progMarks[index] != -1 && dbmsMarks[index] != -1) {
            System.out.println("Marks already added for this student.");
            return;
        }
        addMarksForStudent(scanner, index);
    }

    static void addMarksForStudent(Scanner scanner, int index) {
        while (true) {
            System.out.print("Enter Programming Fundamentals Marks (0-100): ");
            int pfMarks = scanner.nextInt();
            if (pfMarks < 0 || pfMarks > 100) {
                System.out.println("Invalid marks. Try again.");
                continue;
            }
            progMarks[index] = pfMarks;
            break;
        }
        while (true) {
            System.out.print("Enter Database Management System Marks (0-100): ");
            int dbmsMarksInput = scanner.nextInt();
            if (dbmsMarksInput < 0 || dbmsMarksInput > 100) {
                System.out.println("Invalid marks. Try again.");
                continue;
            }
            dbmsMarks[index] = dbmsMarksInput;
            break;
        }
        System.out.println("Marks added successfully.");
    }

    static void updateStudentDetails(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Invalid Student ID. Try again.");
            return;
        }
        System.out.print("Enter New Name: ");
        studentName[index] = scanner.nextLine();
        System.out.println("Student details updated successfully.");
    }

    static void updateMarks(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Invalid Student ID. Try again.");
            return;
        }
        if (progMarks[index] == -1 && dbmsMarks[index] == -1) {
            System.out.println("Marks not added for this student.");
            return;
        }
        addMarksForStudent(scanner, index);
    }

    static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Invalid Student ID. Try again.");
            return;
        }
        for (int i = index; i < studentCount - 1; i++) {
            studentId[i] = studentId[i + 1];
            studentName[i] = studentName[i + 1];
            progMarks[i] = progMarks[i + 1];
            dbmsMarks[i] = dbmsMarks[i + 1];
        }
        studentCount--;
        System.out.println("Student deleted successfully.");
    }

    static void printStudentDetails(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Invalid Student ID. Try again.");
            return;
        }
        System.out.println("Student ID: " + studentId[index]);
        System.out.println("Student Name: " + studentName[index]);
        if (progMarks[index] == -1 || dbmsMarks[index] == -1) {
            System.out.println("Marks: Not added yet.");
        } else {
            int total = progMarks[index] + dbmsMarks[index];
            double average = total / 2.0;
            System.out.println("Programming Fundamentals Marks: " + progMarks[index]);
            System.out.println("Database Management System Marks: " + dbmsMarks[index]);
            System.out.println("Total Marks: " + total);
            System.out.println("Average Marks: " + average);
        }
    }

    static void printStudentRanks() {
        int[] totalMarks = new int[studentCount];
        int[] ranks = new int[studentCount];
        for (int i = 0; i < studentCount; i++) {
            if (progMarks[i] != -1 && dbmsMarks[i] != -1) {
                totalMarks[i] = progMarks[i] + dbmsMarks[i];
            } else {
                totalMarks[i] = -1;
            }
        }
        for (int i = 0; i < studentCount; i++) {
            ranks[i] = 1;
            for (int j = 0; j < studentCount; j++) {
                if (totalMarks[j] > totalMarks[i]) {
                    ranks[i]++;
                }
            }
        }
        System.out.println("\n--- Student Ranks ---");
        for (int i = 0; i < studentCount; i++) {
            if (totalMarks[i] != -1) {
                System.out.println("Rank " + ranks[i] + ": " + studentName[i] + " (Total: " + totalMarks[i] + ")");
            }
        }
    }

    static void bestInProgrammingFundamentals() {
        int maxIndex = -1;
        int maxMarks = -1;
        for (int i = 0; i < studentCount; i++) {
            if (progMarks[i] > maxMarks) {
                maxMarks = progMarks[i];
                maxIndex = i;
            }
        }
        if (maxIndex == -1) {
            System.out.println("No students have marks in Programming Fundamentals.");
        } else {
            System.out.println("Best in Programming Fundamentals: " + studentName[maxIndex] + " (" + maxMarks + ")");
        }
    }

    static void bestInDBMS() {
        int maxIndex = -1;
        int maxMarks = -1;
        for (int i = 0; i < studentCount; i++) {
            if (dbmsMarks[i] > maxMarks) {
                maxMarks = dbmsMarks[i];
                maxIndex = i;
            }
        }
        if (maxIndex == -1) {
            System.out.println("No students have marks in Database Management System.");
        } else {
            System.out.println("Best in Database Management System: " + studentName[maxIndex] + " (" + maxMarks + ")");
        }
    }

    static int findStudentIndexById(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (studentId[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
