package oopacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static oopacman.Actor.Status.IDLE;

import static oopacman.Key.*;

public class Ghost extends Actor {

    private Color cor;

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return this.cor;
    }

    public Ghost(int x, int y, Color cor) {
        super(x, y, "Ghost");
        setSize((int) Math.floor(OOPacman.ga.getWidth() * 0.05));
        setSpeed(20);
        setCor(cor);
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        gc.setFill(getCor());//cor do corpo do fantasma
        gc.fillRect(getX(), getY() + getSize() / 2, getSize(), getSize() / 2);//parte quadrada do fantasma
        gc.fillOval(getX(), getY(), getSize(), getSize() * 0.95);//parte redonda

        gc.setFill(Color.BLACK);//cor dos olhos
        gc.fillOval(getX() + 0.2 * getSize(), getY() + getSize() / 4, getSize() * 0.16, getSize() * 0.16);//olho esquerdo
        gc.fillOval(getX() + 0.62 * getSize(), getY() + getSize() / 4, getSize() * 0.16, getSize() * 0.16);//olho direito

    }

    @Override
    public void update(GraphicsContext gc, double time) {
        if (getStatus() == IDLE || Draw.frameCount%100   == 0) {
            System.out.println(time);
            direcionar(Key.getRandomDir(), getSpeed());
        }
        mover();

    }

}
