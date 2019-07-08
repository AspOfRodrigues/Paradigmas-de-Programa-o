/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzergui;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import githubanalyzergui.CommitAnalyzerFunct;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;

/**
 *
 * @author user
 */
public class GithubAnalyzerGUI extends Application {
    
    ListView<String> lista_url;
    ArrayList<Repositorio> repositorios = null; 
    ThreadView threadview;
    CommitAnalyzerFunct commitAnalyzerFunct;
    @Override
    public void start(Stage stage) throws Exception {
        
        //________label________  
        
        Label label = new Label("GitHunAnalyzerGUI");
        
        //________menu________
        
        final Menu menuFile = file_menu();
        final Menu menuTools = tools_menu();
        final Menu menuHelp = help_menu();
        
        //________menu itens_________
        file_menu_itens(menuFile, stage);
        tools_menu_itens(menuTools, stage, lista_url);
        help_menu_itens(menuHelp, stage);
        
        
        
        //_______atribuindo menu bar___________
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuTools, menuHelp);
        
        ObservableList<String> repo_list = FXCollections.<String>observableArrayList("load an archive");
        
        lista_url = new ListView<>(repo_list);
        
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label,menuBar, lista_url);// name);

        stage.setScene(new Scene(vbox,800,600));
        stage.show();
    }
    
    synchronized public void data_loading() {
	  if(!repositorios.isEmpty()) {
		  for(Repositorio r: repositorios) {
			  r.start();
		  }
	  }
  } 
        
    //_____________cria o menu file___________
    
    private Menu file_menu() {
        Menu menu = new Menu("File");
        return menu;
    }
    
    //_____________cria o menu Tools__________
    
    private Menu tools_menu(){
        Menu menu = new Menu("Tools");
        return menu;
    }
    
    //_____________cria o menu help___________
    
    private Menu help_menu()
    {
        Menu menu = new Menu("Help");
        return menu;
    }
    
    //____itens do menu file____________
    
    public void file_menu_itens(final Menu menuFile, final Stage primaryStage)
    {
        //___item reload(menu file)_______
        final FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Open Source File");
        final MenuItem menuItemOpen = new MenuItem("Open");
	menuItemOpen.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                    try {
                        BufferedReader reader = null;
                        //System.out.println("file existe");
                        reader = Functions.abrir(file.getAbsolutePath());
                        List<String> urls = Functions.ler(reader);
                        ObservableList<String> url_observable_list = FXCollections.<String>observableArrayList(urls);
                        lista_url.setItems(url_observable_list);
                        lista_url.setOrientation(Orientation.VERTICAL);
                        lista_url.setPrefSize(100, 100); // Set the Size of the ListView
                        repositorios = new ArrayList<Repositorio>();
                        data_loading();
                        for(String url: urls)
                                repositorios.add(new Repositorio(url));
                        try {
                            commitAnalyzerFunct.setInitRepo(urls);
                        } catch (Throwable ex) {
                            Logger.getLogger(GithubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            Logger.getLogger(GithubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(GithubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Throwable ex) {
                    Logger.getLogger(GithubAnalyzerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                }        
	    }
	});
       
        
        //___item exit(menu file)__________
        
        final MenuItem menuItemExit = new MenuItem("Exit");
        menuItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Program closed!");
                System.exit(0);
            }
        });
        menuFile.getItems().addAll(menuItemOpen,menuItemExit);
    }
    
    //__itens do menu Tools_________
    public void tools_menu_itens(final Menu menuTools, Stage primaryStage, ListView<String> lista_url )
    {
        final MenuItem commitAnalyzer = new MenuItem("Commit Analyzer");
        commitAnalyzer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Stage stage = new Stage();
                stage.setTitle("CommitAnalyzer");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(primaryStage);
                ObservableList<String> analyze = null;
                analyze = FXCollections.<String>observableArrayList(Repositorio.get_analyzedData(repositorios));
                VBox vbox = new VBox();
                
                vbox.setPadding(new Insets(10, 10, 10, 10));
                vbox.setSpacing(10);
                ListView<String> info_list_FX = new ListView<>(analyze);
                vbox.getChildren().addAll(info_list_FX );
                Scene scene = new Scene(vbox,400,400);
                stage.setScene(scene);
		  		  
		stage.show();
                
                
                
            }
        });
        menuTools.getItems().add(commitAnalyzer);
    }
    
    //__itens do menu Help__________
    
    public void help_menu_itens(final Menu menuHelp, Stage primaryStage)
    {
        
        
        final MenuItem menuItem = new MenuItem("About");
        menuItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent e)
            {
                Label secondLabel = new Label("GitHubAnalyzerGUI developed by Henrique Rodrigues(AspOfRodrigues)");
                TextArea txt = new TextArea();
                txt.setText("GitHubAnalyzerGUI developed by Henrique Rodrigues(AspOfRodrigues)");
                StackPane aboutLayout = new StackPane();
                aboutLayout.getChildren().add(secondLabel);
                Scene aboutScene = new Scene(aboutLayout, 800, 200);
                Stage about = new Stage();
                about.setTitle("About");
                about.setScene(aboutScene);
                about.setX(100);
                about.setY(100);
                about.show();
            }
        });
        menuHelp.getItems().add(menuItem);
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args){
        launch(args);
    }
    
}