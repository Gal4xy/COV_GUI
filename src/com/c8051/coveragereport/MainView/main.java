/*
 * 20180917 17:23 Main Starter 
                  Offering navigation methods   
 */
package com.c8051.coveragereport.MainView;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Galaxy
 */
public class main extends Application {
    
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        
       stage=primaryStage;
       stage.setTitle("Coverage Report");
        gotomain();
       stage.show();
    }
  
  
    
     public void gotomain(){  
      try {  
            MainViewController main = (MainViewController) replaceSceneContent("MainView.fxml",800,600);  
            main.setApp(this);  
        } catch (Exception ex) {  
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }  
    
    
      /**
       * 20180917 14:09
       * 
       * 提供重载方法以适应不同的界面尺寸
       * 
       * @param fxml
       * @param width   界面宽度
       * @param height  界面长度
       * @return
       * @throws Exception 
       */
      
private Initializable replaceSceneContent(String fxml,int width,int height) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream inFxml = main.class.getResourceAsStream(fxml); 
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(main.class.getResource(fxml));  
        Parent page;  
        try {  
            page = (Parent) loader.load(inFxml);  
        } finally {  
            inFxml.close();  
        }   
        
        Scene scene = new Scene(page,width,height);  
        stage.setScene(scene);  
        stage.sizeToScene();  
        return (Initializable) loader.getController();  
    }   
     
/**
 *   @override
 * @param fxml
 * @return
 * @throws Exception 
 */

private Initializable replaceSceneContent(String fxml) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream inFxml = main.class.getResourceAsStream(fxml); 
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(main.class.getResource(fxml));  
        Parent page;  
        try {  
            page = (Parent) loader.load(inFxml);  
        } finally {  
            inFxml.close();  
        }   
        
        Scene scene = new Scene(page,800,600);  
        stage.setScene(scene);  
        stage.sizeToScene();  
        return (Initializable) loader.getController();  
    }   

     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
