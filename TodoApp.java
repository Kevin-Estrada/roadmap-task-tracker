import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TodoApp {
    Scanner scanner = new Scanner(System.in);
    List<Task> listOfTodos = new ArrayList<>();
    boolean endMenu = true;

    public void startTodo() {
        listOfTodos.add(new Task("Eat"));
        listOfTodos.add(new Task("Shit"));
        listOfTodos.add(new Task("Sleep"));
        System.out.println("Welcome to the TODO application:");
        cycleMenu();
    }

    public void cycleMenu() {
        String userInput;
        while (endMenu) {
            try {
                System.out.println("Please select from the options below:");
                System.out.println("1: Add New Task");
                System.out.println("2: Update Task");
                System.out.println("3: Delete Existing Task");
                System.out.println("4: Update Task Status");
                System.out.println("5: List All Tasks");
                System.out.println("6: List Tasks by status");
                System.out.println("7: Exit");
                System.out.println();
                System.out.print("User Input: ");
                userInput = scanner.nextLine();
                System.out.println();
                int number = Integer.parseInt(userInput);
                switch (number) {
                    // Add task
                    case 1:
                        addTask();
                        break;
                    // Update Task
                    case 2:
                        System.out.println("Enter the task you would like to update: ");
                        listAllTodos();
                        System.out.print("\nUser Input: ");
                        int passedId = Integer.parseInt(scanner.nextLine());
                        if (checkIfIdIsValid(passedId)) {
                            System.out.print("Enter the description you would like to replace above with: ");
                            String desc = scanner.nextLine();
                            updateTask(desc, passedId);
                        } else {
                            System.out.println();
                            System.out.println("Id: " + passedId + " is not a valid ID.");
                        }
                        break;
                    // Delete Task
                    case 3:
                        System.out.println("Enter the task you would like to delete: ");
                        listAllTodos();
                        System.out.print("\nUser Input: ");
                        int passedIdToDelete = Integer.parseInt(scanner.nextLine());
                        if (checkIfIdIsValid(passedIdToDelete)) {
                            deleteTask(passedIdToDelete);
                        } else {
                            System.out.println();
                            System.out.println("Id: " + passedIdToDelete + " is not a valid ID.");
                        }
                        break;
                    case 5:
                        listAllTodos();
                        break;
                    case 4:
                        System.out.println("Choose which task you would like to update: ");
                        listAllTodos();
                        int input = Integer.parseInt(scanner.nextLine());
                        System.out.println();
                        if (checkIfIdIsValid(input)) {
                            System.out.println("Enter what status to mark your task below");
                            listTask(input);
                            System.out.println("1. Mark In Progress");
                            System.out.println("2. Mark To Do");
                            System.out.println("3. Mark Done");
                            int choice = Integer.parseInt(scanner.nextLine());
                            String action = "";
                            if (choice == 1) {
                                action = "In Progress";
                            } else if (choice == 2) {
                                action = "To Do";
                            } else if (choice == 3) {
                                action = "Done";
                            } else {
                                System.out.println("Input was invalid. Please try again.");
                            }
                            progressStatus(action, input);
                        } else {
                            System.out.println();
                            System.out.println("Id: " + input + " is not a valid ID.");
                        }
                        break;
                    case 6:
                        System.out.println("Please choose which action to list out by");
                        System.out.println("1. In Progress");
                        System.out.println("2. To Do");
                        System.out.println("3. Done");
                        int statusInput = Integer.parseInt(scanner.nextLine());
                        String action = "";
                        if (statusInput == 1) {
                            action = "In Progress";
                        } else if (statusInput == 2) {
                            action = "To Do";
                        } else if (statusInput == 3) {
                            action = "Done";
                        } else {
                            System.out.println("Input was invalid. Please try again.");
                        }
                        listTasksByStatus(action);
                        break;
                    case 7:
                        System.out.println("Thank you for using the TODO App.");
                        endMenu = false;
                        break;

                    default:
                        System.out.println("Invalid number choice, try again");
                        break;
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number: " + e.getMessage());
            }
        }

    }

    public void addTask() {
        System.out.println("Please enter what task you would like to have added: ");
        try {
            String desc = scanner.nextLine();
            Task newTask = new Task(desc);
            listOfTodos.add(newTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(String desc, int id) {
        listOfTodos.get(id - 1).setDescription(desc);
        listOfTodos.get(id - 1).setUpdatedAt(new Date());
    }

    public void deleteTask(int id) {
        // listOfTodos.stream().filter((item) -> item.getId() == id)

        Task taskToDelete = listOfTodos.get(id - 1);
        System.out.println("You have removed the task with id " + id + " and descritpion of: "
                + taskToDelete.getDescription());
        listOfTodos.remove(id - 1);
    }

    public void listAllTodos() {
        int count = 1;
        if (listOfTodos.isEmpty()) {
            System.out.println("There is nothing yet on the Todo List. Please add new entries");
        } else {
            for (Task task : listOfTodos) {
                System.out.println(count + ". " + task.getDescription() + " || " + task.getStatus() + " || "
                        + task.getCreatedAt() + " || " + task.getUpdatedAt());
                count++;
            }
        }
    }

    public void progressStatus(String action, int id) {
        if (action.equals("In Progress")) {
            listOfTodos.get(id - 1).setStatus("In Progress");
            listOfTodos.get(id - 1).setUpdatedAt(new Date());
        } else if (action.equals("To Do")) {
            listOfTodos.get(id - 1).setStatus("Todo");
            listOfTodos.get(id - 1).setUpdatedAt(new Date());
        } else if (action.equals("Done")) {
            listOfTodos.get(id - 1).setStatus("Done");
            listOfTodos.get(id - 1).setUpdatedAt(new Date());
        } else {
            System.out.println("Not a valid input.");
        }
    }

    public boolean checkIfIdIsValid(int id) {
        // return listOfTodos.stream().equals(listOfTodos.get(id - 1));
        try {
            if (listOfTodos.get(id - 1) != null) {
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void listTask(int id) {
        Task task = listOfTodos.get(id - 1);
        System.out.println("Task Descritpion: " + task.getDescription());
        System.out.println("Task Status: " + task.getStatus());
        System.out.println("Task Created Date: " + task.getCreatedAt());
        System.out.println("Task Updated Date: " + task.getUpdatedAt());
    }

    public void listTasksByStatus(String status) {
        List<Task> list = listOfTodos.stream().filter((task) -> status.equals(task.getStatus()))
                .collect(Collectors.toList());
        if (list.size() != 0) {
            list.stream().forEach((task) -> System.out.println(task.toString()));
        } else {
            System.out.println("List is empty, there are no tasks with " + status + " status in list.");
        }
    }
}
