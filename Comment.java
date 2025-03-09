public class Comment {
    private String commenterUsername;
    private String content;

    public Comment(String commenterUsername, String content) {
        this.commenterUsername = commenterUsername;
        this.content = content;
    }

    public String getUsername() {
        return commenterUsername;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return commenterUsername + ": " + content;
    }
}