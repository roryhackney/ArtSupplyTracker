import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * User class, mainly used for displaying chosen alias or different random aliases, set by system or user
 */
public class User {
    /** Main alias to be used, unless random pool is preferred and this is blank */
    private String alias;

    /** True if random aliases are preferred or alias is blank, false if main alias is preferred */
    private boolean random;

    /** List of aliases that could be selected for random display */
    private ArrayList<String> randomAliases;

    /** initial system suggested random aliases, used if user does not set any */
    private final static HashSet<String> suggestions;

    /** possible welcome messages to greet the user */
    private final static String[] greetings;

    /** Maximum number of characters an alias can have */
    public static final int LENGTH_LIMIT;

    /** Maximum number of random aliases a user can save */
    public static final int ALIASES_LIMIT;

    //initial limits and suggestions set
    static {
        LENGTH_LIMIT = 50;
        ALIASES_LIMIT = 20;

        suggestions = new HashSet<>(20);
        suggestions.add("Dark One");
        suggestions.add("O Greatest of Heroes");
        suggestions.add("Your Majesty");
        suggestions.add("Wrinkled Hot Dog");
        suggestions.add("Collector of Treasures");
        suggestions.add("Ancient and Honored Evil");
        suggestions.add("User #4");
        suggestions.add("Bright Knight");
        suggestions.add("Alexia");
        suggestions.add("Timmy");
        suggestions.add("Skilled Artisan");
        suggestions.add("Michelangelo");
        suggestions.add("Fluffy");
        suggestions.add("Noodle");
        suggestions.add("Overripe Tomato");
        suggestions.add("Wisest of Sages");
        suggestions.add("Fartist");
        suggestions.add("Muddy Paint Water");
        suggestions.add("Steamed Pork Bun");
        suggestions.add("X");

        greetings = new String[] {"Welcome", "Hello", "Greetings", "Hi"};
    }

    /** Basic constructor, sets main alias to blank, random to true, and randomAliases to system suggestions */
    public User() {
        this("", true, null);
    }

    /**
     * More complex constructor
     * @param alias Main alias to be displayed if random is false, or as part of the random pool if not null/blank;
     *              null or blank sets alias to blank and random to true
     * @param random Whether to display all random aliases, including alias (true) or main alias only (false)
     * @param randomAliases Random aliases to be displayed if random is true or alias is null/blank
     */
    public User(String alias, boolean random, String[] randomAliases) {
        setAlias(alias);
        setRandom(random);

        //sets the random options to given randomAliases if there's at least one, otherwise set to suggestions
        if (! replaceRandomAliases(randomAliases)) resetRandomAliases();
    }

    /**
     * Returns the main alias
     * @return the main alias of the user, which may be an empty String if unset
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets a new main alias. If null or blank, sets to empty String and sets random to true.
     * @param alias Main alias of the User to display; if null or blank, random aliases will be displayed instead
     */
    public void setAlias(String alias) {
        if (alias == null || alias.isBlank()) {
            this.alias = "";
            setRandom(true);
        } else {
            this.alias = alias;
        }
    }

    /** Returns whether random is true or false */
    public boolean getRandom() {
        return random;
    }

    /**
     * Sets random
     * @param random whether to use random aliases; if alias is null/blank, must be true
     */
    public void setRandom(boolean random) {
        if (! alias.isBlank()) this.random = random;
    }

    /** Returns the number of random aliases */
    public int numRandomAliases() {
        return randomAliases.size();
    }

    /** Returns an array of all random aliases */
    public String[] getAllRandomAliases() {
        return randomAliases.toArray(new String[0]);
    }

    /**
     * Resets the list of random aliases to the initial system suggestions
     */
    public void resetRandomAliases() {
        randomAliases = new ArrayList<>(suggestions);
    }

    /**
     * Adds a new alias to the pool of random aliases
     * @param alias the alias to add; will only be added if not null, not blank, not a duplicate,
     *              not longer than LENGTH_LIMIT, and adding will not increase the set size beyond ALIASES_LIMIT
     * @return whether the alias was successfully added
     */
    public boolean addRandomAlias(String alias) {
        boolean success = false;
        if (alias != null &&
                ! alias.isBlank() &&
                ! randomAliases.contains(alias) &&
                alias.length() <= LENGTH_LIMIT &&
                numRandomAliases() < ALIASES_LIMIT) {
            success = randomAliases.add(alias);
        }
        return success;
    }

    /**
     * Replaces the pool of random aliases with a new one; there must be at least one valid alias
     * @param randomAliases array of random aliases to replace with; null/empty array or values will not be used
     * @return whether the randomAliases were successfully replaced
     */
    public boolean replaceRandomAliases(String[] randomAliases) {
        boolean success = false;
        if (randomAliases != null && randomAliases.length != 0) {
            ArrayList<String> orig = this.randomAliases;
            this.randomAliases = new ArrayList<>();
            for (String alias : randomAliases) {
                addRandomAlias(alias);
            }
            if (! this.randomAliases.isEmpty()) {
                success = true;
            } else {
                //no valid aliases, put the old ones back
                this.randomAliases = orig;
            }
        }
        return success;
    }

    /**
     * Gets an alias from the random pool at the given index (main alias not included)
     * @param index position at which to retrieve the alias
     * @return the alias at the given index
     */
    public String getRandomAliasAt(int index) {
        return randomAliases.get(index);
    }

    /**
     * Returns a random alias for display. If random is false, returns alias.
     * Else, return a random alias which may include alias if not blank.
     * @return a random alias or the main alias
     */
    public String getAnyRandomAlias() {
        //either random should be true or alias should not be blank
        assert (random || ! alias.isBlank());
        String alias = getAlias();
        //only change from main alias if random is set to true
        if (random) {
            //get random number between 0 and number of aliases (inclusive)
            //max bound (not included)
            int maxIndex = numRandomAliases();
            //if main alias is not blank, add 1 to max
            if (! getAlias().isBlank()) maxIndex++;
            //get random number 0 to max
            int index = (int) (Math.random() * maxIndex);

            //if index is less than array max + 1, return random alias
            if (index < numRandomAliases()) {
                //get the random alias at the index
                alias = getRandomAliasAt(index);
            }
        }
        return alias;
    }

    /**
     * Returns a random welcome message for the user
     */
    public String randomWelcomeMessage(boolean displayName) {
        String greeting = greetings[(int)(Math.random() * (greetings.length))];
        if (! displayName) {
            greeting += "!";
        } else {
            greeting += getAnyRandomAlias() + "!";
        }
        return greeting;
    }
}
