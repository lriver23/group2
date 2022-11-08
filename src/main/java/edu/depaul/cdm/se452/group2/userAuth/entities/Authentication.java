package edu.depaul.cdm.se452.group2.userAuth.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Authentication{
    
    public static final String getU_name = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "User_Name")
    private String u_name;
    
    @Column(name = "Password")
    private String pwd;

   
    @Email(regexp = ".+[@].+[\\.].+", message = "Email must be in the format abc@xyz.pqr")
    private String email;

    @OneToOne
    @JoinColumn(name = "Address_Id")
    private Address address;


    /**
     * @return String return the u_name
     */
    public String getU_name() {
        return u_name;
    }

    /**
     * @param u_name the u_name to set
     */
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    /**
     * @return String return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return Address return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

}
