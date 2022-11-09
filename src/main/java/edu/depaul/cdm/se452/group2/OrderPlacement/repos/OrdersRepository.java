package edu.depaul.cdm.se452.group2.OrderPlacement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.depaul.cdm.se452.group2.OrderPlacement.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
