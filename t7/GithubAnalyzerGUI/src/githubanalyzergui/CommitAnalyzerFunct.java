package githubanalyzergui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommitAnalyzerFunct {
    private ObservableList<Repositorio> repositorios;
    private Repositorio maisCommitado;
    private Repositorio menosCommitado;
    private Repositorio maisAtualizado;
    private Repositorio menosAtualizado;
    
    
    public CommitAnalyzerFunct(){
        List<Repositorio> repos = new ArrayList<>();
        this.repositorios =  FXCollections.observableArrayList(repos);
    }

    /**
     * @return the repositorios
     */
    public ObservableList<Repositorio> getRepositorios() {
        return repositorios;
    }

    /**
     * @param repositorios the repositorios to set
     */
    public void setRepositorios(ObservableList<Repositorio> repositorios) {
        this.repositorios = repositorios;
    }

    /**
     * @return the maisCommitado
     */
    public String getMaisCommitado() {
        String message = "Maior numero de commits:";
        return message + this.maisCommitado.getRepositorioUrl();
    }

    /**
     * @param maisCommitado the maisCommitado to set
     */
    public void setMaisCommitado(Repositorio maisCommitado) {
        this.maisCommitado = maisCommitado;
    }

    /**
     * @return the menosCommitado
     */
    public String getMenosCommitado() {
        String message = "Menor numero de commits:";
        return message + this.menosCommitado.getRepositorioUrl();
    }

    /**
     * @param menosCommitado 
     */
    public void setMenosCommitado(Repositorio menosCommitado) {
        this.menosCommitado = menosCommitado;
    }

    /**
     * @return the maisAtualizado
     */
    public String getMaisAtualizado() {
        String message = "Mais atualizado:";
        return message + this.maisAtualizado.getRepositorioUrl();
    }

    /**
     * @param maisAtualizado 
     */
    public void setMaisAtualizado(Repositorio maisAtualizado) {
        this.maisAtualizado = maisAtualizado;
    }

    /**
     * @return the menosAtualizado
     */
    public String getMenosAtualizado() {
        String message = "Menos atualizado:";
        return message + this.menosAtualizado.getRepositorioUrl();
    }

    public void setMenosAtualizado(Repositorio menosAtualizado) {
        this.menosAtualizado = menosAtualizado;
    }
    
    public void imprime_repo()
    {
        for(Repositorio repo : repositorios)
        {
            System.out.println(repo.toString());
        }
    }
    
    public void setInitRepo(List<String> repositorioUrl) throws Throwable{
        for (String repUrl : repositorioUrl) {
            repositorios.add(new Repositorio(repUrl));
        }
    }
    
    public void getRepoCommits() throws Exception {
        this.maisAtualizado = this.menosAtualizado = this.maisCommitado = this.menosCommitado = this.repositorios.get(0);
        for (Repositorio repositorio : this.repositorios) {
            
            getAllCommits(repositorio.getRepositorioUrl(), repositorio);
            if (repositorio.getRepositorio_num_commits() > this.maisCommitado.getRepositorio_num_commits()){
                this.maisCommitado= repositorio;
            }
            if (repositorio.getRepositorio_num_commits() < this.menosCommitado.getRepositorio_num_commits()){
                this.menosCommitado = repositorio;
            }
            if (repositorio.getLastDate().after(this.maisAtualizado.getLastDate())){
                this.maisAtualizado = repositorio;
            }
                
            if (repositorio.getFirstDate().before(this.menosAtualizado.getFirstDate())){
                this.menosAtualizado = repositorio;
            }
                
        }
    }
    
    private Repositorio getAllCommits(String repoURL, Repositorio repositorio) throws MalformedURLException, IOException{
        URL url = null;
        url = new URL(repoURL);
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
            JsonObject Jcommit = e.getAsJsonObject().getAsJsonObject("commit");
            JsonObject Jauthor = Jcommit.getAsJsonObject("author");
            String mensagem = object.get("message").toString();
            String autor = object.get("author").toString();
            
            String data = object.get("date").toString();
            Autor author = new Autor(Jauthor.get("name").getAsString(), Jauthor.get("email").getAsString(), Jauthor.get("date").getAsString());
            //Commit commit = new Commit(autor, mensagem, data);
            //repositorio.addCommit(commit);
      }
        repositorio.setTam_medio_commit();
        get.close();
        String nxt;
        try {
            nxt = connect.getHeaderFields().get("Link").get(0);
        } catch (Exception e) {
            return repositorio;
        }
        if (!nxt.contains("rel=\"last\"")) return repositorio;
        String next = nxt.split(";")[0].replace("<", "").replace(">", "");
        return getAllCommits( next, repositorio );
    }
    
    
    
}