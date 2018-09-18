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
public class BeanFactory {
    
     public static FileBean getFileBeanInstance(String[] args){
        int index=Integer.parseInt(args[0]);
        return new FileBean(false,index,args[1],args[2],args[3]);
    }
}
