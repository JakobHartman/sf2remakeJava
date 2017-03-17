package game.states;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import util.DialogUtil;

import java.util.List;

public class MainMenu extends BasicGameState {

    private int id;
    private Image mitulaOld;
    private Music witchTheme;
    private Animation faceAnimation;
    private Image originFace;
    private RoundedRectangle rect;
    private boolean showFace;
    private boolean showTalkAnimation;
    private List<String> lines;
    private boolean dialogBoxShow = false;


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
        rect = new RoundedRectangle(0, 260, gameContainer.getWidth(), (gameContainer.getHeight() / 3), 15);
        lines = DialogUtil.wrap("Hee, hee, hee... your're finally here! Ah, you look so confused. You don't know why your here?", gameContainer.getDefaultFont(), 460);
    }

    private int renderRow = 0;
    private int renderCol = 0;

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
            dialogBoxShow = true;
        }

        if (dialogBoxShow) {
            ShapeFill fill = new GradientFill(rect.getWidth(), 0, new Color(0, 0, 255, .7f), rect.getWidth(), rect.getHeight() + 10, new Color(0, 0, 255, .2f), true);
            graphics.fill(rect, fill);

            int lineHeight = gameContainer.getDefaultFont().getLineHeight();

            int dx = 20;
            int dy = 300;
            for (int i = 0; i < renderRow + 1; i++) {
                String line = lines.get(i);
                int len = i < renderRow ? line.length() : renderCol;
                String t = line.substring(0, len);
                if (t.length() != 0) {
                    graphics.drawString(t, dx, dy);
                }
                dy += lineHeight;
            }

        }


    }

    private long pastTime = 0;
    private boolean finished = false;
    private boolean canFirst = true;
    private boolean canSec = false;
    private boolean canThird = false;
    private boolean canClear = false;

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (pastTime == 10) {
            showFace = true;
        }

        if (pastTime == 60) {
            showTalkAnimation = true;

        }

        if (pastTime > 60 && !finished) {
            if (pastTime % 5 == 0) {
                String line = lines.get(renderRow);

                if (renderCol > line.length() - 1) {
                    if (renderRow >= lines.size() - 1) {
                        finished = true;
                        showTalkAnimation = false;
                        if (canFirst) {
                            canSec = true;
                        } else if (canSec) {
                            canSec = false;
                            canThird = true;
                        } else if (canThird){
                            canThird = false;
                            canClear = true;
                        }
                        canFirst = false;
                    } else {
                        renderRow++;
                        renderCol = 0;
                    }
                } else {
                    renderCol++;
                }
            }
        }

        Input input = gameContainer.getInput();
        boolean spacePressed = input.isKeyPressed(Input.KEY_SPACE);

        if (spacePressed && canSec) {
            changeDialog("Yes, yes... I used a spell on you.\n\n Ha, ha where you are going?",gameContainer.getDefaultFont());
        } else if (spacePressed && canThird) {
            changeDialog("You can't escape from this mysterious forest unless you help me? Whatcha gonna do?", gameContainer.getDefaultFont());
        } else if (spacePressed && canClear){
            dialogBoxShow = false;
        }
        pastTime++;
    }


    private void changeDialog(String text, Font font) {
        showTalkAnimation = true;
        lines = DialogUtil.wrap(text, font, 460);
        renderCol = 0;
        renderRow = 0;
        finished = false;
    }
}
