import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A simple To-Do List application that allows users to:
 * 1. Add tasks
 * 2. View tasks
 * 3. Remove tasks
 * 4. Exit the program
 *
 * Uses an ArrayList to store tasks and Scanner for user input.
 */
public class ToDoListApp {
    // Stores the list of tasks
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        int choice; // Stores user menu choice

        // Loop to continuously show menu until user exits
        do {
            // Display menu options
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Edit Tasks");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Read user input (menu choice)
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character left after nextInt()

            // Handle user choice using a switch statement
            switch (choice) {
                case 1 -> addTask(scanner); // Call method to add a task
                case 2 -> viewTasks(); // Call method to display tasks
                case 3 -> editTasks(scanner); // Call method to edit a task
                case 4 -> removeTask(scanner); // Call method to remove a task
                case 5 -> System.out.println("Exiting..."); // Exit message
                default -> System.out.println("Invalid choice. Try again."); // Handle invalid input
            }
        } while (choice != 4); // Loop until user selects option 4 (Exit)

        scanner.close(); // Close scanner to prevent memory leaks
    }

    /**
     * Method to add a new task to the list.
     * @param scanner Scanner object for user input.
     */
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine(); // Read task from user
        System.out.print("Enter deadline (Y/N): ");
        String DeadlineCheck = scanner.nextLine(); // Ask if a deadline should be given
        if (DeadlineCheck.equalsIgnoreCase("y") || DeadlineCheck.equalsIgnoreCase("yes")){
            System.out.print("Enter deadline: ");
            task = task + " | Due " + scanner.nextLine(); // Append deadline onto task
            tasks.add(task); // Add task to the list
        } else {

            tasks.add(task); // Add task to the list
            System.out.println("Task added!"); // Confirmation message
        }
    }

    /**
     * Method to display all tasks in the list.
     */
    private static void viewTasks() {
        if (tasks.isEmpty()) { // Check if the list is empty
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nYour Tasks:");
            // Loop through the list and display each task with a number
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Method to edit a task in the list.
     * @param scanner Scanner object for user input.
     */
    private static void editTasks(Scanner scanner){
        if (tasks.isEmpty()) { // Check if the list is empty
            System.out.println("No tasks available for edit.");
        } else {
            viewTasks(); // Show current tasks before asking for input
            System.out.print("Enter task number to edit: ");
            int index = scanner.nextInt(); // Get task number from user
            scanner.nextLine(); // Consume newline

            // Validate the task number before editing
            if (index > 0 && index <= tasks.size()) {
                String[] deadLine = tasks.get(index - 1).split(" \\| Due "); // Save previous deadline
                System.out.print("Enter new description for the task: ");
                String newTaskDescription = scanner.nextLine(); // Read new task description
                System.out.print("Add/Change Deadline (Y/N): ");
                String DeadlineCheck = scanner.nextLine(); // Ask if a deadline should be given
                if (DeadlineCheck.equalsIgnoreCase("y") || DeadlineCheck.equalsIgnoreCase("yes")) {
                    System.out.print("Enter deadline: ");
                    newTaskDescription = newTaskDescription + " | Due " + scanner.nextLine(); // Append deadline onto task
                    tasks.set(index - 1, newTaskDescription); // Update task with new description
                } else {
                    newTaskDescription = newTaskDescription + " | Due " + deadLine[1]; // reattach deadline if unchanged
                    System.out.println("Task updated!"); // Confirmation message
                    tasks.set(index - 1, newTaskDescription); // Update task with new description
                }
            } else {
                System.out.println("Invalid task number."); // Handle invalid input
            }
        }
    }

    /**
     * Method to remove a task from the list.
     * @param scanner Scanner object for user input.
     */
    private static void removeTask(Scanner scanner) {
        viewTasks(); // Show current tasks before asking for input
        if (tasks.isEmpty()) return; // If no tasks, exit method

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt(); // Get task number from user

        // Validate the task number before removing
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1); // Remove task (index is 1-based, ArrayList is 0-based)
            System.out.println("Task removed."); // Confirmation message
        } else {
            System.out.println("Invalid task number."); // Handle invalid input
        }
    }
}
