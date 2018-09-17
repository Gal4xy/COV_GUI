/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c8051.coveragereport.MainView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Galaxy
 */
public class MainViewController implements Initializable {

    private main application;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
      @FXML 
      /**
       * Button _ Start
       */
    protected void handleStartButtonAction(ActionEvent event) {
      if(application!=null){
           application.gotomain();
       }
    }
    
    
     @FXML 
     /**
      * Button _Return
      */
    protected void handleReturnButtonAction(ActionEvent event) {
       if(application!=null){
           application.gotomain();
       }
    }
    
      public void setApp(main application){
        this.application=application;
    }
}
