import java.util.ArrayList;

public interface SubjectInterface {
    /**
     * Retrieves a list of data to send to Observers
     * @return list of ArtSupplies to be used by Observers
     */
    ArrayList<ArtSupply> getData();

    /**
     * Adds a new Observer to the list
     * @param observer the Observer to be added (notified on change)
     */
    void register(ObserverInterface observer);

    /**
     * Removes an Observer from the list
     * @param observer the Observer to be removed (no longer notified)
     */
    void unregister(ObserverInterface observer);

    /** Uses new input to set new data, notifying Observers data has changed */
    void setData();

    /** Notifies all registered Observers that data has changed */
    void notifyObservers();
}
