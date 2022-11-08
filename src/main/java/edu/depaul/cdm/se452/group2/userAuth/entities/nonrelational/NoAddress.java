package edu.depaul.cdm.se452.group2.userAuth.entities.nonrelational;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Document
@Builder
public class NoAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String location;
    public List<NoAddress> findAll() {
        return null;
    }
    public int count() {
        return 0;
    }
    public void save(Address smkAddress) {
    }
        
}
