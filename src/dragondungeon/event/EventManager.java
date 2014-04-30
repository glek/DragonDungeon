package dragondungeon.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Event manager.
 * The event manager handles connecting event providers to event subscribers.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public class EventManager {

    /**
     * The global event manager.
     */
    private static EventManager s_globalManager;
    /**
     * A mapping of event types to their listeners.
     */
    private Map<Long, List<IEventListener>> m_listeners;
    /**
     * The event queues.
     */
    private List<BaseEvent>[] m_eventQueues;
    /**
     * The number of queues in the event manager.
     */
    private final int m_queueCount;
    /**
     * The current queue into which events are placed.
     */
    private int m_currentQueue;
    
    /**
     * Constructor.
     */
    public EventManager()
    {
        this(false);
    }
    
    /**
     * Constructor.
     * Making an event manager global destroys the old global event manager.
     * @param isGlobal a boolean indicating whether or not this event manager
     * is the global event manager.
     */
    public EventManager(boolean isGlobal)
    {
        this(isGlobal, 2);
    }
    
    /**
     * Constructor.
     * Making an event manager global destroys the old global event manager.
     * @param isGlobal a boolean indicating whether or not this event manager
     * is the global event manager.
     * @param queueCount the number of event queues to keep track of.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public EventManager(boolean isGlobal, int queueCount)
    {
        m_queueCount = queueCount;
        m_eventQueues = new ArrayList[m_queueCount];
        m_currentQueue = 0;
        m_listeners = new HashMap<>();
        if(isGlobal)
        {
            s_globalManager = this;
        }
    }
    
    /**
     * Add a listener for an event type.
     * @param eventType the type of event to listen for.
     * @param listener the listener who is listening for the events.
     * @return true if the listener was not already added and was added, false
     * otherwise.
     */
    public boolean AddEventListener(long eventType, IEventListener listener)
    {
        if(!m_listeners.containsKey(eventType))
        {
            m_listeners.put(eventType, new ArrayList<IEventListener>());
        }
        List<IEventListener> list = m_listeners.get(eventType);
        if(list.contains(listener))
        {
            return false;
        }
        return list.add(listener);
    }
    
    /**
     * Remove a listener from an event type.
     * @param eventType the type of event to stop listening for.
     * @param listener the listener to remove.
     * @return true if the listener was listening to the given event type and
     * was removed, false otherwise.
     */
    public boolean RemoveEventListener(long eventType, IEventListener listener)
    {
        if(!m_listeners.containsKey(eventType))
        {
            return false;
        }
        List<IEventListener> list = m_listeners.get(eventType);
        if(!list.contains(listener))
        {
            return false;
        }
        return list.remove(listener);
    }
    
    /**
     * Fire an event.
     * @param e the event to fire.
     * @return true if at least one event listener was registered to handle the
     * event, false otherwise.
     */
    public boolean FireEvent(BaseEvent e)
    {
        if(!m_listeners.containsKey(e.GetType()))
        {
            return false;
        }
        List<IEventListener> list = m_listeners.get(e.GetType());
        if(list.isEmpty())
        {
            return false;
        }
        for(IEventListener l : list)
        {
            l.HandleEvent(e);
        }
        return true;
    }
    
    /**
     * Queue an event for processing.
     * Events will not be queued if there are no listeners listening for them.
     * @param e the event to queue.
     * @return true if at least one event listener was listening for the event
     * and the event was added to the queue, false otherwise.
     */
    public boolean QueueEvent(BaseEvent e)
    {
        if(!m_listeners.containsKey(e.GetType()))
        {
            return false;
        }
        if(m_listeners.get(e.GetType()).isEmpty())
        {
           return false; 
        }
        List<BaseEvent> queue = m_eventQueues[m_currentQueue];
        return queue.add(e);
    }
    
    //TODO Remove events?
    
    /**
     * Process the event queue.
     * @param timeout the maximum time to take processing the queue.
     * @return true if the entire queue was processed, false otherwise.
     */
    public boolean ProcessEvents(long timeout)
    {
        long startTime = System.currentTimeMillis();
        List<BaseEvent> queue = m_eventQueues[m_currentQueue];
        m_currentQueue = (m_currentQueue + 1) % m_queueCount;
        long delta = System.currentTimeMillis() - startTime;
        while(!queue.isEmpty() && delta < timeout)
        {
            BaseEvent e = queue.remove(0);
            if(!FireEvent(e))
            {
                //TODO Log
            }
            delta = System.currentTimeMillis() - startTime;
        }
        if(!queue.isEmpty())
        {
            while(!queue.isEmpty())
            {
                BaseEvent e = queue.remove(queue.size() - 1);
                m_eventQueues[m_currentQueue].add(0, e);
            }
            return false;
        }
        return true;
    }
}
