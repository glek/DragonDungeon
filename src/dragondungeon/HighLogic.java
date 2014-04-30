package dragondungeon;

/**
 * A class for managing high-level game logic.
 * The high logic class keeps track of various high-level information about a
 * game, such as whether or not it's paused, whether it's in-game or not, etc.
 * @author M. Damian Mulligan (G'lek Tarssza)
 */
public class HighLogic {

    /**
     * Singleton instance.
     */
    private static final HighLogic s_singleton = new HighLogic();
    /**
     * The time at which the game was started.
     */
    private long m_startTime;
    /**
     * Whether or not the game is paused.
     */
    private boolean m_paused;
    /**
     * Whether or not the game is actually in the game.
     */
    private boolean m_ingame;
    
    /**
     * Constructor.
     */
    private HighLogic()
    {
        m_paused = false;
        m_ingame = false;
        m_startTime = System.currentTimeMillis();
    }
    
    /**
     * Get the global high logic class.
     * @return the global high logic class.
     */
    public static HighLogic GetGlobal()
    {
        return s_singleton;
    }
    
    /**
     * Get the time at which the game was started.
     * @return the time at which the game was started.
     */
    public long GetStartTime()
    {
        return m_startTime;
    }
    
    /**
     * Get whether or not the game is paused.
     * @return true if the game is paused, false otherwise.
     */
    public boolean IsPaused()
    {
        return m_paused;
    }
    
    /**
     * Pause the game.
     */
    public void Pause()
    {
        SetPause(true);
    }
    
    /**
     * Set whether or not the game is paused.
     * @param arg the boolean indicating whether or not the game is paused.
     */
    public void SetPause(boolean arg)
    {
        m_paused = arg;
    }
}
