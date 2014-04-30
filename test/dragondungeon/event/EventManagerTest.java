/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dragondungeon.event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public class EventManagerTest
{
    /**
     * The event manager being tested.
     */
    private EventManager m_manager;
    /**
     * The stub event listener.
     */
    private EventListenerStub m_stub;
    
    /**
     * Constructor.
     */
    public EventManagerTest ()
    {
        m_manager = new EventManager(true);
        m_stub = new EventListenerStub();
    }

    /**
     * Test of AddEventListener method, of class EventManager.
     */
    @Test
    public void testAddEventListener ()
    {
        
    }

    /**
     * Test of RemoveEventListener method, of class EventManager.
     */
    @Test
    public void testRemoveEventListener ()
    {
    }

    /**
     * Test of FireEvent method, of class EventManager.
     */
    @Test
    public void testFireEvent ()
    {
    }

    /**
     * Test of QueueEvent method, of class EventManager.
     */
    @Test
    public void testQueueEvent ()
    {
    }

    /**
     * Test of ProcessEvents method, of class EventManager.
     */
    @Test
    public void testProcessEvents ()
    {
    }
}