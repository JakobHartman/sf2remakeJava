package game.states;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.Font;
import java.util.ArrayList;


public class Menu extends BasicGameState {

    private TrueTypeFont font;
    private Font mainFont = null;
    private ArrayList<String> menuItems;
    private Image image;
    private Music music;
    private int id;

    public Menu(int state) {
        this.id = state;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.image = new Image("resources/images/S2_Logo.png");
        this.menuItems = new ArrayList<>();
        this.menuItems.add("Press any key to start");
        music = new Music("resources/audio/music/Battle2.ogg");

        try {
            this.mainFont = new Font("JOYSTIX", Font.PLAIN, 20);
            this.font = new TrueTypeFont(this.mainFont, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        for (int i = 0; i < menuItems.size(); i++) {
//            String item = menuItems.get(i);
//            TrueTypeFont font = new TrueTypeFont(this.mainFont, true);
//            float tWidth = font.getWidth(item);
//            float tHeight = font.getHeight(item);
//            float t_h = menuItems.size() * tHeight;
//            float x = (gameContainer.getWidth() / 2) - (tWidth / 2);
//            float y = (gameContainer.getHeight() / 2) - (t_h / 2) + ((5 * 2) + 5 * tHeight);
//        }
        if (!music.playing()) {
            music.loop();
        }
        graphics.drawImage(this.image.getScaledCopy(gameContainer.getWidth(), 200), 0, 50);
        graphics.setFont(font);
        graphics.drawString("Press any key to start", 85, 359);


        Input input = gameContainer.getInput();
        if (keyPressed(input)) {
            music.fade(500, 0f, true);
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }


    public Boolean keyPressed(Input input) {
        for (int i = 0; i < 223; i++) {
            if (input.isKeyPressed(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
