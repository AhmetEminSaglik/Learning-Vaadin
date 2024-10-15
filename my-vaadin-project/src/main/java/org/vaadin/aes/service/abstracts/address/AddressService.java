package org.vaadin.aes.service.abstracts.address;

import org.vaadin.aes.model.concrete.Address;

public interface AddressService {
    Address save(Address address);

    Address findById(long id);
}
