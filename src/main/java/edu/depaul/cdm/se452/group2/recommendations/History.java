package edu.depaul.cdm.se452.group2.recommendations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
 
@Data
@Entity
@Table(name = "History")
public class History {
  
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Search")
   private long Search;
 
   @Column(name = "ItemList")
   private String ItemList;
  
 
}
