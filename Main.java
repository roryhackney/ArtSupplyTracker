import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/** Runnable manager of art supplies, use to add, delete, view, etc*/
public class Main {
    private Main() {}

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
            } catch (FileNotFoundException ignored) {}
            String alias = userScanner.nextLine().strip();
            Boolean random = userScanner.nextBoolean();
            HashSet<String> suggestions = new HashSet<>();
            while (userScanner.hasNext()) {
                suggestions.add(userScanner.next());
            }
            User user = new User(alias, random, suggestions);
        } else {
            //new gui -> send user data -> process user data -> replace file
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
