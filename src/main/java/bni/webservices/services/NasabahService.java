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
    
    public Nasabah save(Nasabah nasabah){
        Optional<Nasabah> dataNasabah = repo.findById(nasabah.getId());
        
        try{
            if(dataNasabah.isPresent()){
                repo.updateData(nasabah.getNamaLengkap(),nasabah.getAlamat(),nasabah.getTanggalLahir(),nasabah.getNoHandphone(),nasabah.getId());
            }
            else{
                repo.insertData(nasabah.getNamaLengkap(),nasabah.getAlamat(),nasabah.getTanggalLahir(),nasabah.getNoHandphone(),nasabah.getNomorKtp());
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return nasabah;
    }
    
    public void delete(Integer nasabahId){
        repo.deleteById(nasabahId);
    }
    
    public Nasabah findByKtp(String noKtp){
        return repo.findByKtp(noKtp);
    }
    
    public Optional<Nasabah> findById(Integer nasabahId){
        return repo.findById(nasabahId);
    }
    
    public Optional<Nasabah> lastData(){
        return repo.lastData();
    }
}
