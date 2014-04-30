package dragondungeon.event;

/**
 * Interface for event listeners.
 * An event listener is a class that listens for events.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public interface IEventListener {

    /**
     * Handle an event.
     * @param e the event to handle.
     */
    public void HandleEvent(BaseEvent e);
}
