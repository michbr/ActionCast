package action_cast.view;

import action_cast.controller.Controller;
import action_cast.data_store.DataStore;
import action_cast.model.*;
import action_cast.model.exceptions.InvalidIDException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private People people1;
    private ManageSessions manageSessions1;
    private TileViewTest tileViewTest1;

    private Controller controller;

    public Main() {
        controller = new Controller();

        people1.setController(controller);//.setData(store.getModel());
        manageSessions1.setController(controller);
    }

    public void resetData() throws JAXBException, SAXException, InvalidIDException {
        ClassLoader classLoader = getClass().getClassLoader();
        DataStore store = new DataStore(new DataModel());//new DataStore(classLoader.getResource("main.xml").getFile());
        //store.load();
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        endTime += 1000000000;
        DataModel model = store.getModel();
        model.setCurrentSession(new Session("Fall 2015", new Date(), new Date(endTime)));
        //model.addSession(new Performances(new Date(startTime - 2 * 1000000000), new Date(startTime - 1000000000)));
        Song song = model.addSong("The First Song", "It goes like this na na na, na na, na na na na");

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("mane chericter", "The main character", RoleType.MAIN));
        song.setRoles(roles);
        model.getCurrentSession().addPerformance(song, "First Performance", "First Venue", new Date());

        Person random_guy = model.addPerson("random guy");
        model.getCurrentSession().addPerson(random_guy);

        model.addPerson("Random Guy");

        store.save();
    }

    public static void main(String[] args) throws JAXBException, SAXException, InvalidIDException {
        JFrame frame = new JFrame("Action Cast");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
