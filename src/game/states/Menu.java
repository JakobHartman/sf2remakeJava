package game.states;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.Font;
import java.io.File;


public class Menu extends BasicGameState {

    private TrueTypeFont font;
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
        File fontFile = new File("resources/font/JOYSTIX.TTF");
        this.image = new Image("resources/images/S2_Logo.png");
        music = new Music("resources/audio/music/Battle2.ogg");
        try {
            Font mainFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            this.font = new TrueTypeFont(mainFont.deriveFont(20f), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
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

    private Boolean keyPressed(Input input) {
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
