/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static oopacman.OOPacman.*;

/**
 *
 * @author Alexylva
 */
public class Draw extends AnimationTimer {

    public static long frameCount = 0;
    private LongValue lastNanoTime;

    public static enum Mode {
        MENU, GAME, GAMEOVER, WIN
    };
    private Mode mode;
    private int level;

    public Draw(LongValue lastNanoTime) {
        this.lastNanoTime = lastNanoTime;
        setMode(Mode.MENU);
    }

    @Override
    public void handle(long currentNanoTime) {
        double elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
        switch (getMode()) {
            case MENU:
                gc.setFill(new Color(0, 0, 0, 1));
                gc.fill();
                gc.fillRect(0, 0, width, height);
                gc.setFill(new Color(1, 1, 1, 1));
                gc.setFont(Font.font("Tahoma", 20));
                for (String c : mapsList) {
                    gc.fillText(c, width/4, height/2+mapsList.indexOf(c)*20);
                }
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
            case GAME:
                gc.setFill(new Color(0, 0, 0, 1));
                gc.fill();
                gc.fillRect(0, 0, width, height);

                updateEntities(uiObject, elapsedTime);
                renderEntities(uiObject, elapsedTime);

                gc.save();
                gc.translate(ga.getX(), ga.getY()); //Seta novo ponto 0,0 no canto da gameArea
                renderEntities(mapObject, elapsedTime);
                updateEntities(entityObject, elapsedTime);
                renderEntities(entityObject, elapsedTime);
                gc.restore();
                frameCount++;
                break;
            case WIN:
                break;
            case GAMEOVER:
                break;
            default:
                throw new AssertionError(getMode().name());

        }
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void updateEntities(ArrayList<GameObject> gameobjects, double time) {
        for (GameObject gob : gameobjects) {
            if (gob != null) {
                gob.update(gc, time);
            }
        }
    }

    /**
     * Realiza a logica de renderização de cada entidade
     *
     * @param gameobjects Array de entidades
     * @param time Tempo atual
     */
    public void renderEntities(ArrayList<GameObject> gameobjects, double time) {
        for (GameObject gob : gameobjects) {
            if (gob != null) {
                gob.render(gc, time);
            }
        }
    }
}
