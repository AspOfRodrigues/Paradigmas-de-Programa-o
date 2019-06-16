package randompickercmd;
import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author AspOfRodrigues
 */
public class RandomPickerCmd {
    public static FileReader read;
    public static BufferedReader readFile;
    private static Scanner input;

    public static void main(String[] args) { 
        String path = args[0];
        System.out.println("Arquivo de entrada:" + path);
        ArrayList<String> names = new ArrayList();
        Random gerador = new Random();
        try
        {
            readFile = NList.abrir(path);
            names = NList.leitor(readFile);
        }catch(IOException e)
        {
            System.out.println("FILE Error");
            System.exit(0);
        }
        int rand;

        
        if(names.size()> 50)
        {
            rand = 0; // utiliza o metodo offline de embaralhamento
        }else
        {
            rand = 1; // utiliza o metodo online de embaralhamento
        }
        //Nota: alterne o campo rand caso queira alternar entre os modos de
        //embaralhamento 
        //rand = 1;//atribua 0 para testar o rand offline e qualquer outro valor
        // para testar o rand Online e descomente o rand acima;
        if( rand == 0)
        {
            //__ metodo de embaralhar offline__
            names = NList.embaralhaOffline(names);
            NList.imprimeOffline(names);
        }
        else
        {
            //__metodo de embaralhar online___
            names = NList.embaralhaOnline(names);
            NList.imprimeOffline(names);
        } 
  }
   
}
