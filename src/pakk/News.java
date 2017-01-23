package pakk;

/**
 * Created by villur on 23/01/17.
 */
public class News {

    private String Title;
    private String Description;
    private String Author;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
    @Override
    public String toString() {
        return "\n" + this.getTitle() + "\n" +
               this.getAuthor() ;
    }
}
