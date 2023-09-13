import java.awt.Color;
import java.util.Objects;

// * Represents paper for drawing or crafting or printing*/
public class Paper extends ArtSupply {
    /**
     * Whether the paper is meant for printing on
     */
    private boolean isPrintable;
    /**
     * Width of the paper in inches
     */
    private double widthInches;
    /**
     * Height of the paper in inches
     */
    private double heightInches;
    /**
     * Primary color of the paper
     */
    private Color color;
    //
    public Paper(String title, String location, boolean isPrintable) {
        this(title, location, "", title + ".jpg", 1, null, 8.5, 11, Color.WHITE, isPrintable);
    }
    public Paper(String title, String location, String description, String imageFile, int quantity, Tags[] tags,
                 double width, double height, Color color, boolean isPrintable) {
        super(title, location, description, imageFile, quantity, ArtSupply.Categories.PAPER_CRAFTS, tags);
        this.widthInches = width > 0 ? width : 8.5;
        this.heightInches = height > 0 ? height : 11;
        this.color = color != null ? color : Color.WHITE;
        this.isPrintable = isPrintable;
    }



    /**
     * Gets the width of the paper
     * @return width of the paper in inches
     */
    public double getWidth() {
        return widthInches;
    }

    /**
     * Gets the height of the paper
     * @return height of the paper in inches
     */
    public double getHeight() {
        return heightInches;
    }

    /**
     * Gets the main color of the paper
     * @return color of the paper
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets whether the paper can be printed on
     * @return whether the paper is printable
     */
    public boolean getIsPrintable() {
        return isPrintable;
    }

    /**
     * Sets the width of the paper in inches
     * @param width width of the paper in inches; will not be set if 0 or less
     * @return whether the width was successfully set
     */
    public boolean setWidth(double width) {
        if (width > 0) {
            widthInches = width;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the height of the paper in inches
     * @param height height of the paper in inches; will not be set if 0 or less
     * @return whether the height was successfully set
     */
    public boolean setHeight(double height) {
        if (height > 0) {
            heightInches = height;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the color of the paper
     * @param color Color of the paper; will not be set if null
     * @return whether the color was successfully set
     */
    public boolean setColor(Color color) {
        if (color != null) {
            this.color = color;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Changes whether the paper can be printed on
     * @param isPrintable whether the paper can be printed on
     */
    public void setIsPrintable(boolean isPrintable) {
        this.isPrintable = isPrintable;
    }

    /**
     * Checks if this paper is the same as another object
     * @param other Object to be compared
     * @return whether this paper is equal to the other object
     */
    @Override
    public boolean equals(Object other) {
        if(!super.equals(other)) {
            return false;
        } else {
            //test paper specific fields
            Paper otherPaper = (Paper) other;
            return this.isPrintable == otherPaper.isPrintable &&
                    this.color == otherPaper.getColor() &&
                    this.widthInches == otherPaper.getWidth() &&
                    this.heightInches == otherPaper.getHeight();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTags(), getTitle(), getDescription(), getLocation(), getImageFile(),
                getQuantity(), getWidth(), getHeight(), getColor(), getIsPrintable());
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += String.format("\nWidth: %.2f Height: %.2f", getWidth(), getHeight());
        Color color = getColor();
        if (colorNames.containsKey(color)) {
            result += "\nColor: " + colorNames.get(color);
        }
        if (getIsPrintable()) {result += "\nprintable";}
        return result;
    }

}