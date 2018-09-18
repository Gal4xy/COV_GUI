/*
 * 1:read Log.txt
 */
package com.c8051.coveragereport.MainView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Galaxy
 */
public class MainViewController implements Initializable {

    private main application;
    
    @FXML 
    private TableView<FileBean> table;
    
    @FXML 
    private TableColumn check;
    
    @FXML 
    private TableColumn index;
     
    @FXML 
    private TableColumn date;
  
    
    @FXML 
    private TableColumn name;
 
    @FXML 
    private TableColumn cov;
    
    
    private final ObservableList<FileBean> data;
    private Set<FileBean> SelectedBeans;
    private String filePath="";
    
    
     public MainViewController() throws IOException{
      data=FXCollections.observableArrayList();
      SelectedBeans=new HashSet<>(); 
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
           // TODO
           String PrjName=this.genPrjName(Constants.PathDoc);
           filePath=this.getFilePath("LOG",PrjName);
         
           this.getContext(filePath);
           this.setColumns();
           table.setItems(data);
           
           /**
            * 20180917 10：27
            * 加入判断columns的部分 如果不空就添加空白
            */
           
           if(table.getColumns().isEmpty()){
               table.getColumns().addAll(check,index,date,name,cov);
           }
           else{
               table.getColumns().addAll( );
           }
           
            
        table.setOnMouseClicked(new EventHandler<Event>(){
            public void handle(Event e){
                TableViewSelectionModel<FileBean> selectionModel=table.selectionModelProperty().get();
                FileBean fileBean=selectionModel.getSelectedItem();
                    if(fileBean==null){
                       
                    }
                    else{
                        fileBean.setCheck(Boolean.TRUE);
                        SelectedBeans.add(fileBean);
                    }
            }
            
        });
    
  
           
       }
         catch(IOException ex){
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
       
       
    }    
    
    
      @FXML 
      /**
       * Button _ Start
       */
    protected void handleStartButtonAction(ActionEvent event) {
        try {
            String PrjName=this.genPrjName(Constants.PathDoc);
            String infoPath=this.getFilePath("INFO", PrjName);
            
            Process(infoPath);
            
            if(application!=null){
                application.gotomain();
            }} catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     @FXML 
     /**
      * Button _Return
      */
    protected void handleReturnButtonAction(ActionEvent event) {
      /*
        if(application!=null){
           application.gotomain();
       }*/
      
      /**
       * 20180918 16:42 reset
       */
      //重置按钮 重置所有按钮为FALSE
      //         清空列表中的所有对象
         for (FileBean bean:table.getItems()) {
             bean.setCheck(Boolean.FALSE);
         }
        if(!SelectedBeans.isEmpty()){ 
             SelectedBeans.clear();
        }
       
    }
    
      public void setApp(main application){
        this.application=application;
    }
     
      
       public void setColumns(){
        check.setCellFactory(CheckBoxTableCell.forTableColumn(check));
         
         check.setCellValueFactory(
              new PropertyValueFactory("check"));
        
        index.setCellValueFactory(
        new PropertyValueFactory<>("index"));
        
        date.setCellValueFactory(
        new PropertyValueFactory<>("date"));
        
        name.setCellValueFactory(
        new PropertyValueFactory<>("name"));
        
        cov.setCellValueFactory(
        new PropertyValueFactory<>("cov"));
        
     }
     
      
      
     /**
      * 20180918 14:51 Access Project Name
      * 
     * @param filepath Path.txt -- Project Name
     * @return 
     * @throws java.io.IOException
      */ 
      
     public String genPrjName(String filepath)throws IOException{
          File file=new File(filepath);
          String PrjName;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            PrjName = " ";
            while((s=br.readLine())!=null){
                String[] args=s.split("#");
                if(args!=null){
                    if(args[0].equals("PRJNAME")){
                        PrjName=args[1];
                    }
                }
            }
        }
          return PrjName;
       
     }
     
     /**
      * 20180829 14:34 合成sumlist的地址
      * 20180918 14:56   合成Log.txt的地址
      * @param PrjName
      * @return 
      */
     
      public String getFilePath(String mode,String PrjName){
          String destDir="";
          
          if(mode.equals("LOG")){
            destDir=System.getProperty("user.home")+File.separator+"IDE_WORKSPACE"
                 +File.separator+PrjName+File.separator+"log.txt";  }
          
          if(mode.equals("INFO")){
               destDir=System.getProperty("user.home")+File.separator+"IDE_WORKSPACE"
                 +File.separator+PrjName+File.separator+"info.txt";
          }
          
         return destDir;
     }
      
      /**
     * 20180829 14:16  数据注入
     * 20180918 14：58 数据输入
     *  
     * @param filePath
     * @throws IOException 
     */
   
     public void getContext(String filePath) throws IOException{
         File f=new File(filePath);
         BufferedReader br=new BufferedReader(new FileReader(f));
           String s;
         while((s=br.readLine())!=null){
             if(s.contains("#")){
              String[] args=s.split("#");
                if(args!=null){
                data.add(BeanFactory.getFileBeanInstance(s.split("#")));}
                else{
                    System.out.println("CHECK_208_REGEX_NULL");
                }
             }
           }
            br.close();
     }
     
     
     
     
     
     public void Process(String infoPath){
      try {
            String PrjName=this.genPrjName(Constants.PathDoc);
            String args[]=new String[4];
            List<String> Info=new ArrayList<>();
            
            //args[1]/args[3]
            Info=FileUtils.readLines(new File(infoPath), "UTF-8");
            if(!Info.isEmpty()){
                for(String s:Info){
                    if(s.contains("#")){
                        String[] res=s.split("#");
                           if(res[0].equals("BIN")){
                               args[1]=res[1];
                             
                           }
                           if(res[0].equals("SRCLIST")){
                               args[3]=res[1];
                              
                           }
                    }
                }
            }
            
            //args[2]
                 args[2]=Constants.userhome+File.separator
                    +PrjName+File.separator+"cov_data";
           
            
            if(SelectedBeans.size()==1){
                    String mode="Single";
                     for(FileBean bean:SelectedBeans){
                         args[0]=bean.getCov();
                     }
                }
        
            
            if(SelectedBeans.size()>1){
                String mode="Multi";
            }
            if(SelectedBeans.size()==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                //alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("错误：并未选取任何结果");
                
                alert.showAndWait();
            }
            
            
            //CHECK
            for (int i = 0; i < args.length; i++) {
                System.out.println("Args "+i+""+args[i]);
          }
        }
        catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     
     public void Launch(String mode,String[] args){
         if(mode.equals("Single")){
             
         }
         if(mode.equals("Multi")){
             
         }
         
     }
     
     
     
}
