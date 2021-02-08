/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.config.auth_event;

import bni.webservices.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import bni.webservices.models.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Component
public class BadCredentialEvent implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UserRepositories repository;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        updateUserVerifiedStatus(e.getAuthentication().getName());
    }

    private void updateUserVerifiedStatus(String username) {
        User user = repository.findByUsername(username).get();
        int userStatus = user.getStatus();
        if (userStatus != 3) {
            user.setStatus(user.getStatus() + 1);
        }else{
            user.setIsVerified(false);
        }
        repository.save(user);
    }
}
