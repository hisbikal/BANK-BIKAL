/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bni.webservices.models.Nasabah;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Repository
public interface NasabahRepositories extends JpaRepository<Nasabah, Integer>{
    
    @Query(value="select * from nasabah n where n.nomor_ktp = :noKtp",nativeQuery = true)
    Nasabah findByKtp(@Param("noKtp") String noKtp);
}
