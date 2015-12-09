package action_cast.controller;

import action_cast.controller.ClientObjects.Person;
import action_cast.model.Session;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.modelinterface.PerformanceView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brian on 12/3/2015.
 */
public class SessionController {

    private final Session session;
    private final Controller controller;

    public SessionController(Session session, Controller controller) {
        this.session = session;
        this.controller = controller;
    }

    public List<Person> getPeople() {
        List<Person> results = session.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public void setPeople(List<Person> people) throws InvalidIDException {
        session.clearPeople();
        for (Person person : people) {
           controller.assignPersonToCurrentSession(person);
        }
    }

    public List<PerformanceView> getPerformances() {
        return session.getPerformances().stream().map(performance -> ((PerformanceView)performance)).collect(Collectors.toList());
    }
}
