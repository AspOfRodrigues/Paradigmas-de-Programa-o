/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzercmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import githubanalyzercmd.Functions;
import java.io.IOException;

/**
 *
 * @author user
 */
public class GitHubAnalyzerCmd extends Thread {
    public static FileReader read;
    public static BufferedReader reader;
    public static Scanner scannner;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Throwable {
        if(args.length > 0)
        {
            System.out.println("loading:" + args[0]);
        }else
        {
            System.out.println("No file loaded");
            System.exit(0);
        }
        List<String> urls = new ArrayList<>();
        try{
            reader = Functions.abrir(args[0]);
            urls = Functions.ler(reader);
        } catch (IOException e)
        {
            System.out.println("File Error");
            System.exit(0);
        }
        
        final List<String> lista = urls;
        
        
            urls = lista;
            Repositorio repositorio = new Repositorio (lista.get(0));
            repositorio.start();
            int carregamento = 0;
            ArrayList<Commit> commits = repositorio.getRepositorioCommit();
            for(Commit cmt: commits)
            {
                System.out.println("Repository info:" + cmt.getAutor() + "/" + cmt.getData()+ "/" +cmt.getMensagem() );
                System.out.println("Medium commit size:" + repositorio.getTam_medio_commit());
                System.out.println("Commit counter:" + repositorio.getRepositorio_num_commits());
            }
            System.exit(0);
        
        
    }
    
}
