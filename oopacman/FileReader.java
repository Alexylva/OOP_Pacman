package oopacman;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    private int linhas;
    private int colunas;


    public FileReader(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public int[][] readFile(String url) throws IOException {
        String filePath = new File(url).getAbsolutePath();
        Path path = Paths.get(filePath);

        int[][] matrizFinal = new int[linhas][colunas];

        List<String> lines = Files.readAllLines(path);
        String[] matrizLinha = lines.toArray(new String[0]); //new String[0] serve pra ele retornar a matriz no tipo certo
        matrizLinha[0] = matrizLinha[0].substring(1); //Fixes missing block
        
        for (int i = 0; i < this.linhas; i++) {
            char[] matrizChar = matrizLinha[i].toCharArray();

            for (int j = 0; j < this.colunas; j++) {
                matrizFinal[i][j] = Character.getNumericValue(matrizChar[j]);
            }
        }

        return matrizFinal;
    }

    /*
    public static void main( String[] args ){
        try {
            int a[][];
            FileReader leitor = new FileReader(20, 15);
            a = leitor.readFile("oopacman/MapaTeste.txt");
            for (int i = 0; i < a.length ; i++){
                    for (int j = 0; j < a[i].length; j++){
                            System.out.print(a[i][j]);
                    }
                    System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    */

}
