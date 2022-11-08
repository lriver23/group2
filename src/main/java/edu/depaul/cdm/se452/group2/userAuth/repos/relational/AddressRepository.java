package edu.depaul.cdm.se452.group2.userAuth.repos.relational;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.depaul.cdm.se452.group2.userAuth.entities.relational.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByLocation(String location);
}