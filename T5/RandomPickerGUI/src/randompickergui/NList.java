/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompickergui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.StringReader;

/**
 *
 * @author user
 */
public class NList {
    
    public static FileReader read;
    public static BufferedReader readFile;
    
    
    public static BufferedReader abrir(String path) throws IOException {
        read = new FileReader(path);
        readFile = new BufferedReader(read);
        return readFile;
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
    
    public static ArrayList<String> embaralhaOffline(ArrayList<String> lista)
    {
        Collections.shuffle(lista);
        return lista;
    }
    
    public static void imprime(ArrayList<String> lista)
    {
        System.out.println("Lista alterada:" + lista );
    }
    
    public static void imprimeOffiline(ArrayList<String> names)
    {
        Scanner prox = new Scanner(System.in);
        int cnt = 1;
        int i = 0;
        while (cnt == 1 & i < names.size())
        {        
            System.out.println("Embaralhado:" + names.get(i));
            i++;
            System.out.println("Digite 1 para continuar ou 0 para sair");
            cnt = prox.nextInt();
        }
        
        System.out.println("Execucao encerrada");
    }
    public static String criaStr(ArrayList<String> names)
    {
        int i = 0;
        String data = "list=";
        data = data.concat(names.get(i));
        i = 1;
        while (i < names.size())
        {        
            data = data.concat("%0D%0A");
            data = data.concat(names.get(i));
            i++;
        }
        data = data.concat("&format=plain&rnd=new");
        System.out.println("String Final:" + data);
        return data;
    }
    public static ArrayList<String> embaralhaOnline(ArrayList<String> names)
    {
        ArrayList<String> array = new ArrayList<>();
        try {
            
            String urlstr = "https://www.random.org/lists/?mode=advanced";
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setDoOutput(true);

            String data = NList.criaStr(names);
            // Envia dados pela conexão aberta
            con.getOutputStream().write(data.getBytes("UTF-8"));
            System.out.println("Response code: " + con.getResponseCode());

            // Cria objeto que fará leitura da resposta pela conexão aberta
            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));

            // Lê resposta, linha por linha
            String responseLine;
            StringBuffer response = new StringBuffer();
            while ((responseLine = in.readLine()) != null) {
                array.add(responseLine);
            }
                
            in.close();
            
            } catch (IOException e) {
                e.printStackTrace();
            }
        return array;
    }
    
    
}
