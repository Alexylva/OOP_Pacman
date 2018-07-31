/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import static oopacman.Actor.Status.IDLE;
import static oopacman.Key.*;
import static oopacman.OOPacman.mapObject;

/**
 *
 * @author Alexylva
 */
public class Pacman extends Actor {

    private double arc_start = 0;//posicao inicial da parte superior da boca
    private double arc_length = 360.0;//comprimento do arco inicial (começa como um circulo completo)
    private String mouth = "OPENING";

    public Pacman(int x, int y) {
        super(x, y, "Pacman");
        setSize((int) Math.floor(OOPacman.gameAreaObject.getWidth() * 0.05)); //20:15
        setSpeed(10);
    }

    @Override
    public void render(GraphicsContext graphics) {
        graphics.save();
        graphics.transform(new Affine(new Rotate(gira(), getX() + getSize() / 2, getY() + getSize() / 2)));
        graphics.setFill(Color.YELLOW);
        graphics.setStroke(new Color(0, 0, 0, 1));
        graphics.arc(getX(), getY(), this.getSize(), this.getSize(), this.arc_start, this.arc_length);
        graphics.fillArc(getX(), getY(), this.getSize(), this.getSize(), this.arc_start, this.arc_length, ArcType.ROUND);
        graphics.strokeArc(getX(), getY(), this.getSize(), this.getSize(), this.arc_start, this.arc_length, ArcType.ROUND);
        graphics.restore();
    }
    String lastStatus = "";

    @Override
    public void update(GraphicsContext graphics) {
        if (getStatus() != IDLE) {
            movimentoBoca();//calcula a abertura da boca
        }
        direcionar(); //Obtem comandos do jogador
        mover(); //Realiza o movimento
        checkPathCollision(); //Colidiu com Path?
        checkGhostCollision(); //Colidiu com Ghost?
    }
    
    private void movimentoBoca() {
        if ("OPENING".equals(mouth)) {
            if (arc_start < 60) {
                arc_start = arc_start + getSpeed()*2.5;
                arc_length = arc_length - 2 * getSpeed()*2.5;
            } else {
                mouth = "CLOSING";
            }
        }
        if ("CLOSING".equals(mouth)) {
            if (arc_start > 0) {
                arc_start = arc_start - getSpeed()*3;
                arc_length = arc_length + 2 * getSpeed()*3;
            } else {
                mouth = "OPENING";
            }
        }
    }

    private double gira() {
        switch (this.getDirection()) {
            case UP:
                return 270.0;
            case DOWN:
                return 90.0;
            case RIGHT:
                return 0.0;
            case LEFT:
                return 180.0;
            default:
                return 0.0;
        }
    }

    private void direcionar() {
        if (isPressed(UP)) {
            direcionar(UP, getSpeed());
        } else if (isPressed(DOWN)) {
            direcionar(DOWN, getSpeed());
        } else if (isPressed(LEFT)) {
            direcionar(LEFT, getSpeed());
        } else if (isPressed(RIGHT)) {
            direcionar(RIGHT, getSpeed());
        }
    }

    private void checkPathCollision() {
        if (mapObject.getStaticEntity(this.getGridX(), this.getGridY()) instanceof Path && !(((Path) mapObject.getStaticEntity(this.getGridX(), getGridY())).getCapturado())) {
            ((Path) mapObject.getStaticEntity(this.getGridX(), getGridY())).capturar();
            UserInterface.addScore(100);
        }
    }

    private void checkGhostCollision() {
        for (GameObject g : OOPacman.entityObjectList) {
            if (g instanceof Ghost) {
                int psize = getSize() - 7; // Mais fácil de ser pego
                int gsize = ((Ghost) g).getSize() + 10;
                int gleft = 10;
                int[] pacman = new int[]{getX(), getX() + psize, getY(), getY() + psize};
                int[] ghost = new int[]{((Ghost) g).getX() - gleft, ((Ghost) g).getX() - gleft + gsize, ((Ghost) g).getY() - gleft, ((Ghost) g).getY() - gleft + gsize};
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
