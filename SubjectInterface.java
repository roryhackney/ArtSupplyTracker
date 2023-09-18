import java.util.ArrayList;

public interface SubjectInterface {
    public ArrayList<ArtSupply> getData();
    public void register(ObserverInterface observer);
    public void unregister(ObserverInterface observer);
    public void notifyObservers();
    public void setData();
}
