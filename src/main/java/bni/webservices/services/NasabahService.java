/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.services;

import bni.webservices.models.Nasabah;
import bni.webservices.repositories.NasabahRepositories;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Service
public class NasabahService {
    
    @Autowired
    NasabahRepositories repo;
    
    public List<Nasabah> read(){
        return repo.findAll();
    }
    
    public boolean save(Nasabah nasabah){
        Optional<Nasabah> dataNasabah = repo.findById(nasabah.getId());
        
        try{
            if(dataNasabah.isPresent()){
                repo.save(nasabah);
            }
            else{
                repo.save(nasabah);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return true;
    }
    
    public boolean delete(Integer nasabahId){
       try{
            repo.deleteById(nasabahId);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    
    public Nasabah findByKtp(String noKtp){
        return repo.findByKtp(noKtp);
    }
    
    public Optional<Nasabah> findById(Integer nasabahId){
        return repo.findById(nasabahId);
    }
}
