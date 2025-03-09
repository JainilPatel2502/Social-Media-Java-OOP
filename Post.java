import java.util.ArrayList;

public class Post {
    private String content;
    private ArrayList<String> comments;

    public Post(String content) {
        this.content = content;
        this.comments = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String username, String content) {
        comments.add(username + ": " + content);
    }

    public void viewComments() {
        if (comments.isEmpty()) {
            System.out.println("No comments on this post.");
        } else {
            System.out.println("Comments:");
            for (String comment : comments) {
                System.out.println(comment);
            }
        }
    }


    public String write() {
        StringBuilder serialized = new StringBuilder(content + "|" + comments.size() + "|");
        for (String comment : comments) {
            serialized.append(comment).append(";");
        }
        return serialized.toString();
    }


    public static Post read(String data) {
        String[] parts = data.split("\\|");
        Post post = new Post(parts[0]);

        int commentCount = Integer.parseInt(parts[1]);

        if (commentCount > 0 && parts.length > 2) {
            String[] commentsDataArray = parts[2].split(";");
            for (String commentData : commentsDataArray) {
                if (!commentData.isEmpty()) {
                    post.comments.add(commentData);
                }
            }
        }

        return post;
    }
}