/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.config.auth_event;

import bni.webservices.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import bni.webservices.models.User;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Component
public class SuccessEvent implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserRepositories repository;
    
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        User user = repository.findByUsername(e.getAuthentication().getName()).get();
        
        user.setIsVerified(true);
        user.setStatus(0);
        repository.save(user);
    }
}