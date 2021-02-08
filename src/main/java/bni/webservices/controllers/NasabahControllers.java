/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.controllers;

import bni.webservices.models.Nasabah;
import bni.webservices.response.ResponseRest;
import bni.webservices.services.NasabahService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hisbikal-Haqqi
 */
@RestController
@RequestMapping("api/nasabah")
public class NasabahControllers {
    
    @Autowired
    private NasabahService service;
    
    
    @GetMapping("/")
    public List<Nasabah> listCountries(){
        return service.read();
    }
    
    @GetMapping("ktp/{id}")
    public ResponseRest getByKtp(@PathVariable("id")String noKtp){
        if(service.findByKtp(noKtp) == null){
             return new ResponseRest(false,null,"Not found");
        }
        else{
            return new ResponseRest(true,service.findByKtp(noKtp),"founded");
        }
    }
    
    @PostMapping("")
    public ResponseRest addNasabah(@RequestBody Nasabah nasabah){
        if(service.findByKtp(nasabah.getNomorKtp()) != null){
             return new ResponseRest(false,null,"Sorry user has already registered");
        }
        else{
            if(validationKtp(nasabah.getNomorKtp()) == true){
                if(service.save(nasabah) != true){
                    return new ResponseRest(false,null,"Error");
                }
                Optional<Nasabah> nsbh = service.findById(nasabah.getId());

                return new ResponseRest(true,nsbh,"Add Successfull");
            }
            else{
                return new ResponseRest(true,null,"Please input valid no ktp");
            }
        }
    }
    
    @PutMapping("")
    public ResponseRest updateNasabah(@RequestBody Nasabah nasabah){
        if(validationKtp(nasabah.getNomorKtp()) == true){
            if(service.save(nasabah) != true){
                return new ResponseRest(false,null,"Error");
            }
            Optional<Nasabah> nsbh = service.findById(nasabah.getId());

            return new ResponseRest(true,nsbh,"Update Successfull");
        }
        else{
            return new ResponseRest(true,null,"Please input valid no ktp");
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseRest deleteNasabah(@PathVariable("id") Integer id){
        
       if(service.delete(id) != true){  
           
           return new ResponseRest(false,null,"Error");
       }
       return new ResponseRest(true,null,"Delete Successfull");
    }
    
    public boolean validationKtp(String noKtp){
        if(noKtp.length() > 16){
             return false;
        }
        return true;
    }
}
