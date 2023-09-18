import java.util.ArrayList;

public class Subject implements SubjectInterface {
    private ArrayList<ObserverInterface> observers;
    //gui sends data
    private ArtSupply data;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public ArrayList<ArtSupply> getData() {
        ArrayList<ArtSupply> data = new ArrayList<>();
        data.add(this.data);
        return data;
    }

    public void setData() {
        this.data = new ArtSupply("communication test");
        notifyObservers();
    }

    public void register(ObserverInterface observer) {
        if (observer != null && ! observers.contains(observer)) observers.add(observer);
    }

    public void unregister(ObserverInterface observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (ObserverInterface observer : observers) observer.update();
    }
}
