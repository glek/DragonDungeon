package dragondungeon.gui;

import dragondungeon.event.EventManager;
import java.util.List;

/**
 * A desktop to hold GUI widgets.
 * A desktop is similar to a container, but at the top level. It is, thus, a
 * container with no parents that can directly interface with JSFML to provide
 * a unified interface for drawing and updating the GUI as well as for
 * processing events. It uses its own internal event manager.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public class Desktop {

    /**
     * The GUI's event manager.
     */
    private EventManager m_eventManager;
    /**
     * A list of widgets contained in this desktop.
     */
    private List<Widget> m_widgets;
}
