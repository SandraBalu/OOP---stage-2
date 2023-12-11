package app.pages;

public class Page {
    protected String type;

    private Page(String type) {
        this.type = type;
    }

    public static Page getInstance(String type) {
        return new Page(type);
    }

    public void displayPage() {
        System.out.println(type + " Page");
        displayContent();
    }

    protected void displayContent() {
    }
}
