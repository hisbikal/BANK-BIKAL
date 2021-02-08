/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.webservices.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Hisbikal-Haqqi
 */
@Data
@Entity
@Table(name = "nasabah")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nasabah.findAll", query = "SELECT n FROM Nasabah n")
    , @NamedQuery(name = "Nasabah.findById", query = "SELECT n FROM Nasabah n WHERE n.id = :id")
    , @NamedQuery(name = "Nasabah.findByNamaLengkap", query = "SELECT n FROM Nasabah n WHERE n.namaLengkap = :namaLengkap")
    , @NamedQuery(name = "Nasabah.findByAlamat", query = "SELECT n FROM Nasabah n WHERE n.alamat = :alamat")
    , @NamedQuery(name = "Nasabah.findByTanggalLahir", query = "SELECT n FROM Nasabah n WHERE n.tanggalLahir = :tanggalLahir")
    , @NamedQuery(name = "Nasabah.findByNomorKtp", query = "SELECT n FROM Nasabah n WHERE n.nomorKtp = :nomorKtp")
    , @NamedQuery(name = "Nasabah.findByNoHandphone", query = "SELECT n FROM Nasabah n WHERE n.noHandphone = :noHandphone")})
public class Nasabah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nama_lengkap")
    private String namaLengkap;
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalLahir;
    @Basic(optional = false)
    @Column(name = "nomor_ktp")
    private String nomorKtp;
    @Basic(optional = false)
    @Column(name = "no_handphone")
    private String noHandphone;

    public Nasabah() {
    }
}
