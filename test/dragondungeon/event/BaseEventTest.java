/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dragondungeon.event;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit Test for BaseEvent.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public class BaseEventTest
{
    /**
     * Constructor.
     */
    public BaseEventTest ()
    {
        //Do nothing
    }

    /**
     * Test of GetSource method, of class BaseEvent.
     */
    @Test
    public void testGetSource ()
    {
        BaseEvent e = new BaseEvent();
        assertEquals("Default source should be NO_SOURCE", BaseEvent.NO_SOURCE, e.GetSource());
        e = new BaseEvent(10, BaseEvent.NO_TYPE);
        assertEquals("Source should be 10", 10, e.GetSource());
    }

    /**
     * Test of GetType method, of class BaseEvent.
     */
    @Test
    public void testGetType ()
    {
        BaseEvent e = new BaseEvent();
        assertEquals("Default type should be NO_TYPE", BaseEvent.NO_TYPE, e.GetType());
        e = new BaseEvent(BaseEvent.NO_SOURCE, 10);
        assertEquals("Type should be 10", 10, e.GetType());
    }
}