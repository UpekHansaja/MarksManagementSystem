import java.util.Scanner;

public class MarksManagementSystem {
    static final int maxStudentCount = 100;
    static String[] studentId = new String[maxStudentCount];
    static String[] studentName = new String[maxStudentCount];
    static int[] progMarks = new int[maxStudentCount];
    static int[] dbmsMarks = new int[maxStudentCount];
    static int currentStudentCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    addNewStudentWithMarks(scanner);
                    break;
                case 3:
                    addMarks(scanner);
                    break;
                case 4:
                    updateStudentDetails(scanner);
                    break;
                case 5:
                    updateMarks(scanner);
                    break;
                case 6:
                    deleteStudent(scanner);
                    break;
                case 7:
                    printStudentDetails(scanner);
                    break;
                case 8:
                    printStudentRanks();
                    break;
                case 9:
                    bestInProgrammingFundamentals();
                    break;
                case 10:
                    bestInDBMS();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
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
            studentId[currentStudentCount] = id;
            studentName[currentStudentCount] = name;
            progMarks[currentStudentCount] = -1;
            dbmsMarks[currentStudentCount] = -1;
            currentStudentCount++;
            System.out.println("Student added successfully.");
            break;
        }
    }

    static void addNewStudentWithMarks(Scanner scanner) {
        addNewStudent(scanner);
        addMarksForStudent(scanner, currentStudentCount - 1);
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
        for (int i = index; i < currentStudentCount - 1; i++) {
            studentId[i] = studentId[i + 1];
            studentName[i] = studentName[i + 1];
            progMarks[i] = progMarks[i + 1];
            dbmsMarks[i] = dbmsMarks[i + 1];
        }
        currentStudentCount--;
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
        int[] totalMarks = new int[currentStudentCount];
        int[] ranks = new int[currentStudentCount];
        for (int i = 0; i < currentStudentCount; i++) {
            if (progMarks[i] != -1 && dbmsMarks[i] != -1) {
                totalMarks[i] = progMarks[i] + dbmsMarks[i];
            } else {
                totalMarks[i] = -1;
            }
        }
        for (int i = 0; i < currentStudentCount; i++) {
            ranks[i] = 1;
            for (int j = 0; j < currentStudentCount; j++) {
                if (totalMarks[j] > totalMarks[i]) {
                    ranks[i]++;
                }
            }
        }
        System.out.println("\n--- Student Ranks ---");
        for (int i = 0; i < currentStudentCount; i++) {
            if (totalMarks[i] != -1) {
                System.out.println("Rank " + ranks[i] + ": " + studentName[i] + " (Total: " + totalMarks[i] + ")");
            }
        }
    }

    static void bestInProgrammingFundamentals() {
        int maxIndex = -1;
        int maxMarks = -1;
        for (int i = 0; i < currentStudentCount; i++) {
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
        for (int i = 0; i < currentStudentCount; i++) {
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
        for (int i = 0; i < currentStudentCount; i++) {
            if (studentId[i].equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
