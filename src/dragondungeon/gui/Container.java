package dragondungeon.gui;

import java.util.List;

/**
 * A container widget.
 * A container is the most basic form of widget that can contain other widgets.
 * Unlike a desktop, it must have a parent. The container is not a concrete
 * widget in and of itself but rather provides the basic information needed
 * to manage contained widgets.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public abstract class Container extends Widget {

    /**
     * A list of widgets contained by the container.
     */
    private List<Widget> m_widgets;
}
