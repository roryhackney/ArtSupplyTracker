public class ArtSupply {
    private String title;

    public ArtSupply(String title) {
        if (!setTitle(title)) throw new IllegalArgumentException("title cannot be null or blank");
    }

    public boolean setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
            return true;
        }
        return false;
    }

    public String getTitle() {
        return title;
    }

}
