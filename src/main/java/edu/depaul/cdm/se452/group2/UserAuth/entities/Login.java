package edu.depaul.cdm.se452.group2.UserAuth.entities;
import java.util.Optional;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Login")
public class Login{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Active_user_Id")
    private long active_user_id;

    @OneToOne
    private Authentication u_name;

    @Column(name = "Is_login")
    private boolean isLoggedIn;


}
