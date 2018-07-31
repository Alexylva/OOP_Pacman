package oopacman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static oopacman.Draw.setMode;

/**
 *
 * @author Alexylva
 */
public class OOPacman extends Application { // Stage -> Scene -> Nodes

    static GraphicsContext graphics;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int NUM_MAPS = 5;

    //Entidades que possuem metodos render() e update();
    static ArrayList<GameObject> uiObjectList = new ArrayList<>(); //Desenhados no geral
    static ArrayList<GameObject> entityObjectList = new ArrayList<>(); //Desenhados em GameArea
    static ArrayList<GameObject> mapObjectList = new ArrayList<>(); //Mapas

    //Lista de arquivos de mapa
    static ArrayList<String> mapsFileList = new ArrayList<>();

    static GameArea gameAreaObject = new GameArea(WIDTH * 0.533, HEIGHT * 0.947, WIDTH * 0.1, HEIGHT * 0.03); //Area de jogo (sorry for the magic numbers) 3:4 aspect, x:10%, y:3%
    static Map mapObject;
    static UserInterface uiObject = new UserInterface(WIDTH * 0.65, HEIGHT * 0.03);
    static Font font;

    static ArrayList<String> inputArray = new ArrayList<>(); //Array que conterá as teclas pressionadas; //Lista de teclas pressionadas

    @Override
    public void start(Stage stage) {
        /* Esse bloco é executado apenas uma vez */

        stage.setTitle("OOP Pacman"); //Titulo

        Group root = new Group(); //Grupo raiz, onde são adicionados todos os elementos

        Canvas canvas = new Canvas(WIDTH, HEIGHT); //Nossa tela
        root.getChildren().add(canvas); //Adiciona a tela ao grupo

        graphics = canvas.getGraphicsContext2D(); //Obtem a instancia do GraphicsContext2D

        Scene pacmanScene = new Scene(root); //Cena principal

        stage.setScene(pacmanScene); //Determina a cena exibida no stage

        for (int i = 1; i <= NUM_MAPS; i++) {
            mapsFileList.add(String.format("maps/map%d.txt", i)); //Lista de mapas
        }

        try {
            font = Font.loadFont(new FileInputStream(new File("./fonts/VT323-Regular.ttf")), 48);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        //Comandos que determinam qual classes serão chamadas no evento de pressionamento e soltura de teclas 
        pacmanScene.setOnKeyPressed(new keyPressed());
        pacmanScene.setOnKeyReleased(new keyReleased());

        setupObjects(); //Adiciona os objetos a Array gameObjects;

        new Draw().start(); //Loop da logica do jogo, essa função é chamada a cada frame

        stage.show(); //Exibe a GUI do jogo
    }
    
    public static void setupGame(int level) {
        OOPacman.setupObjects();
        OOPacman.setupMap(level);
        setMode(Draw.Mode.GAME);
    }

    public static void setupMap(int selection) {
        OOPacman.mapObject = null;
        OOPacman.mapObject = new Map(mapsFileList.get(selection));
        mapObjectList.clear();
        mapObjectList.add(mapObject);
    }

    public static void setupObjects() {
        entityObjectList.clear();
        uiObjectList.clear();
        mapObjectList.clear();

        uiObjectList.add(gameAreaObject);
        uiObjectList.add(uiObject);
    }

    public static void main(String[] args) {
        launch(args); //Inicia a aplicação
    }

    private static class keyPressed implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            String code = e.getCode().toString();

            if (!inputArray.contains(code)) { //Evita duplicatas
                inputArray.add(code);
            }
        }
    }

    private static class keyReleased implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            String code = e.getCode().toString();
            inputArray.remove(code);
        }
    }

}
