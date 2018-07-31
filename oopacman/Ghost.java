package oopacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static oopacman.Actor.Status.IDLE;

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
        setSize((int) Math.floor(OOPacman.gameAreaObject.getWidth() * 0.05));
        setSpeed(20);
        setCor(cor);
    }

    @Override
    public void render(GraphicsContext graphics) {
        graphics.setFill(getCor());//cor do corpo do fantasma
        graphics.fillRect(getX(), getY() + getSize() / 2, getSize(), getSize() / 2);//parte quadrada do fantasma
        graphics.fillOval(getX(), getY(), getSize(), getSize() * 0.95);//parte redonda

        graphics.setFill(Color.BLACK);//cor dos olhos
        graphics.fillOval(getX() + 0.2 * getSize(), getY() + getSize() / 4, getSize() * 0.16, getSize() * 0.16);//olho esquerdo
        graphics.fillOval(getX() + 0.62 * getSize(), getY() + getSize() / 4, getSize() * 0.16, getSize() * 0.16);//olho direito

    }

    @Override
    public void update(GraphicsContext graphics) {
        if (getStatus() == IDLE || Draw.frameCount%100   == 0) {
            direcionar(Key.getRandomDir(), getSpeed());
        }
        mover();

    }

}
