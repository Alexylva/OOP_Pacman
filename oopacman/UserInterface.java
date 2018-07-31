package oopacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserInterface implements GameObject {

        private static int score = 0, highscore = 0;
        private final double x, y;
        private Font font = null;
        
        public UserInterface(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static int getScore() {
                return UserInterface.score;
        }
        
        public void setHighscore(int highscore) {
            this.highscore = highscore;
        }
        
        public int getHighscore() {
            return this.highscore;
        }
        
        public static void resetScore() {
                UserInterface.score = 0;
        }
        public static void addScore(int score) {
                UserInterface.score += score;
        }

        @Override
        public void render(GraphicsContext graphics){
            graphics.save();
            graphics.translate(this.x,this.y);
            graphics.setFill(new Color(1,1,1,1));
            graphics.setFont(font);
            graphics.fillText(String.format("HIGH-SCORE\n%08d\nSCORE\n%08d", getHighscore(), getScore()), 30, 30);
            graphics.restore();
            
        }

        public void update( GraphicsContext graphics){
            if(getHighscore() < getScore()) {
                setHighscore(getScore());
            }
        }
}
