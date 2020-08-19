public class Model {

    private View view;
    private Controller controller;
    private State state;

    // Sample instance variables:
    private int time;
    private String typedChar = "";
    private Game game;

    public Model(Game game) {
        view = new View(this);
        controller = new Controller(this);
        state = new TitleState();
        this.game = game;
    }

    public synchronized void processTimeElapsed(int msec) {
        time++;
        state = state.processTimeElapsed(time);
        repaint();
    }

    public synchronized void processKeyTyped(String typed) {
        typedChar = typed;
        state = state.processKeyTyped(typed);
        if(typed.equals("ALT")) { //ALTキーでボスが来た画面に移行
        	game.setBossWindow();
        	State new_state = new BossState(state,game);
        	state = new_state;
        }
       repaint();
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
    
    public void repaint() {
    	view.repaint();
    }
    
    public State getState() {
    	return state;
    }

}
