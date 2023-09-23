import java.util.ArrayList;
import java.util.HashSet;

/**
 * User class, mainly used for displaying chosen alias or different random aliases, set by system or user
 */
public class User {
    /** Main alias to be used unless random aliases are preferred */
    private String alias;

    /** True if random aliases are preferred, false if main alias is preferred */
    private boolean random;

    /** List of aliases that could be selected for random display */
    private ArrayList<String> randomAliases;

    /** initial system suggested random aliases, used if user does not set any */
    private final static HashSet<String> suggestions;

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
    }

    /** Basic constructor, sets main alias to blank, random to true, and randomAliases to system suggestions */
    public User() {
        this("", true, null);
    }

    /**
     * More complex constructor
     * @param alias Main alias to be displayed if random is false and this is not null/blank;
     *              null or blank sets alias to blank and random to true
     * @param random Whether to display randomAliases (true) or main alias (false)
     * @param randomAliases Random aliases to be displayed if random is true or alias is null/blank
     */
    public User(String alias, boolean random, HashSet<String> randomAliases) {
        this.randomAliases = new ArrayList<>();
        //add all the given aliases to the random options
        if (randomAliases != null) {
            for (String oneAlias : randomAliases) {
                addRandomAlias(oneAlias);
            }
        }
        //if there are no aliases, add suggested aliases to random options
        if (getAllRandomAliases().length == 0) this.randomAliases.addAll(suggestions);

        setRandom(random);
        //set random to true if invalid alias
        setAlias(alias);
        //if valid, add alias to random options
        addRandomAlias(alias);
    }

    /** Returns a random alias, or the main alias if not blank and random is false */
    public String getAlias() {
        if (getRandom() || alias.isBlank()) {
            return getRandomAlias();
        }
        return alias;
    }

    /**
     * Sets a new main alias. If null or blank, sets to blank and sets random to true.
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

    /** Sets random */
    public void setRandom(boolean random) {
        this.random = random;
    }

    /** Returns an array of all random aliases */
    public String[] getAllRandomAliases() {
        return randomAliases.toArray(new String[0]);
    }

    /** Returns a random alias from the list */
    public String getRandomAlias() {
        int index = (int) (Math.random() * randomAliases.size());
        return getRandomAlias(index);
    }

    /**
     * Returns the alias from randomAliases at the index given, or a random alias if invalid index is given
     * @param index position in the array to retrieve; should be 0 through numRandomAliases() - 1
     * @return the alias at the given index, or a random alias if an invalid index is given
     */
    public String getRandomAlias(int index) {
        if (index < 0 || index >= numRandomAliases()) return getRandomAlias();
        return randomAliases.get(index);
    }

    /** Returns the number of random aliases */
    public int numRandomAliases() {
        return randomAliases.size();
    }

    /**
     * Adds a new alias to the pool of random aliases
     * @param alias the alias to add; will only be added if not null, not blank, and not a duplicate
     * @return whether the alias was successfully added
     */
    public boolean addRandomAlias(String alias) {
        if (alias != null && ! alias.isBlank() && ! randomAliases.contains(alias) &&  alias.length() <= LENGTH_LIMIT) {
            randomAliases.add(alias);
            return true;
        }
        return false;
    }

    /**
     * Removes an alias from the pool of random aliases
     * @param alias the alias to remove; will only be removed if it exists already
     * @return whether the alias was removed
     */
    public boolean removeRandomAlias(String alias) {
        return randomAliases.remove(alias);
    }

    /**
     * Removes an alias from the pool of random aliases at the index given
     * @param index the position at which to remove a random alias; should be 0 through numRandomAliases() - 1
     * @return the alias that was removed, or null if not removed
     */
    public String removeRandomAlias(int index) {
        return randomAliases.remove(index);
    }

    /**
     * Resets the list of random aliases to the initial system suggestions
     */
    public void resetRandomAliases() {
        randomAliases = new ArrayList<>(suggestions);
    }
}
