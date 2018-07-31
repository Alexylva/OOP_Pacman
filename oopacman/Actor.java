/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopacman;

import static oopacman.Actor.Status.*;
import static oopacman.Key.*;
import static oopacman.OOPacman.mapObject;

/**
 *
 * @author Alexylva
 */
abstract class Actor extends Entity {

    enum Status {
        IDLE, MOVING, BUFFERED, SPECIAL
    };

    private int speed;
    private Key buffer, direction = UP;
    private Status status;

    public Actor(int x, int y, String tipo) {
        super(x, y, tipo);
    }

    public void mover(Key dir, int step) {
        switch (dir) {
            case UP:
                setY(getY() - step);
                break;
            case DOWN:
                setY(getY() + step);
                break;
            case RIGHT:
                setX(getX() + step);
                break;
            case LEFT:
                setX(getX() - step);
                break;
        }
    }

    public void direcionar(Key dir, int step) {
        if (getStatus() == MOVING || getStatus() == IDLE) {
            setBuffer(dir);
            return;
        }
    }

    public void mover() {
        //System.out.printf("(%d,%d) => (%d,%d) => (%.2f, %.2f) [%f,%f]", getX(), getY(), grid[1], grid[0], gridf[1], gridf[0], getSubX(), getSubY());
        Wall wall;
        if (null != getStatus()) {
            switch (getStatus()) {
                case IDLE:
                    break;
                case BUFFERED:
                    if (canTurn()) {
                        setDirection(getBuffer());
                        setStatus(MOVING);
                    }
                case MOVING:
                    wall = mapObject.dirIsFree(getGridX(), getGridY(), getDirection());
                    if (wall != null) {
                        switch (getDirection()) {
                            case UP:
                                if (getY() <= (wall.getY() + wall.getSize() + 2)) {
                                    setStatus(IDLE);
                                    return;
                                }
                                break;
                            case DOWN:
                                if (getY() >= (wall.getY() - wall.getSize() + 2)) {
                                    setStatus(IDLE);
                                    return;
                                }
                                break;
                            case LEFT:
                                if (getX() <= (wall.getX() + wall.getSize() + 2)) {
                                    setStatus(IDLE);
                                    return;
                                }
                                break;
                            case RIGHT:
                                if (getX() >= (wall.getX() - wall.getSize() + 2)) {
                                    setStatus(IDLE);
                                    return;
                                }
                                break;
                        }
                    }
                    mover(getDirection(), getSpeed());
                    break;
            }
        }
    }
    
    public boolean canTurn() {
        return true;
        //return (getSubX()+getSize()-6 <= map.squareSize && getSubY()+getSize()-6 <= map.squareSize);
    }

    public float getSubX() {
        return getX() % mapObject.squareSize;
    }

    public float getSubY() {
        return getY() % mapObject.squareSize;
    }

    public int getGridX() {
        return mapObject.xyToGrid(getX(), getY())[0];
    }

    public int getGridY() {
        return mapObject.xyToGrid(getX(), getY())[1];
    }

    public Key getBuffer() {
        if (this.buffer == null) return RIGHT;
        return buffer;
    }

    public void setBuffer(Key buffer) {
        if (getDirection() != buffer) {
            this.buffer = buffer;
            setStatus(BUFFERED);
        }
    }

    public Status getStatus() {
        if (status == null) setStatus(IDLE);
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getSpeed() {
        return speed/10;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Key getDirection() {
        return direction;
    }

    public void setDirection(Key direction) {
        this.direction = direction;
        setBuffer(null);
    }

}
