import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Post> posts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(String content) {
        posts.add(new Post(content));
        System.out.println("Post added successfully!");
    }

    public void viewPosts() {
        if (posts.isEmpty()) {
            System.out.println("No posts available.");
        } else {
            for (int i = 0; i < posts.size(); i++) {
                System.out.println((i + 1) + ". " + posts.get(i).getContent());
                posts.get(i).viewComments();
            }
        }
    }

    public String write() {
        StringBuilder serialized = new StringBuilder(username + "|" + password + "|" + posts.size() + "|");
        for (Post post : posts) {
            serialized.append(post.write()).append(";");
        }
        return serialized.toString();
    }

    public static User read(String data) {
        String[] parts = data.split("\\|", 4);
        User user = new User(parts[0], parts[1]);

        int postCount = Integer.parseInt(parts[2]);
        if (postCount > 0 && parts.length == 4) {
            String[] postDataArray = parts[3].split(";");
            for (String postData : postDataArray) {
                if (!postData.isEmpty()) {
                    user.posts.add(Post.read(postData));
                }
            }
        }
        return user;
    }
}