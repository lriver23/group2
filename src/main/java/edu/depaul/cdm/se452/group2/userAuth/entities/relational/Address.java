package edu.depaul.cdm.se452.group2.userAuth.entities.relational;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // public Address(String location){
    //     this.location = location;
    // }
    private String location;
	public int count() {
		return (Integer) null;
	}
    public void save(Address smkAddress) {
    }        
}
