/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.controllers;

import bni.webservices.models.Nasabah;
import bni.webservices.response.ResponseApi;
import bni.webservices.services.NasabahService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class NasabahControllers {
    
    @Autowired
    private NasabahService service;
    
    @GetMapping("")
    public ResponseApi<List<Nasabah>> listCountries(){
        return ResponseApi.apiOk(service.read(), "success");
    }
    
    @GetMapping("ktp/{id}")
    public ResponseApi<Nasabah> getByKtp(@PathVariable("id")String noKtp){
        if(service.findByKtp(noKtp) == null){
             return ResponseApi.apiFailed("Not found",HttpStatus.BAD_REQUEST);
        }
        else{
            return ResponseApi.apiOk(service.findByKtp(noKtp), "success");
        }
    }
    
    @PostMapping("")
    public ResponseApi<Optional<Nasabah>> addNasabah(@RequestBody Nasabah nasabah){
        if(service.findByKtp(nasabah.getNomorKtp()) != null){
            return ResponseApi.apiFailed("Sorry user has already registered",HttpStatus.BAD_REQUEST);
        }
        else{
            if(validationKtp(nasabah.getNomorKtp()) == true){
                if(service.save(nasabah) != true){
                    return ResponseApi.apiFailed("Please fill out the form completely ",HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Optional<Nasabah> nsbh = service.findById(nasabah.getId());

                return ResponseApi.apiOk(nsbh, "success");
            }
            else{
                return ResponseApi.apiFailed("No nik maximal 16 character ",HttpStatus.BAD_REQUEST);
            }
        }
    }
    
    @PutMapping("")
    public ResponseApi<Optional<Nasabah>> updateNasabah(@RequestBody Nasabah nasabah){
        if(service.findByKtp(nasabah.getNomorKtp()) != null){
            return ResponseApi.apiFailed("Sorry user has already registered",HttpStatus.BAD_REQUEST);
        }
        else{
            if(validationKtp(nasabah.getNomorKtp()) == true){
                if(service.save(nasabah) != true){
                    return ResponseApi.apiFailed("Please fill out the form completely ",HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Optional<Nasabah> nsbh = service.findById(nasabah.getId());

                return ResponseApi.apiOk(nsbh, "success");
            }
            else{
                return ResponseApi.apiFailed("No nik maximal 16 character ",HttpStatus.BAD_REQUEST);
            }
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseApi deleteNasabah(@PathVariable("id") Integer id){
        
       if(service.delete(id) != true){  
           
           return ResponseApi.apiFailed("Please select id",HttpStatus.BAD_REQUEST);
       }
       return ResponseApi.apiOk("Delete success");
    }
    
    public boolean validationKtp(String noKtp){
        if(noKtp.length() > 16){
             return false;
        }
        return true;
    }
}
