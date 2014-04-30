package dragondungeon.gui;

import dragondungeon.event.IEventListener;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

/**
 * A basic widget.
 * A widget is the most basic form of GUI element and provides only the most
 * basic of information. It is meant to be expanded upon by more concrete
 * GUI elements.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public abstract class Widget implements IEventListener {

    /**
     * The area allocated to this widget.
     */
    private FloatRect m_allocation;
    /**
     * Whether or not the widget has had its allocation manually set.
     */
    private boolean m_manualAllocation;
    /**
     * The parent of the widget.
     */
    private Container m_parent;
    /**
     * The desktop who contains the widget.
     */
    private Desktop m_desktop;
    
    /**
     * Constructor.
     */
    protected Widget()
    {
        m_allocation = new FloatRect(0, 0, 0, 0);
        m_manualAllocation = false;
        m_parent = null;
        m_desktop = null;
    }
    
    /**
     * Get the desktop that owns the widget.
     * If the widget does not have a desktop, then it cannot be displayed.
     * Calling the Draw method of a widget with no desktop produces undefined
     * behavior.
     * @return the desktop that owns the widget, or null if the widget has not
     * been added to a desktop yet.
     */
    public Desktop GetDesktop()
    {
        return m_desktop;
    }
    
    /**
     * Set the desktop who owns the widget.
     * @param desktop the desktop who owns the widget.
     */
    void SetDesktop(Desktop desktop)
    {
        m_desktop = desktop;
    }
    
    /**
     * Get the parent of the widget.
     * If the widget does not have a parent, then the desktop that owns the
     * widget is considered the 'logical' parent of the widget.
     * @return the container that contains the widget, or null if the widget
     * is not in a container.
     */
    public Container GetParent()
    {
        return m_parent;
    }
    
    /**
     * Set the parent of the widget.
     * @param parent the parent container of the widget.
     */
    void SetParent(Container parent)
    {
        m_parent = parent;
        if(m_parent != null)
        {
            SetDesktop(m_parent.GetDesktop());
        }
    }
    
    /**
     * Get the allocation of the widget.
     * The allocation of the widget is the combined position and size data.
     * @return the allocation (position + size) of the widget.
     */
    public FloatRect GetAllocation()
    {
        return m_allocation;
    }
    
    /**
     * Set the allocation of the widget.
     * An allocation of (X, Y, -1, -1) has special meaning. It informs the
     * GUI sub-system to allocate the widget's size automatically based on its
     * content.
     * @param allocation the allocation of the widget.
     * @throws IllegalArgumentException if the allocation is not valid.
     */
    public void SetAllocation(FloatRect allocation) throws IllegalArgumentException
    {
        m_allocation = allocation;
        if(m_allocation.height == -1 && m_allocation.width == -1)
        {
            m_manualAllocation = false;
        }
        else if(m_allocation.height >= 0 && m_allocation.width >= 0)
        {
            m_manualAllocation = true;
        }
        else
        {
            throw new IllegalArgumentException("Invalid size allocation");
        }
    }
    
    public void PerformAllocation()
    {
        if(m_manualAllocation)
        {
            return;
        }
        Vector2f size = GetContentSize();
    }
    
    /**
     * Get the size of the widget's content.
     * @return the size of the widget's content.
     */
    public abstract Vector2f GetContentSize();
    
    /**
     * Update the widget.
     * @param delta the time, in fractional seconds, since the last update.
     */
    public abstract void Update(float delta);
    
    /**
     * Draw the widget.
     * @param target the target to draw to.
     */
    public abstract void Draw(RenderTarget target);
}
