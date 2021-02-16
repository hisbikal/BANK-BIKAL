/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bni.webservices.models.Nasabah;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Repository
public interface NasabahRepositories extends JpaRepository<Nasabah, Integer>{
    
    @Query(value="select * from nasabah n where n.nomor_ktp = :noKtp",nativeQuery = true)
    Nasabah findByKtp(@Param("noKtp") String noKtp);
    
    @Query(value="select * from nasabah order by id desc LIMIT 1",nativeQuery = true)
    Optional<Nasabah> lastData();
    
    @Modifying
    @Transactional
    @Query(value="update nasabah set nama_lengkap = :nmLengkap, alamat = :alamat, "
            + " tanggal_lahir = :tglLahir, no_handphone = :noHp where id = :id",nativeQuery = true)
    void updateData(@Param("nmLengkap") String nmLengkap, @Param("alamat") String alamat,
            @Param("tglLahir") Date tanggal_lahir, @Param("noHp") String noHp, 
            @Param("id") Integer id);
    
    @Modifying
    @Transactional
    @Query(value="insert into nasabah (nama_lengkap,alamat,tanggal_lahir,nomor_ktp,no_handphone) "
            + " values (:nmLengkap,:alamat,:tglLahir,:nmrKtp,:noHp)",nativeQuery = true)
    void insertData(@Param("nmLengkap") String nmLengkap, @Param("alamat") String alamat,
            @Param("tglLahir") Date tanggal_lahir, @Param("noHp") String noHp, 
            @Param("nmrKtp") String nmrKtp);
}
