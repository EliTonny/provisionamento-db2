package Sistema;

public interface Subject {
    public void registerObserver(Observer obs);
    public void removeOberser(Observer obs);
    public void notifyObservers(Object obj);
}
