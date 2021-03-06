package githubanalyzergui;

public class Commit {
    private String autor;
    private Autor author;
    private String mensagem;
    private String data;
    private String repositorio;
    
    public Commit( String repositorio, String autor, String mensagem, String data)
    {
        this.repositorio = repositorio;
        this.author = author;
        this.autor = author.getNome();;
        this.mensagem = mensagem;
        this.data = data;
    }
    
    public String getRepositorio()
    {
        return repositorio;
    }
    /**
     * @return the autor
     */
    public String getAutor() {
        return autor + "\t";
    }
    
    public Autor getAuthor(){
        return author;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data + "\t";
    }
}


