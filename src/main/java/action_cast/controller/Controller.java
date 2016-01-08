package action_cast.controller;

import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.Session;
import action_cast.controller.ClientObjects.Song;
import action_cast.data_store.DataStore;
import action_cast.model.DataModel;
import action_cast.model.exceptions.InvalidIDException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 12/1/2015.
 */
public class Controller {

    DataModel model;
    DataStore store;

    SessionController sessionController;

    public Controller() {
        ClassLoader classLoader = getClass().getClassLoader();
        store = new DataStore(classLoader.getResource("main.xml").getFile());
        try {
            store.load();
            model = store.getModel();
            sessionController = new SessionController(model.getCurrentSession(), this);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void assignPersonToCurrentSession(Person person) throws InvalidIDException {
        model.getCurrentSession().addPerson(model.getPerson(person.getId()));
    }

    public void assignSongToPerformance(int performanceId, int songId) throws InvalidIDException {
        model.getCurrentSession().getPerformance(performanceId).setSong(model.getSong(songId));
    }

    public void addPerson(String name) {
        model.addPerson(name);
    }

    public List<Person> getPeopleNotInCurrentSession() {
        List<Person> results = model.getPeople().stream().filter(person -> !model.getCurrentSession().hasPerson(person)).map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        //= model.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public List<Person> getPeople() {
        List<Person> results = model.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public List<Song> getSongs() {
        List<Song> results = model.getSongs().stream().map(song -> new Song(song.getIndex(), song.getName(), song.getDescription())).collect(Collectors.toList());
        return results;
    }

    public Song getSong(int id) throws InvalidIDException {
        action_cast.model.Song song = model.getSong(id);
        return new Song(song.getIndex(), song.getName(), song.getDescription());
    }

    public List<action_cast.controller.ClientObjects.Role> getRoles(int songId) throws InvalidIDException {

        return model.getSong(songId).getRoles().stream().map(role -> new action_cast.controller.ClientObjects.Role(role.getName(), role.getDescription(), role.getType())).collect(Collectors.toList());
    }

    public Session getCurrentSession() {
        return new Session(model.getCurrentSession().getName(), model.getCurrentSession().getStartDate(), model.getCurrentSession().getEndDate());
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void updateCurrentSession(Session session) {
        model.getCurrentSession().setName(session.getName());
        model.getCurrentSession().setEnd(session.getEndDate());
        model.getCurrentSession().setStart(session.getStartDate());
        save();
    }

    private void save() {
        try {
            store.save();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
