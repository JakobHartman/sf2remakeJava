package game.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

    private int id;
    private Image mitulaOld;
    private Music witchTheme;
    private Animation faceAnimation;
    private Image originFace;

    private boolean showFace;
    private boolean showTalkAnimation;


    public MainMenu(int state) {
        this.id = state;
    }

    @Override
    public int getID() {
        return this.id;
    }


    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        // 564 x 446
        Image mitulaMain = new Image("resources/images/sprites/Mitula Old Woman.png");
        Image mitulaFaces = mitulaMain.getSubImage(33, 10, 182, 49);
        mitulaOld = mitulaMain.getSubImage(292, 223, 262, 213).getScaledCopy(gameContainer.getWidth(), gameContainer.getHeight());
        showFace = false;
        showTalkAnimation = false;
        witchTheme = new Music("resources/audio/music/Witch's Theme.ogg");
        SpriteSheet mitulaSheet = new SpriteSheet(mitulaFaces, 57, 49, 5);
        originFace = mitulaSheet.getSprite(0, 0).getScaledCopy(111, 118);
        Image[] faceTalkAnimation = {originFace, mitulaSheet.getSprite(2, 0).getScaledCopy(111, 118), mitulaSheet.getSprite(0, 0).getScaledCopy(111, 118)};
        faceAnimation = new Animation(faceTalkAnimation, 3);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        mitulaOld.draw(-10, 0);
        if (!witchTheme.playing()) {
            witchTheme.loop();
        }
        if (showFace) {
            originFace.draw(212, 74);
        }

        if (showTalkAnimation) {
            faceAnimation.draw(212, 74);
        }

    }

    private long pastTime = 0;

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (pastTime == 10) {
            showFace = true;
        }

        if (pastTime == 60) {
            showTalkAnimation = true;
        }

        if(pastTime == 200){
            showTalkAnimation = false;
        }
        pastTime++;

    }
}
