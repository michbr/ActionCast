package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class SessionTest {

    @Test
    public void testCreate() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Date start = new Date(startTime);
        Thread.sleep(5);
        Long endTime = System.currentTimeMillis();
        Date end = new Date(endTime);
        Session s = new Session("sessionName", start, end);
        assertNotNull(s);

        Date expectedStart = new Date(startTime);
        Date expectedEnd = new Date(endTime);

        assertEquals(expectedStart, s.getStartDate());
        assertEquals(expectedEnd, s.getEndDate());
        assertEquals("sessionName", s.getName());
    }

    @Test
    public void testPeopleList() throws InvalidIDException {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session("someName", start, end);

        DataModel model = new DataModel();
        Person person = model.addPerson("SomeGuy","","");
        assertEquals(0, s.getPeople().size());
        s.addPerson(model.getPerson(person.getIndex()));
        assertEquals(1, s.getPeople().size());

    }

    @Ignore
    @Test
    public void testPeopleList_NonExistentPerson()  {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session("someName", start, end);

        assertEquals(0, s.getPeople().size());
        boolean thrown = false;
        s.addPerson(new Person(-1, "guy","",""));

        assertTrue(thrown);
        assertEquals(0, s.getPeople().size());

    }
}