# Social Media Console Application

## Overview
This is a simple console-based social media application built in Java. It allows users to register, log in, create posts, comment on other users' posts, and store user data in a file for persistence.

## Features
- User Registration and Login
- Posting Messages
- Viewing User Posts
- Commenting on Other Users' Posts
- Persistent Storage of User Data

## Project Structure
The application consists of the following main classes:

1. **Comment.java**
   - Represents a comment on a post.
   - Stores the commenter's username and content.

2. **Post.java**
   - Represents a post made by a user.
   - Stores the content and associated comments.
   - Methods for adding and viewing comments.

3. **User.java**
   - Represents a registered user.
   - Stores username, password, and user's posts.
   - Methods for creating posts and viewing them.

4. **SocialMedia.java**
   - Manages user registration, login, and main application menu.
   - Handles interactions between users and posts.
   - Implements file storage for saving user data.

5. **SocialMediaApp.java**
   - The entry point of the application.
   - Creates an instance of `SocialMedia` and launches the main menu.

## Installation & Running the Application
### Prerequisites
- Java Development Kit (JDK) installed
- Any IDE (IntelliJ, Eclipse, VS Code) or command line

### Running the Application
1. Clone the repository:
   ```sh
   git clone https://github.com/JainilPatel2502/Social-Media-Java-OOP
   ```
2. Navigate to the project directory:
   ```sh
   cd Social_Media
   ```
3. Compile the Java files:
   ```sh
   javac *.java
   ```
4. Run the application:
   ```sh
   java SocialMediaApp
   ```

## Usage Guide
1. Register a new user by providing a username and password.
2. Log in using your registered credentials.
3. Post messages that will be stored in your account.
4. View posts made by other users and leave comments on them.
5. Log out and re-login anytime as your data is stored persistently.

## File Storage
User data is saved in `users.txt`, allowing persistence across sessions.
- Data includes usernames, passwords, posts, and comments.
- The application reads from and writes to this file automatically.

## Future Enhancements
- Implement password hashing for security.
- Add a GUI-based version using JavaFX.
- Improve file storage format (e.g., JSON or database integration).


