package org.vaadin.aes.repository.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.aes.model.concrete.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndCity(String street, String city);

}
