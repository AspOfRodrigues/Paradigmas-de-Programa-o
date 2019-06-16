package randompickergui;


import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import java.util.List;

import java.util.ArrayList;
/**
 * @author AspOfRodrigues
 */
public class RandomPickerGUI extends Application {
    public ArrayList<String> listname;
    TextArea texto;
    int i = 0;
    int decide = 1;
    public class DataEntry
    {
        private SimpleStringProperty name;
        private DataEntry(String nome, String transparencia, String explosionRes){
            this.name = new SimpleStringProperty(nome);
        }
        public SimpleStringProperty nameProperty() {
            return name;
        }
        public String getNome() {
            return name.get();
        }
        public void setNome(String f) {
            name.set(f);
        }
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
       //________label________  
        Label label = new Label("RandomPickerGUI");
        
        //________menu________
        final Menu menuFile = file_menu();
        final Menu menuHelp = help_menu();
        //________menu itens_________
        file_menu_itens(menuFile, primaryStage);
        help_menu_itens(menuHelp, primaryStage);
        
        
        
        //_______atribuindo menu bar___________
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuHelp);
        
        //__________texto_____________
        Text txt = new Text();
        txt.setText("Developed by AspOfRodrigues!");
        texto = new TextArea();
        
        
        //_________BUTTONS_____________
        
        
        //_______Next______________
        Button next = new Button("Next");
        buttonNext(next);

        
        //_______Shuffle__________
        Button shuffle = new Button("Shuffle");
        buttonShuffle(shuffle, next);
        
        //_______cena___________
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, menuBar , texto ,txt, next, shuffle); 
    
        primaryStage.setScene(new Scene(vbox,800,400));
        primaryStage.show();
    }
    
    //_____________Abre os arquivos___________
    
    private List<String> open_file(File file) {
      try {
    	 BufferedReader reader = NList.abrir(file.getAbsolutePath());
    	 return NList.leitor(reader);    	 
      } catch (Exception ex) {
    	  System.out.println("Error! Cannot open file!");
    	  System.exit(0);
      }
      return null;
    }
    
    //_____________cria o menu file___________
    
    private Menu file_menu() {
        Menu menu = new Menu("File");
        return menu;
    }
    
    //_____________cria o menu help___________
    
    private Menu help_menu()
    {
        Menu menu = new Menu("Help");
        return menu;
    }
    
    //____itens do menu file____________
    
    public void file_menu_itens(final Menu menuFile, Stage primaryStage)
    {
        //___item open(menu file)_______
        FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Open Names File");
        final MenuItem menuItemOpen = new MenuItem("Open");
	menuItemOpen.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
            	List<String> names = open_file(file);
                for(String nome:names) {
                	String txt_print = nome + "\n";
                	texto.appendText(txt_print);
                }
            }        
	    }
	});
        
        //___item exit(menu file)_________
        
        final MenuItem menuItemExit = new MenuItem("Exit");
        menuItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Thats all folks!");
                System.exit(0);
            }
        });
        menuFile.getItems().addAll(menuItemOpen,menuItemExit);
    }
    
    //__itens do menu Help__________
    
    public void help_menu_itens(final Menu menuHelp, Stage primaryStage)
    {
        
        
        final MenuItem menuItem = new MenuItem("About");
        menuItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent e)
            {
                Label secondLabel = new Label("RandomPickerGUI developed by Henrique Rodrigues(AspOfRodrigues)");
                StackPane aboutLayout = new StackPane();
                aboutLayout.getChildren().add(secondLabel);
                Scene aboutScene = new Scene(aboutLayout, 400, 300);
                Stage about = new Stage();
                about.setTitle("About");
                about.setScene(aboutScene);
                about.setX(400);
                about.setY(300);
                about.show();
            }
        });
        menuHelp.getItems().add(menuItem);
    }
    
    public void buttonNext(Button next)
    {
        next.setVisible(false);
        next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) 
            {
		texto.setText(listname.remove(0));
                if(listname.isEmpty()) {
                    next.setVisible(false);
                }
            }
        });
    }
    
    public void buttonShuffle( Button shuffle, Button next)
    {
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ArrayList<String>namelis = NList.toArray(texto.getText());
        	if(!texto.getText().isEmpty()) {
                    if(namelis.size()> 50)
                    {
                        decide = 0; //utiliza o metodo offline de embaralhamento
                    }else
                    {
                        decide = 1; //utiliza o metodo online de embaralhamento
                    }
                    //Nota: alterne o campo rand caso queira alternar entre 
                    //os modos de embaralhamento 
                    //rand = 1; //atribua 0 para testar o 
                    //rand offline e qualquer outro valor
                    // para testar o rand Online e descomente o rand acima;
	        	if(decide == 0)
                        {
                            namelis = NList.embaralhaOffline(namelis);
                        }else{
                            namelis = NList.embaralhaOnline(namelis);
                        }
	            
        	}
                listname = namelis;
                next.setVisible(true);
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}