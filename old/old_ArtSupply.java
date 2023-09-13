import java.awt.Color;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Top most parent class of all art supply categories
 */
public class old_ArtSupply implements Comparable<old_ArtSupply> {

    /**
     * Categories of art supplies
     */

    public enum Categories {COLORING, DRAWING, PAPER_CRAFTS, LARGE_TOOLS, OTHER;}
    /**
     * Tags of art supplies, multiple tags ok
     */
    public enum Tags {FAVORITE, HATE, REPLACE}


    /**Built in color names*/
    public static HashMap<Color, String> colorNames = new HashMap<>();

    static {
        //add color names
        colorNames.put(Color.WHITE, "White");
        colorNames.put(Color.BLACK,"Black");
    }

    /**
     * Category of this art supply
     */
    private Categories category;

    /**
     * Subcategories available for the parent category
     */
    private String[] subcategories;

    /**
     * Tags of this art supply
     */
    private final TreeSet<Tags> tags;
    /**
     * Label for this art supply
     */
    private String title;
    /**
     * Longer description of this art supply
     */
    private String description;
    /**
     * Where this art supply is currently located
     */
    private String location;

    /**
     * Image of the art supply
     */
    private String imageFile;
    /**
     * How many of this art supply are currently owned
     */
    private int quantity;

    /**
     * Constructs an ArtSupply with default values
     * Defaults: Empty description, title.jpg image file, quantity 1, no tags
     * @param title Label for this ArtSupply; cannot be null or blank
     * @param location Current location of this ArtSupply; cannot be null or blank
     * @param category Category of this ArtSupply; cannot be null
     */
    public ArtSupply(String title, String location, Categories category) {
        this(title, location, "", title + ".jpg", 1, category, null);
    }

    /**
     * Constructs an ArtSupply item using no default values
     * @param title Label for this ArtSupply; cannot be null or blank
     * @param location Current location of this ArtSupply; cannot be null or blank
     * @param description Longer description of this ArtSupply; default is empty
     * @param imageFile Filename of the image of this ArtSupply; default is title.jpg
     * @param quantity Number owned; must be at least 1 or will be set to default; default is 1
     * @param category Category this ArtSupply belongs to; cannot be null
     * @param tags Tags associated with this ArtSupply, ie Favorites; default is no tags
     */
    public ArtSupply(String title, String location, String description, String imageFile, int quantity, Categories category, Tags[] tags) {
        old_Helper.rejectNullOrBlank(title, "title");
        setTitle(title);

        old_Helper.rejectNullOrBlank(location, "location");
        setLocation(location);

        setDescription(description);
        setImageFileName(imageFile);
        setQuantity(quantity);

        old_Helper.rejectNull(category, "category");
        setCategory(category);

        this.tags = new TreeSet<>();
        addAllTags(tags);

        //set subcategories based on parent category
    }

    /**
     * Gets the label name
     * @return title of the ArtSupply
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the location
     * @return current location of the ArtSupply
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the Category this ArtSupply belongs to
     * @return Category of ArtSupply
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * Gets all the tags this ArtSupply is associated with
     * @return String representation of the set of tags
     */
    public String getTags() {
        return tags.toString().replace("[", "").replace("]", "");
    }

    /**
     * Gets the description of the ArtSupply
     * @return longer description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the filename of the image of the ArtSupply
     * @return filename of the image
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * Gets the number owned of this ArtSupply
     * @return quantity owned
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the title of the ArtSupply
     * @param title label for this ArtSupply; cannot be null or blank
     * @return whether the title was successfully set or not
     */
    public boolean setTitle(String title) {
        if (title == null || title.isBlank()) {
            return false;
        } else {
            this.title = title;
            return true;
        }
    }

    /**
     * Sets the location of the ArtSupply
     * @param location where this is currently located; cannot be null or blank
     * @return whether the location was successfully set or not
     */
    public boolean setLocation(String location) {
        if (location == null || location.isBlank()) {
            return false;
        } else {
            this.location = location;
            return true;
        }
    }

    /**
     * Sets the description of the ArtSupply
     * @param description description of the ArtSupply; if null or blank, changes to empty String
     */
    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            clearDescription();
        } else {
            this.description = description;
        }
    }

    /**
     * Clears description and changes to empty String
     */
    public void clearDescription() {
        description = "";
    }

    /**
     * Sets the image filename of the ArtSupply
     * @param filename name of the file; if null or blank, set to title.jpg with spaces removed;
     *                 if it doesn't end with an image file extension, appends .jpg;
     *                 if contains spaces, removes them
     */
    public void setImageFileName(String filename) {
        if (filename == null || filename.isBlank()) {
            this.imageFile = this.title.replaceAll(" ", "") + ".jpg";
        } else {
            filename = filename.replace(" ", "");
            if (! filename.endsWith(".jpg") && ! filename.endsWith(".png")) {
                filename += ".jpg";
            }
            this.imageFile = filename;
        }
    }

    /**
     * Sets the quantity owned for this ArtSupply
     * @param quantity number of this ArtSupply owned; if less than 1, set to 1
     */
    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 1);
    }

    /**
     * Sets the Category this ArtSupply falls under
     * @param category category of ArtSupply; cannot be null, must be one of set options
     * @return whether the category was successfully changed
     */
    public boolean setCategory(Categories category) {
        if (category != null) {
            this.category = category;
            resetSubcategories();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds one tag to this ArtSupply
     * @param tag Tag to be added; will not be added if null
     * @return whether the tag was successfully added
     */
    public boolean addTag(Tags tag) {
        if (tag != null) {
            tags.add(tag);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds multiple tags to this ArtSupply
     * @param tags array of tags to be added; will not be added if null
     * @return number of tags that were successfully added
     */
    public int addAllTags(Tags[] tags) {
        int tagsAdded = 0;
        if (tags != null) {
            for (Tags tag : tags) {
                if (addTag(tag)) {
                    tagsAdded++;
                }
            }
        }
        return tagsAdded;
    }

    /**
     * Removes one tag from this ArtSupply's tags
     * @param tag the tag to remove
     * @return whether the tag was successfully removed
     */
    public boolean removeTag(Tags tag) {
        if (tag == null) {
            return false;
        }
        return tags.remove(tag);
    }

    /**
     * Removes all the tags for this ArtSupply
     */
    public void removeAllTags() {
        tags.clear();
    }

    /**
     * Returns potential subcategories based on parent category
     * @return array of available subcategories
     */
    public String[] getSubcategories() {
        return subcategories.clone();
    }

    /**
     * Sets subcategories based on parent category
     */
    public void resetSubcategories() {
        switch (getCategory()) {
            case COLORING:
                if (subcategories == null || ! (subcategories[0].equals("Ink"))) {
                    subcategories = new String[]{"Ink", "Watercolor", "Markers", "Soluble Pencils", "Acrylic"};
                }
                break;
            case DRAWING:
                if (subcategories == null || ! (subcategories[0].equals("Pencils"))) {
                    subcategories = new String[]{"Pencils", "Pens", "Brushes", "Paper", "Guides"};
                }
                break;
            case PAPER_CRAFTS:
                if (subcategories == null || ! (subcategories[0].equals("Paper"))) {
                    subcategories = new String[]{"Paper", "Guides", "Cutters", "Other"};
                }
                break;
            case LARGE_TOOLS:
                if (subcategories == null || ! (subcategories[0].equals("Machines"))) {
                    subcategories = new String[]{"Machines", "Printers", "Guides", "Software"};
                }
                break;
            case OTHER:
                if (subcategories == null || ! (subcategories[0].equals("Fabric Crafts"))) {
                    subcategories = new String[]{"Fabric Crafts", "Resin", "Kits", "Other"};
                }
        }
    }

    /**
     * Checks if another object is equal to this ArtSupply
     * @param other Object to be compared
     * @return whether the other object is equal to this ArtSupply
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        ArtSupply otherSupply = (ArtSupply) other;
        return otherSupply.getTitle().equals(this.getTitle()) &&
                otherSupply.getLocation().equals(this.getLocation()) &&
                otherSupply.getDescription().equals(this.getDescription()) &&
                otherSupply.getImageFile().equals(this.getImageFile()) &&
                otherSupply.getQuantity() == this.getQuantity() &&
                otherSupply.getCategory() == this.getCategory() &&
                otherSupply.getTags().equals(this.getTags());
    }

    /**
     * Generates a hash code used for hash tables
     * @return hash code integer for this ArtSupply
     */
    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTags(), getTitle(), getDescription(), getLocation(), getImageFile(), getQuantity());
    }

    /**
     * Compares an object to this ArtSupply and returns the ordering (by class, category)
     * @param other the object to be compared
     * @return negative for less than, 0 for equal, positive for greater than
     */
    @Override
    public int compareTo(ArtSupply other) {
        if (other == this) {
            return 0;
        }
        //nulls last
        if (other == null) {
            return 1;
        }
        //sort by class
        if (getClass() != other.getClass()) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        //sort by category
        return getCategory().compareTo(other.getCategory());
    }

    @Override
    public String toString() {
        return String.format("%s Qty: %d\n%s\n%s\n%s\n%s\n%s",
                getTitle(), getQuantity(), getCategory(), getTags(), getDescription(), getLocation(), getImageFile());
    }
}