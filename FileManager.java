import java.io.File;

/**
 * Static methods for managing files
 */
public class FileManager {
    /**
     * Creates a folder (directory) in the current folder if the folder does not exist
     *
     * @param folderName Name of the folder; cannot be null or blank
     * @return true if successfully created, false if already exists or failure
     */
    public static boolean createDirectoryIfNotExists(String folderName) {
        if (folderName == null || folderName.isBlank()) return false;
        File folder = new File(folderName);
        if (!folder.isDirectory()) {
            return folder.mkdir();
        }
        return false;
    }
}