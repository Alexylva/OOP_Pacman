/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import static oopacman.Key.*;
import static oopacman.OOPacman.*;

/**
 *
 * @author Alexylva
 */
public class Draw extends AnimationTimer {

    public static long frameCount = 0;

    public static enum Mode {
        MENU, GAME, GAMEOVER, WIN
    };
    private static Mode mode;
    private int level, selection = 0;

    public Draw() {
        setMode(Mode.MENU);
    }

    @Override
    public void handle(long currentNanoTime) {

        switch (getMode()) {
            case MENU: //Spaguetti

                graphics.setFill(new Color(0, 0, 0, 1));
                graphics.fill();
                graphics.fillRect(0, 0, WIDTH, HEIGHT);
                graphics.setFill(new Color(1, 1, 1, 1));
                graphics.setFont(font);
                graphics.save();
                graphics.scale(2, 2);
                graphics.setFill(new Color(1, 1, .2, 1));
                graphics.fillText("OOPACMAN", WIDTH * 0.15, HEIGHT * 0.15);
                graphics.restore();

                sleep(100);

                if (isPressed(UP)) {
                    selection = ((selection == 0) ? mapsFileList.size() - 1 : (selection - 1) % mapsFileList.size());
                } else if (isPressed(DOWN)) {
                    selection = ((selection + 1) % mapsFileList.size());
                } else if (isPressed(RIGHT)) {
                    sleep(1000);
                    level = selection;
                    OOPacman.setupGame(level);
                }
                for (String c : mapsFileList) {
                    if (selection == mapsFileList.indexOf(c)) {
                        graphics.fillText("=> " + c, WIDTH / 4 + frameCount % 5, HEIGHT / 2 + mapsFileList.indexOf(c) * font.getSize() + 1);
                    } else {
                        graphics.fillText("   " + c, WIDTH / 4, HEIGHT / 2 + mapsFileList.indexOf(c) * font.getSize() + 1);
                    }
                }

                break;
            case GAME:
                graphics.setFill(new Color(0, 0, 0, 1));
                graphics.fill();
                graphics.fillRect(0, 0, WIDTH, HEIGHT);

                updateEntities(uiObjectList);
                renderEntities(uiObjectList);

                graphics.save();
                graphics.translate(gameAreaObject.getX(), gameAreaObject.getY()); //Seta novo ponto 0,0 no canto da gameArea
                updateEntities(mapObjectList);
                renderEntities(mapObjectList);
                updateEntities(entityObjectList);
                renderEntities(entityObjectList);
                graphics.restore();
                break;
            case GAMEOVER:
                sleep(1000);
                UserInterface.resetScore();
                OOPacman.setupGame(level);
                break;
            case WIN:
                sleep(1000);
                level++;
                if (level > mapsFileList.size() - 1) {
                    setMode(Draw.Mode.MENU);
                }
                OOPacman.setupGame(level);
                break;

        }
        frameCount++;
    }

    public Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        Draw.mode = mode;
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
        }
    }

    public void updateEntities(ArrayList<GameObject> gameobjects) {
        for (GameObject gob : gameobjects) {
            if (gob != null) {
                gob.update(graphics);
            }
        }
    }

    public void renderEntities(ArrayList<GameObject> gameobjects) {
        for (GameObject gob : gameobjects) {
            if (gob != null) {
                gob.render(graphics);
            }
        }
    }
}
