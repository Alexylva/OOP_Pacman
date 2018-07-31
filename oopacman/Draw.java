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
            case MENU:
                
                graphics.setFill(new Color(0, 0, 0, 1));
                graphics.fill();
                graphics.fillRect(0, 0, WIDTH, HEIGHT);
                graphics.setFill(new Color(1, 1, 1, 1));
                graphics.setFont(Font.font("Tahoma", 20));
                 try {Thread.sleep(100);} catch (Exception e) {}
                if(isPressed(UP)) {
                    selection = ((selection==0)?mapsFileList.size()-1:(selection-1)%mapsFileList.size());
                } else if (isPressed(DOWN)) {
                    selection = ((selection+1)%mapsFileList.size());
                } else if (isPressed(RIGHT)) {
                  try {Thread.sleep(1000);} catch (Exception e) {}
                  OOPacman.setupObjects();
                  OOPacman.mapObject = new Map(mapsFileList.get(selection));
                  mapObjectList.add(mapObject);
                  setMode(Mode.GAME);
                }                
                for (String c : mapsFileList) {
                    if (selection == mapsFileList.indexOf(c)) {
                        graphics.fillText("=> " + c, WIDTH/4, HEIGHT/2+mapsFileList.indexOf(c)*20);
                    } else {
                        graphics.fillText("   " + c, WIDTH/4, HEIGHT/2+mapsFileList.indexOf(c)*20);
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
                renderEntities(mapObjectList);
                updateEntities(entityObjectList);
                renderEntities(entityObjectList);
                graphics.restore();
                frameCount++;
                break;
            case GAMEOVER:
                try {Thread.sleep(1000);} catch (Exception e) {}
                UserInterface.resetScore();
            case WIN:
                setMode(Mode.MENU); //TODO
                break;
            default:
                throw new AssertionError(getMode().name());

        }
    }

    public Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        Draw.mode = mode;
    }

    public void updateEntities(ArrayList<GameObject> gameobjects) {
        for (GameObject gob : gameobjects) {
            if (gob != null) {
                gob.update(graphics);
            }
        }
    }

    /**
     * Realiza a logica de renderização de cada entidade
     *
     * @param gameobjects Array de entidades
     * @param time Tempo atual
     */
    public void renderEntities(ArrayList<GameObject> gameobjects) {
        for (GameObject gob : gameobjects) {
            if (gob != null) {
                gob.render(graphics);
            }
        }
    }
}
