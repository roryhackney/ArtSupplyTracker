import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArtSupplyTest {
    private final ArtSupply marker = new ArtSupply("Red Marker", "Closet", "red marker", "red_mark.jpg",
            2, ArtSupply.Categories.DRAWING, new ArtSupply.Tags[] {ArtSupply.Tags.FAVORITE});

    @Test
    public void test_constructorTitleLocationCategoryGets() {
        //Simple constructor testing

        //title
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply(null, "valid", ArtSupply.Categories.COLORING);});
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply("", "valid", ArtSupply.Categories.COLORING);});
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply("  \n \t  ", "valid", ArtSupply.Categories.COLORING);});

        //location
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply("valid", null, ArtSupply.Categories.COLORING);});
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply("valid", "", ArtSupply.Categories.COLORING);});
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply("valid", " \n\t ", ArtSupply.Categories.COLORING);});

        //category
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtSupply("valid", "valid", null);
        });

        //valid data defaults and gets
        ArtSupply test = new ArtSupply("validTitle", "validLocation", ArtSupply.Categories.COLORING);
        assertEquals("validTitle", test.getTitle());
        assertEquals("validLocation", test.getLocation());
        assertEquals("", test.getDescription());
        assertEquals("validTitle.jpg", test.getImageFile());
        assertEquals(1, test.getQuantity());
        assertEquals(ArtSupply.Categories.COLORING, test.getCategory());
        assertEquals("", test.getTags());
    }

    @Test
    public void test_constructorFullGets() {
        //title, location, and category tested above
        //title, location, description, imageFile, quantity, category, tags

        //invalid data changes to defaults testing
        ArtSupply test = new ArtSupply("validTitle", "validLocation", null, null, -45, ArtSupply.Categories.COLORING, null);
        assertEquals("", test.getDescription());
        assertEquals("validTitle.jpg", test.getImageFile());
        assertEquals(1, test.getQuantity());
        assertEquals("", test.getTags());

        //real data is saved correctly testing
        ArtSupply testRealData = new ArtSupply("validTitle", "validLocation", "validDescription", "validImage.png",
                5, ArtSupply.Categories.COLORING, new ArtSupply.Tags[] {ArtSupply.Tags.FAVORITE});
        assertEquals("validDescription", testRealData.getDescription());
        assertEquals("validImage.png", testRealData.getImageFile());
        assertEquals(5, testRealData.getQuantity());
        assertEquals("FAVORITE", testRealData.getTags());
    }

    @Test
    public void test_setTitle() {
        assertEquals("Red Marker", marker.getTitle());
        assertFalse(marker.setTitle(" \n\t  "));
        assertEquals("Red Marker", marker.getTitle());
        assertFalse(marker.setTitle(null));
        assertEquals("Red Marker", marker.getTitle());
        assertTrue(marker.setTitle("Red Brush"));
        assertEquals("Red Brush", marker.getTitle());
    }

    @Test
    public void test_setLocation() {
        assertEquals("Closet", marker.getLocation());
        assertFalse(marker.setLocation(" \n\t  "));
        assertEquals("Closet", marker.getLocation());
        assertFalse(marker.setLocation(null));
        assertEquals("Closet", marker.getLocation());
        assertTrue(marker.setLocation("Desk"));
        assertEquals("Desk", marker.getLocation());
    }

    @Test
    public void test_setDescription() {
        assertEquals("red marker", marker.getDescription());
        marker.setDescription(null);
        assertEquals("", marker.getDescription());
        marker.setDescription("red brush");
        assertEquals("red brush", marker.getDescription());
        marker.setDescription("\n\t   ");
        assertEquals("", marker.getDescription());
    }

    @Test
    public void test_clearDescription() {
        assertEquals("red marker", marker.getDescription());
        marker.clearDescription();
        assertEquals("", marker.getDescription());
    }

    @Test
    public void test_setImageFileName() {
        assertEquals("red_mark.jpg", marker.getImageFile());
        marker.setImageFileName(null);
        assertEquals("RedMarker.jpg", marker.getImageFile());
        marker.setImageFileName("red br us h.png");
        assertEquals("redbrush.png", marker.getImageFile());
        marker.setImageFileName("    ");
        assertEquals("RedMarker.jpg", marker.getImageFile());
        marker.setImageFileName("redBrush");
        assertEquals("redBrush.jpg", marker.getImageFile());
    }

    @Test
    public void test_setQuantity() {
        assertEquals(2, marker.getQuantity());
        marker.setQuantity(0);
        assertEquals(1, marker.getQuantity());
        marker.setQuantity(50);
        assertEquals(50, marker.getQuantity());
    }

    @Test
    public void test_setCategory() {
        assertEquals(ArtSupply.Categories.DRAWING, marker.getCategory());
        assertFalse(marker.setCategory(null));
        assertEquals(ArtSupply.Categories.DRAWING, marker.getCategory());
        assertTrue(marker.setCategory(ArtSupply.Categories.COLORING));
        assertEquals(ArtSupply.Categories.COLORING, marker.getCategory());
    }

    @Test
    public void test_addTag() {
        assertEquals("FAVORITE", marker.getTags());
        assertFalse(marker.addTag(null));
        assertEquals("FAVORITE", marker.getTags());
        assertTrue(marker.addTag(ArtSupply.Tags.REPLACE));
        assertEquals("FAVORITE, REPLACE", marker.getTags());
    }

    @Test
    public void test_removeAllTags() {
        marker.addTag(ArtSupply.Tags.REPLACE);
        assertEquals("FAVORITE, REPLACE", marker.getTags());
        marker.removeAllTags();
        assertEquals("", marker.getTags());
    }

    @Test
    public void test_addAllTags() {
        marker.removeAllTags();
        assertEquals("", marker.getTags());
        assertEquals(0, marker.addAllTags(null));
        assertEquals("", marker.getTags());
        assertEquals(0, marker.addAllTags(new ArtSupply.Tags[]{null, null, null}));
        assertEquals("", marker.getTags());
        assertEquals(3, marker.addAllTags(new ArtSupply.Tags[]
                {ArtSupply.Tags.FAVORITE, ArtSupply.Tags.HATE, ArtSupply.Tags.REPLACE}));
        assertEquals("FAVORITE, HATE, REPLACE", marker.getTags());
    }

    @Test
    public void test_removeTag() {
        assertEquals("FAVORITE", marker.getTags());
        assertFalse(marker.removeTag(null));
        assertEquals("FAVORITE", marker.getTags());
        assertFalse(marker.removeTag(ArtSupply.Tags.HATE));
        assertEquals("FAVORITE", marker.getTags());
        assertTrue(marker.removeTag(ArtSupply.Tags.FAVORITE));
        assertEquals("", marker.getTags());
    }

    @Test
    public void test_equals() {
        ArtSupply markerCopy = new ArtSupply("Red Marker", "Closet", "red marker", "red_mark.jpg",
                2, ArtSupply.Categories.DRAWING, new ArtSupply.Tags[] {ArtSupply.Tags.FAVORITE});
        assertEquals(marker, marker);
        assertEquals(marker, markerCopy);
        markerCopy.setQuantity(1);
        assertNotEquals(marker, markerCopy);
        assertNotEquals(marker, null);
        assertNotEquals(marker, new Paper("White Paper", "Closet", false));
    }

    @Test
    public void test_hashCode() {
        ArtSupply markerCopy = new ArtSupply("Red Marker", "Closet", "red marker", "red_mark.jpg",
                2, ArtSupply.Categories.DRAWING, new ArtSupply.Tags[] {ArtSupply.Tags.FAVORITE});
        assertEquals(marker.hashCode(), markerCopy.hashCode());
        markerCopy.setQuantity(6);
        assertNotEquals(marker.hashCode(), markerCopy.hashCode());
        Paper paper = new Paper("White Paper", "Closet", true);
        assertNotEquals(marker.hashCode(), paper.hashCode());
    }

    @Test
    public void test_compareTo() {
        ArtSupply markerCopy = new ArtSupply("Red Marker", "Closet", "red marker", "red_mark.jpg",
                2, ArtSupply.Categories.DRAWING, new ArtSupply.Tags[] {ArtSupply.Tags.FAVORITE});
        assertEquals(0, marker.compareTo(marker));
        assertEquals(0, marker.compareTo(markerCopy));
        markerCopy.setCategory(ArtSupply.Categories.LARGE_TOOLS);
        assertTrue(marker.compareTo(markerCopy) < 0);
        markerCopy.setCategory(ArtSupply.Categories.COLORING);
        assertTrue(marker.compareTo(markerCopy) > 0);
        markerCopy.setCategory(ArtSupply.Categories.DRAWING);

        markerCopy.addTag(ArtSupply.Tags.REPLACE);
        assertTrue(marker.compareTo(markerCopy) < 0);
        markerCopy.removeAllTags();
        assertTrue(marker.compareTo(markerCopy) > 0);
        markerCopy.addTag(ArtSupply.Tags.FAVORITE);

        markerCopy.setTitle("Silver Marker");
        assertTrue(marker.compareTo(markerCopy) < 0);
        markerCopy.setTitle("Brown Marker");
        assertTrue(marker.compareTo(markerCopy) > 0);

        assertTrue(marker.compareTo(null) < 0);
    }

    @Test
    public void test_toString() {
        String check = "Red Marker Qty: 2\nDRAWING\nFAVORITE\nred marker\nCloset\nred_mark.jpg";
        assertEquals(check, marker.toString());
    }

    @Test
    public void test_subcategories() {
        assertArrayEquals((Object[]) new String[] {"Pencils", "Pens", "Brushes", "Paper", "Guides"}, (Object[]) marker.getSubcategories());
        marker.setCategory(ArtSupply.Categories.PAPER_CRAFTS);
        assertArrayEquals((Object[]) new String[]{"Paper", "Guides", "Cutters", "Other"}, (Object[]) marker.getSubcategories());
        marker.setCategory(ArtSupply.Categories.OTHER);
        assertArrayEquals((Object[]) new String[]{"Fabric Crafts", "Resin", "Kits", "Other"}, (Object[]) marker.getSubcategories());
    }
}