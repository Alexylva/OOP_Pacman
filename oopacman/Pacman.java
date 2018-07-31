/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static oopacman.Actor.Status.IDLE;
import static oopacman.Key.*;
import static oopacman.OOPacman.mapObject;

/**
 *
 * @author Alexylva
 */
public class Pacman extends Actor {

    public Pacman(int x, int y) {
        super(x, y, "Pacman");
        setSize((int) Math.floor(OOPacman.gameAreaObject.getWidth() * 0.05)); //20:15
        setSpeed(10);
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        gc.setFill(new Color(1.00, 1.00, 0.50, 1.0));
        gc.setStroke(new Color(0, 0, 0, 1));
        gc.fillOval(getX(), getY(), getSize(), getSize());
        gc.strokeOval(getX(), getY(), getSize(), getSize());
    }
    String lastStatus = "";

    @Override
    public void update(GraphicsContext gc, double time) {
        String now = String.format("%s (%d,%d) => (%d,%d) direction:%s buffer:%s canTurn:%s\n", getStatus(), getGridX(), getGridY(), getX(), getY(), getDirection(), getBuffer(), canTurn());
        if (!lastStatus.equals(now)) {
            System.out.print(now);
        }
        lastStatus = now;
        if (isPressed(UP)) {
            direcionar(UP, getSpeed());
        } else if (isPressed(DOWN)) {
            direcionar(DOWN, getSpeed());
        } else if (isPressed(LEFT)) {
            direcionar(LEFT, getSpeed());
        } else if (isPressed(RIGHT)) {
            direcionar(RIGHT, getSpeed());
        }
        mover();
        if (mapObject.getStaticEntity(this.getGridX(), this.getGridY()) instanceof Path && !(((Path) mapObject.getStaticEntity(this.getGridX(), getGridY())).getCapturado())) {
            ((Path) mapObject.getStaticEntity(this.getGridX(), getGridY())).capturar();
            UserInterface.addScore(100);
        }
        for (GameObject g : OOPacman.entityObjectList) {
            if (g instanceof Ghost) {
                int psize = getSize()-7; // Mais fácil de ser pego
                int gsize = ((Ghost) g).getSize() + 10;
                int gleft = 10;
                int[] pacman = new int[]{getX(), getX() + psize, getY(), getY() + psize};
                int[] ghost = new int[]{((Ghost) g).getX()-gleft, ((Ghost) g).getX()-gleft + gsize, ((Ghost) g).getY()-gleft, ((Ghost) g).getY()-gleft + gsize};
                if (pacman[0] >= ghost[0] && pacman[0] <= ghost[0 + 1]) {
                    if (pacman[0 + 1] >= ghost[0] && pacman[0 + 1] <= ghost[0 + 1]) {
                        if (pacman[2] >= ghost[2] && pacman[2] <= ghost[2 + 1]) {
                            if (pacman[2 + 1] >= ghost[2] && pacman[2 + 1] <= ghost[2 + 1]) {
                                Draw.setMode(Draw.Mode.GAMEOVER);
                            }
                        }
                    }
                }
            }
        }
    }

}
