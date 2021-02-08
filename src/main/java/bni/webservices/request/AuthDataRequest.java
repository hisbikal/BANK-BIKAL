/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.request;

import lombok.Data;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Data
public class AuthDataRequest {
    
    private String username;
    private String password;

    public AuthDataRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}