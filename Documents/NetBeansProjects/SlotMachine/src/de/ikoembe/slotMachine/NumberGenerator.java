/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ikoembe.slotMachine;

/**
 *
 * @author ikoembe
 */


public interface NumberGenerator{

      
    static boolean status =false;
    
public static int NumberGenarator (boolean x ){
     if (x=true){
     int randomNumber = (int)(Math.random()*6)+1;
     return randomNumber;
        }
    return 0;
}


 }

 
