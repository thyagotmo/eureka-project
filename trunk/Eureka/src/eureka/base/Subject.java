package eureka.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que cont�m v�rios observadores cadastrados em diferentes t�picos
 */
public class Subject {

    public static final String UNDEFINED = "_UNDEFINED_TOPIC";
    private List<String> topics;
    private Map<String, List<Observer>> observers;

    public Subject() {
        this.observers = new HashMap<String, List<Observer>>();
        this.topics = new ArrayList<String>();
        topics.add(UNDEFINED);
        observers.put(UNDEFINED, new ArrayList<Observer>());
    }

    protected void addTopic(String topic) {
        this.topics.add(topic);
    }

    public List<String> getTopics() {
        return this.topics;
    }

    public void addObserver(Observer observer) {
        observers.get(UNDEFINED).add(observer);
    }

    public void addObserver(String topic, Observer observer) {
        if (!observers.containsKey(topic)) {
            observers.put(topic, new ArrayList<Observer>());
        }
        observers.get(topic).add(observer);
    }

    public void removeObserver(Observer observer) {
        for (List<Observer> obsList : observers.values()) {
            obsList.remove(observer);
        }
    }

    public void removeObserver(Observer observer, String topic) {
        List<Observer> list = observers.get(topic);
        if (list != null) {
            list.remove(observer);
        }
    }

    public void notifyAllObservers() {
        for (List<Observer> list : observers.values()) {
            for (Observer observer : list) {
                observer.update();
            }
        }
    }

    public void notifyObservers(String topic) {
        List<Observer> list = observers.get(topic);
        if (list != null) {
            for (Observer observer : list) {
                observer.update();
            }
        }
    }
}
