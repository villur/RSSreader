package pakk;

/**
 * Created by villur on 23/01/17.
 */
public class News {

    private String Title;
    private String Description;
    private String Author;
    private String Link;

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


    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getFormattedNews() {
        String autorText = null;
        String descriptionText = null;
        String linkText = null;


        if (getAuthor() != null) {
            autorText = "Autor: " + getAuthor() + "\n";
        }

        if (getDescription() != null) {
            descriptionText = getDescription() + "\n" + "\n";
        }
        if (getLink() != null) {
            linkText = "Link: " + getLink();
        }

        return descriptionText + autorText + linkText;

    }
}

