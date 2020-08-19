public class Model {

    private View view;
    private Controller controller;
    private State state;

    // Sample instance variables:
    private int time;
    private String typedChar = "";
    private int mx;
    private int my;

    public Model() {
        view = new View(this);
        controller = new Controller(this);
        state = new TitleState();
    }

    public synchronized void processTimeElapsed(int msec) {
        time++;
        state = state.processTimeElapsed(time);
        view.repaint();
    }

    public synchronized void processKeyTyped(String typed) {
        typedChar = typed;
        view.reloadText();
        state = state.processKeyTyped(typed);
        if(typed.equals("ALT")) {
        	State new_state = new BossState(state);
        	state = new_state;
        }
        //view.repaint();
    }

    public synchronized void processMousePressed(int x, int y) {
        mx = x;
        my = y;
        /*view.playBombSound();
        view.repaint();*/
    }

    public void start() {
        controller.start();
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public int getTime() {
        return time;
    }

    public String getTypedChar() {
        return typedChar;
    }

    public int getMX() {
        return mx;
    }

    public int getMY() {
        return my;
    }
    
    public void repaint() {
    	view.repaint();
    }
    
    public State getState() {
    	return state;
    }

}
