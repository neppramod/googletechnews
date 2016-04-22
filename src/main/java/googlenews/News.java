package googlenews;

/**
 * Each News element parsed from Google News
 */
public class News {
    private int id;         // Id for selecting the news item to open in default browser
    private String link;    // Link that needs to be opened when id number is pressed
    private String text;    // Text to show to the user screen

    public News(int id, String text, String link) {
        this.id = id;
        this.text = text;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
