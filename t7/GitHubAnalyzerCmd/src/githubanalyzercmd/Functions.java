/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzercmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Functions {
     public static FileReader read;
    public static BufferedReader readFile;
    
    
    public static BufferedReader abrir(String path) throws IOException {
        read = new FileReader(path);
        readFile = new BufferedReader(read);
        return readFile;
    }
    
    public static List<String> ler(BufferedReader buffreader){
        try{
            return Functions.return_Names(buffreader);
        }catch( Exception e)
        {
            System.out.println("Erro, certifique-se de escolher o arquivo corretamente!!!");
            return null;
        }
    }
    
    public static List<String> return_Names(BufferedReader read) throws IOException{
        List<String> list = new ArrayList<>();
        String name = read.readLine();
        while(name != null){
            System.out.println("readed name:"+name);
            list.add(name);
            name = read.readLine();
        }
        return list;
    }
    
    public static ArrayList<String> leitor(BufferedReader buffread) throws IOException {
        System.out.println("Please wait, reading file");
        ArrayList<String> list = new ArrayList<>();
        String name = buffread.readLine();
        while(name != null)
        {
            list.add(name);
            System.out.println("Adicionando ao array:"+name);
            name = buffread.readLine();
        }
        return list;
    }
    
    public static ArrayList<String> toArray(String name){
		BufferedReader reader = new BufferedReader(new StringReader(name));
		ArrayList<String> array = new ArrayList<>();
		String elemento = null;
		try {
			while((elemento = reader.readLine()) != null) {
				array.add(elemento);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
}
