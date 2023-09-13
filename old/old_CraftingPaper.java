import java.awt.Color;

public class CraftingPaper extends Paper {

    private enum PaperType {CARDSTOCK, HANDMADE_PATTERNED, PRINTER_PAPER, SPECIAL_PRINT}
    private PaperType paperType;
    public CraftingPaper(String title, String location, boolean isPrintable, PaperType paperType) {
        super(title, location, isPrintable);
        setCategory(Categories.PAPER_CRAFTS);
        Helper.rejectNull(paperType, "paperType");
        this.paperType = paperType;
    }

    public CraftingPaper(String title, String location, String description, String imageFile, int quantity, Tags[] tags,
                         double width, double height, Color color, boolean isPrintable, PaperType paperType) {
        super(title, location, description, imageFile, quantity, tags, width, height, color, isPrintable);
        setCategory(Categories.PAPER_CRAFTS);
        Helper.rejectNull(paperType, "paperType");
        this.paperType = paperType;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public boolean setPaperType(PaperType paperType) {
        if (paperType == null) {
            return false;
        } else {
            this.paperType = paperType;
            return true;
        }
    }
}
