package org.vaadin.aes.service.concretes.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.Address;
import org.vaadin.aes.repository.abstracts.AddressRepository;
import org.vaadin.aes.service.abstracts.address.AddressService;

import java.util.logging.Logger;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private static final Logger log = Logger.getLogger(AddressServiceImpl.class.getName());
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        log.info("Save Address Data Function First Line: "+address);
        Address tmpAddress = addressRepository.findByStreetAndCity(address.getStreet(), address.getCity());
        log.info("Save Address--> tmpAddress : "+tmpAddress);
        if (tmpAddress != null) {
            return tmpAddress;
        }
        return addressRepository.save(address);
    }

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
