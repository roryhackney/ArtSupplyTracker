import java.util.ArrayList;

/** Subject which receives input from GUI and sends processed data to Observers */
public class Subject implements SubjectInterface {
    /** Observers to notify when data changes */
    private final ArrayList<ObserverInterface> observers;

    /** ArtSupply data to be sent to Observers */
    private ArtSupply data;

    /** Basic constructor */
    public Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * Retrieves a list of ArtSupply data to send to Observers
     * @return list of ArtSupplies to be used by Observers
     */
    public ArrayList<ArtSupply> getData() {
        ArrayList<ArtSupply> data = new ArrayList<>();
        data.add(this.data);
        return data;
    }

    /** Receives new GUI input and sets new data, notifying Observers data has changed */
    public void setData() {
        this.data = new ArtSupply("communication test");
        notifyObservers();
    }

    /**
     * Adds a new Observer to the list
     * @param observer the Observer to be added (notified on change)
     */
    public void register(ObserverInterface observer) {
        if (observer != null && !observers.contains(observer)) observers.add(observer);
    }

    /**
     * Removes an Observer from the list
     * @param observer the Observer to be removed (no longer notified)
     */
    public void unregister(ObserverInterface observer) {
        observers.remove(observer);
    }

    /** Notifies all registered Observers that data has changed */
    public void notifyObservers() {
        for (ObserverInterface observer : observers) observer.update();
    }
}
