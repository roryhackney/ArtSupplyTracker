import java.util.ArrayList;
import java.util.HashSet;

public class User {
    private String alias;
    private Boolean random;
    private ArrayList<String> randomAliases;
    private final static HashSet<String> suggestions;

    static {
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

    public User() {
        setAlias("");
        setRandom(true);
        resetRandomAliases();
    }
    public User(String alias, Boolean random, HashSet<String> randomAliases) {
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

    public String getAlias() {
        if (getRandom()) {
            return getRandomAlias();
        }
        return alias;
    }

    public void setAlias(String alias) {
        if (alias == null || alias.isBlank()) {
            this.alias = "";
            setRandom(true);
        } else {
            this.alias = alias;
        }
    }

    public Boolean getRandom() {
        return random;
    }

    public void setRandom(Boolean random) {
        this.random = random;
    }

    public String[] getAllRandomAliases() {
        return randomAliases.toArray(new String[0]);
    }

    public String getRandomAlias() {
        int index = (int) (Math.random() * (randomAliases.size() - 1));
        return getRandomAlias(index);
    }

    public String getRandomAlias(int index) {
        return randomAliases.get(index);
    }

    public int numRandomAliases() {
        return randomAliases.size();
    }
    public Boolean addRandomAlias(String alias) {
        if (alias != null && ! alias.isBlank() && ! randomAliases.contains(alias)) {
            randomAliases.add(alias);
            return true;
        }
        return false;
    }

    public Boolean removeRandomAlias(String alias) {
        return randomAliases.remove(alias);
    }

    public String removeRandomAlias(int index) {
        return randomAliases.remove(index);
    }

    public void resetRandomAliases() {
        randomAliases = new ArrayList<>(suggestions);
    }
}
