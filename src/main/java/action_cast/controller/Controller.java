package action_cast.controller;

import action_cast.data_store.DataStore;
import action_cast.model.DataModel;
import action_cast.model.Person;
import action_cast.model.Session;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.id.PersonID;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 12/1/2015.
 */
public class Controller {

    DataModel model;

    PeopleController peopleController;
    PerformanceController performanceController;
    PerformersController performersController;
    SessionController sessionController;

    public Controller() {
        ClassLoader classLoader = getClass().getClassLoader();
        DataStore store = new DataStore(classLoader.getResource("main.xml").getFile());
        try {
            store.load();
            model = store.getModel();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Session getCurrentSession() {
        return model.getCurrentSession();
    }

    public void assignPersonToCurrentSession(PersonID id) throws InvalidIDException {
        model.getCurrentSession().addPerson(id);
    }

    public void addPerson(String name) {
        model.addPerson(name);
    }

    public List<action_cast.controller.Data.Person> getPeople() {
        List<action_cast.controller.Data.Person> results = new ArrayList<>();
        for (Person person : model.getPeople()) {
            results.add(new action_cast.controller.Data.Person(person.getName()));
        }
        return results;
    }
}