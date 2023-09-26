import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Runnable manager of art supplies, use to add, delete, view, etc
 */
public class Main {
    /**
     * Private constructor to prevent instantiation
     */
    private Main() {
    }

    /**
     * Main program, runs the art supply manager
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //Setup folder structure and files. If files do not exist, create. Else, load.

        //folders
        FileManager.createDirectoryIfNotExists("settings");
        FileManager.createDirectoryIfNotExists("settings/images");

        //files

        //user file
        File userFile = new File("settings/user.txt");
        //if the file exists, create the user with its data
        if (userFile.isFile()) {
            Scanner userScanner;
            try {
                userScanner = new Scanner(userFile);
            } catch (FileNotFoundException error) {
                return;
            }
            String alias = userScanner.nextLine().strip();
            boolean random = userScanner.nextBoolean();
            HashSet<String> suggestions = new HashSet<>();
            while (userScanner.hasNextLine()) {
                suggestions.add(userScanner.nextLine().strip());
            }
            User user = new User(alias, random, suggestions);
            userScanner.close();
        } else {
            //new gui -> send user data -> process user data -> replace file
            SubjectInterface subject = new Subject();
            new UserNameGui(subject, null);
            new SearchResults(subject);
        }
//        if (! userFile.isFile()) {
//
//        }

        //ArtSupply images folder

        //if the settings folder does not exist
        // do all setup
        //else (does exist)
        //if user.txt does not exist, do user setup
        //else load user data
        //if categories.txt does not exist, do category setup
        //else load category data
        //if types.txt does not exist, do types setup
        //else load types data
        //if artsupplies.txt does not exist, do artsupplies setup
        //else load supplies data
        //if images folder does not exist, do imgs setup
        //else load images data
    }


}
