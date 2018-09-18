/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c8051.coveragereport.MainView;

/**
 *
 * @author Galaxy
 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Galaxy Yang
 */
public class FileBean {
    
    private final SimpleBooleanProperty check;
    private final SimpleStringProperty date;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty index;
    private final SimpleStringProperty cov;

    
    public FileBean(Boolean check,int index,String date,String name,String cov){
        this.check=new SimpleBooleanProperty(check);
        this.date=new SimpleStringProperty(date);
        this.name=new SimpleStringProperty(name);
        this.index=new SimpleIntegerProperty(index);
        this.cov=new SimpleStringProperty(cov);
    }

    public Boolean getCheck() {
        return check.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getName() {
        return name.get();
    }

    public int getIndex() {
        return index.get();
    }

    public String getCov() {
        return cov.get();
    }
    
    public void setName(String s){
        this.name.set(s);
    }
    
    
    public SimpleBooleanProperty checkProperty() {

        return check;

    }
    
    public void setDate(String s){
        this.date.set(s);
    }
   
    
    public void setCheck(Boolean b){
        this.check.set(b);
    }
    
    public void setIndex(int i){
        this.index.set(i);
    }
    public void setCov(String d){
        this.cov.set(d);
    }
    
}
