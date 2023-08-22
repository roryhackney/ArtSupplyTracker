import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaperTest {

    @Test
    public void test_constructorSimple() {
        assertThrows(IllegalArgumentException.class, () -> new Paper(null, "validLocation", true));
        assertThrows(IllegalArgumentException.class, () -> new Paper(" ", "validLocation", true));
        assertThrows(IllegalArgumentException.class, () -> new Paper("validTitle", null, true));
        assertThrows(IllegalArgumentException.class, () -> new Paper("validTitle", "\t", true));

        Paper paper = new Paper("validTitle", "validLocation", true);
        assertEquals("validTitle", paper.getTitle());
        assertEquals("validLocation", paper.getLocation());
        assertEquals("", paper.getDescription());
        assertEquals("validTitle.jpg", paper.getImageFile());
        assertEquals(1, paper.getQuantity());
        assertEquals(ArtSupply.Categories.PAPER_CRAFTS, paper.getCategory());
        assertEquals("", paper.getTags());
        assertEquals(8.5, paper.getWidth());
        assertEquals(11, paper.getHeight());
        assertEquals(Color.WHITE, paper.getColor());
        assertTrue(paper.getIsPrintable());
    }

    @Test
    public void test_constructor() {
        //regular params tested in parent class
        //...width, height, color, isPrintable params, should insert defaults when given bad data
        Paper paper = new Paper("validTitle", "validLocation", "validDescription", "validImage.jpg", 2, null,
                0, 0, null, false);
        assertEquals(8.5, paper.getWidth());
        assertEquals(11, paper.getHeight());
        assertEquals(Color.WHITE, paper.getColor());
        assertFalse(paper.getIsPrintable());

        Paper validPaper = new Paper("validTitle", "validLocation", "validDescription", "validImage.jpg", 2, null,
                12, 16.4, Color.RED, true);
        assertEquals(12, validPaper.getWidth());
        assertEquals(16.4, validPaper.getHeight());
        assertEquals(Color.RED, validPaper.getColor());
        assertTrue(validPaper.getIsPrintable());
    }

    Paper p = new Paper("validTitle", "validLocation", true);
    Paper p2 = new Paper("validTitle", "validLocation", true);
    

    @Test
    public void setWidth() {
        assertEquals(8.5, p.getWidth());
        assertFalse(p.setWidth(0));
        assertEquals(8.5, p.getWidth());
        assertTrue(p.setWidth(4.25));
        assertEquals(4.25, p.getWidth());
    }

    @Test
    public void setHeight() {
        assertEquals(11, p.getHeight());
        assertFalse(p.setHeight(0));
        assertEquals(11, p.getHeight());
        assertTrue(p.setHeight(6.75));
        assertEquals(6.75, p.getHeight());
    }

    @Test
    public void setColor() {
        assertEquals(Color.WHITE, p.getColor());
        assertFalse(p.setColor(null));
        assertEquals(Color.WHITE, p.getColor());
        assertTrue(p.setColor(Color.GREEN));
        assertEquals(Color.GREEN, p.getColor());
    }

    @Test
    public void setIsPrintable() {
        assertTrue(p.getIsPrintable());
        p.setIsPrintable(false);
        assertFalse(p.getIsPrintable());
        p.setIsPrintable(true);
        assertTrue(p.getIsPrintable());
    }

    @Test
    public void testEquals() {
        assertEquals(p, p);
        assertEquals(p, p2);
        p2.setIsPrintable(false);
        assertNotEquals(p, p2);
        assertNotEquals(p, null);
        assertNotEquals(p, new ArtSupply("validTitle", "validLocation", ArtSupply.Categories.PAPER_CRAFTS));
    }

    @Test
    public void testHashCode() {
        assertEquals(p.hashCode(), p.hashCode());
        assertEquals(p.hashCode(), p2.hashCode());
        p2.setIsPrintable(false);
        assertNotEquals(p.hashCode(), p2.hashCode());
    }

    @Test
    public void testToString() {
        String check = "validTitle Qty: 1\nPAPER_CRAFTS\n\n\nvalidLocation\nvalidTitle.jpg\nWidth: 8.50 Height: 11.00\nColor: White\nprintable";
        assertEquals(check, p.toString());
    }
}