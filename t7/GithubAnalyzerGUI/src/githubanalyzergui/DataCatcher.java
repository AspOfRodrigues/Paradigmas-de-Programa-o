/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzergui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 *
 * @author user
 */
public class DataCatcher extends Thread {
    public ArrayList<Commit> getCommitPageCounter(String proprietary, String repository, int page_cont) throws IOException  {
        
        String cont_page_Tostring = new Integer(page_cont).toString();
        String parametro  = "https://api.github.com/repos/" + proprietary + "/" + repository + "/commits?page=" + cont_page_Tostring;
        ArrayList<Commit> commit_list = new ArrayList<>();
        
        URL url = new URL(parametro);
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("User-Agent", "Mozilla/5.0");
        System.out.println("Response code:" + connect.getResponseCode());
        
        InputStreamReader input = new InputStreamReader(connect.getInputStream());
        
        BufferedReader get;   
        get = new BufferedReader(input);
        
        System.out.println(connect.getHeaderFields().get("Link").get(0));

        JsonParser parser = new JsonParser();
        JsonArray results = parser.parse(get.readLine()).getAsJsonArray();
        System.out.println("Size: "+ results.size());
        
        for (JsonElement e : results) {
            System.out.println(e.getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date"));
            JsonObject object = e.getAsJsonObject();
            String mensagem = object.get("message").toString();
            String autor = object.get("author").toString();
            String data = object.get("date").toString();
            Commit commit = new Commit(repository, autor, mensagem, data);
            commit_list.add(commit);
      }  
      return commit_list; 
    }
}
