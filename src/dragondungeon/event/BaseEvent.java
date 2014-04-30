package dragondungeon.event;

import dragondungeon.HighLogic;

/**
 * A basic event.
 * A basic event contains only the base information needed to handle an event.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public class BaseEvent {

    /**
     * A constant for no source of an event.
     */
    public static final long NO_SOURCE = -1;
    /**
     * A constant for no type of an event.
     */
    public static final long NO_TYPE = -1;
    
    /**
     * The source of the event.
     */
    private long m_source;
    /**
     * The type of the event.
     */
    private long m_type;
    /**
     * The time at which the event was created.
     */
    private long m_time;
    
    /**
     * Constructor.
     */
    public BaseEvent()
    {
        this(NO_SOURCE, NO_TYPE);
    }
    
    /**
     * Constructor.
     * @param source the source of the event.
     * @param type the type of the event.
     */
    public BaseEvent(long source, long type)
    {
        m_source = source;
        m_type = type;
        m_time = System.currentTimeMillis();
    }
    
    /**
     * Get the source of the event.
     * @return the source of the event.
     */
    public long GetSource()
    {
        return m_source;
    }
    
    /**
     * Get the type of the event.
     * @return the type of the event.
     */
    public long GetType()
    {
        return m_type;
    }
    
    @Override
    public String toString()
    {
        float floatingTime = m_time - HighLogic.GetGlobal().GetStartTime();
        floatingTime /= 1000.0f;
        StringBuilder builder = new StringBuilder();
        builder.append("Event type ").append(m_type).append(" from ");
        builder.append(m_source).append(" created at ").append(floatingTime);
        return builder.toString();
    }
}
