/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package githubanalyzergui;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class ThreadView extends Thread {
    ListView lista_commits;
    public ArrayList<String> get_commit(Repositorio repositorio){
        ArrayList<String> commit = new ArrayList<String>();
        for(Commit c: repositorio.getRepositorioCommit()){
            commit.add(c.getAutor()+c.getData()+c.getMensagem());
        }
        return commit;
    }
//    
//    public void show_newStage(Stage stage, String url_repositorio) throws Throwable
//    {
//        Stage newStage = new Stage();
//        newStage.setTitle("Repositorio");
//        newStage.initModality(Modality.WINDOW_MODAL);
//        newStage.initOwner(stage);
//          
//        Repositorio repositorio = new Repositorio(url_repositorio);
//        repositorio.run();
//        currentThread().wait();
//        ObservableList<String> commit_array;
//        commit_array = FXCollections.<String>observableArrayList(get_commit(repositorio));
//        lista_commits = new ListView<>(commit_array);
//        
//        VBox vbox = new VBox();
//        
//	vbox.setPadding(new Insets(10, 10, 10, 10));
//        vbox.setSpacing(10);
//        
//        vbox.getChildren().addAll(lista_commits); 
//        Scene scene = new Scene(vbox,800,400); 
//        stage.setScene(scene);
//        stage.show();
//    }
}