package edu.depaul.cdm.se452.group2.recommendations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "History")
public class History {
  
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Id")
   private long id;

   @Column(name = "SearchHistory")
   private String SearchHistory;
 
   @Column(name = "Item")
   private String Item;
  
 
}
