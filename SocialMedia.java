import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SocialMedia {
    private ArrayList<User> users;
    private Scanner scanner;
    private User loggedInUser;

    public SocialMedia() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        loggedInUser = null;
        loadUsers();
    }

    public void registerUser() {
        System.out.print("Enter a username to sign up: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        if (findUser(username) != null) {
            System.out.println("Username already taken. Please try a different username.");
        } else {
            User newUser = new User(username, password);
            users.add(newUser);
            loggedInUser = newUser;
            System.out.println("User registered and logged in successfully!");
            saveUsers();
        }
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void loginUser() {
        System.out.print("Enter username to log in: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Logged in successfully as " + username);
        } else {
            System.out.println("Incorrect username or password.");
        }
    }

    public void postMessage() {
        if (isLoggedIn()) {
            System.out.print("Enter your post: ");
            String postContent = scanner.nextLine();
            loggedInUser.addPost(postContent);
            saveUsers();
        } else {
            System.out.println("Please log in to post a message.");
        }
    }

    public void commentOnOtherUsersPost() {
        if (!isLoggedIn()) {
            System.out.println("Please log in to comment on posts.");
            return;
        }

        System.out.println("Select a user to view their posts:");
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).getUsername().equals(loggedInUser.getUsername())) {
                System.out.println((i + 1) + ". " + users.get(i).getUsername());
            }
        }

        System.out.print("Enter the number of the user: ");
        int userIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (userIndex < 0 || userIndex >= users.size() || users.get(userIndex).equals(loggedInUser)) {
            System.out.println("Invalid user selection.");
            return;
        }

        User selectedUser = users.get(userIndex);
        ArrayList<Post> posts = selectedUser.getPosts();

        if (posts.isEmpty()) {
            System.out.println("This user has no posts.");
            return;
        }

        System.out.println("\n" + selectedUser.getUsername() + "'s Posts:");
        for (int i = 0; i < posts.size(); i++) {
            System.out.println((i + 1) + ". " + posts.get(i).getContent());
            posts.get(i).viewComments();
        }

        System.out.print("Enter the number of the post to comment on: ");
        int postIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (postIndex < 0 || postIndex >= posts.size()) {
            System.out.println("Invalid post selection.");
            return;
        }

        System.out.print("Enter your comment: ");
        String commentContent = scanner.nextLine();
        posts.get(postIndex).addComment(loggedInUser.getUsername(), commentContent);

        System.out.println("Comment added successfully.");
        saveUsers();
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\n=========================");
            System.out.println("       User Menu        ");
            System.out.println("=========================");

            if (isLoggedIn()) {
                System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
                System.out.println("1. Post Message");
                System.out.println("2. View My Posts");
                System.out.println("3. Comment on Other Users' Posts");
                System.out.println("4. Log Out");
            } else {
                System.out.println("Please log in or register to continue:");
                System.out.println("1. Register User");
                System.out.println("2. Login User");
            }

            System.out.println("0. Exit Application");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    if (isLoggedIn()) {
                        postMessage();
                    } else {
                        registerUser();
                    }
                    break;
                case 2:
                    if (isLoggedIn()) {
                        loggedInUser.viewPosts();
                    } else {
                        loginUser();
                    }
                    break;
                case 3:
                    if (isLoggedIn()) {
                        commentOnOtherUsersPost();
                    } else {
                        System.out.println("Please log in to comment on posts.");
                    }
                    break;
                case 4:
                    if (isLoggedIn()) {
                        loggedInUser = null;
                        System.out.println("You have been logged out.");
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 0:
                    System.out.println("Thank you for using the Social Media application! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User user : users) {
                writer.write(user.write());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.read(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous users found.");
        }
    }
}