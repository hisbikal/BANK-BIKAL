/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.response;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 *
 * @author NetTech
 */
@Getter
public class ResponseRest<T>{
    
    private boolean success;
    private String message;
    private T data;

    public ResponseRest(){
        
    } 
    
    public ResponseRest(boolean success, T data, String message) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
