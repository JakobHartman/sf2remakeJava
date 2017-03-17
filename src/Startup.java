import game.states.MainMenu;
import game.states.Play;
import game.states.Menu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;


public class Startup extends StateBasedGame {
    public static final int menu = 0;
    public static final int mainMenu = 1;
    public static final int play = 2;

    public Startup() {
        super("Shining Force 2");
        this.addState(new Menu(menu));
        this.addState(new MainMenu(mainMenu));
        this.addState(new Play(play));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(menu).init(gameContainer, this);
        this.getState(play).init(gameContainer, this);
        this.getState(mainMenu).init(gameContainer,this);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Startup());
            app.setDisplayMode(500, 500, false);
            app.setVSync(true);
            app.setAlwaysRender(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


}
