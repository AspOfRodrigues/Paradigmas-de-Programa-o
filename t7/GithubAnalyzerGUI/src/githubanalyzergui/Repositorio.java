/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzergui;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author user
 */

public class Repositorio extends Thread {
    public ArrayList<Commit> commits;
    public int num_commit;
    public String url;
    public String name;
    public String property;
    private int tam_total_commit;
    private float tam_medio_commit;
    
    public Repositorio(String url) throws Throwable{
        this.setRepositorioUrl(url);
        this.commits = null;
        this.num_commit = 0;
        this.tam_medio_commit = 0;
        this.tam_total_commit = 0;
        
        //obtem campos da url do repositorio
        try{
            String splited_url[] = this.url.split("/");
            if(splited_url.length > 2)
            {
                this.name = splited_url[splited_url.length];
                this.property = splited_url[splited_url.length - 1];
            }
            else
            {
                System.out.println("Error, verify this url");
                this.finalize();
            }
           
        }catch(Exception e)
        {
            System.out.println("Error, verify this url");
            this.finalize();
        }
    }
    
        
    
        public String getRepositorioUrl() {
		return this.url;
	}
	
	public int getRepositorio_num_commits() {
		return this.num_commit;
	}

	public void setRepositorioUrl(String url) {
		this.url = url;
	}

	public ArrayList<Commit> getRepositorioCommit() {
		return this.commits;
	}
        
        synchronized public void run() {
		if(this.isAlive()) {
			int i = 0;
			DataCatcher catcher = new DataCatcher();
			ArrayList<Commit> commits_pages = null;
			try {
				while((commits_pages = catcher.getCommitPageCounter(this.property, this.name, i) )!= null) {
					commits.addAll(commits_pages);
					i++;
				}
			} catch(IOException e){
				
			}
			this.num_commit = commits.size();
			currentThread().notifyAll();
		}
	}

    /**
     * @return the tam_total_commit
     */
    public int getTam_total_commit() {
        return tam_total_commit;
    }

    /**
     * @param tam_total_commit the tam_total_commit to set
     */
    public void setTam_total_commit(int tam_total_commit) {
        this.tam_total_commit = tam_total_commit;
    }

    /**
     * @return the tam_medio_commit
     */
    public float getTam_medio_commit() {
        return tam_medio_commit;
    }

    /**
     * @param tam_medio_commit the tam_medio_commit to set
     */
    public void setTam_medio_commit(float tam_medio_commit) {
        this.tam_medio_commit = tam_total_commit/this.num_commit;
    }
    
    public Date getFirstDate() {
        return Date.from(Instant.parse(commits.get(num_commit - 1).getAuthor().getData()));
    }

    public Date getLastDate() {
        return Date.from(Instant.parse(commits.get(0).getAuthor().getData()));
    }
    
     public void addCommit(Commit commit) {
        this.commits.add(commit);
        this.tam_total_commit += commit.getMensagem().length();
        this.num_commit++;
    }
    
    public static ArrayList<String> get_analyzedData(ArrayList<Repositorio> rep){
        ArrayList<String> data = new ArrayList<String>();
        Commit recente;
        Commit antigo;
        Repositorio menor;
        Repositorio maior;
        ArrayList<Commit> commits_recentes = new ArrayList<Commit>();
        ArrayList<Commit> commits_antigos = new ArrayList<Commit>();
        maior = rep.get(0);
        menor = rep.get(0);
        for(Repositorio repo: rep)
        {
            if(repo.getRepositorio_num_commits() > maior.getRepositorio_num_commits())
            {
                maior = repo;
            }
            if(repo.getRepositorio_num_commits() < menor.getRepositorio_num_commits())
            {
                menor = repo;
            }
            commits_recentes.add(repo.get_commitRecente());
            commits_antigos.add(repo.get_commitAntigo());
        }
        data.add("mais commits:" + maior.name);
        data.add("menos commits:" + menor.name);
        
        recente = commits_recentes.get(0);
        antigo = commits_antigos.get(commits_antigos.size()-1);
        
        data.add("Commits mais recentes:" + recente.getRepositorio());
        data.add("Commits mais antigos:" + antigo.getRepositorio());
        
        return data;
    }
    
    
    public Commit get_commitRecente() {
                return commits.get(commits.size()-1);
	  }

    public Commit get_commitAntigo() {
                return commits.get(0);
          }
}

