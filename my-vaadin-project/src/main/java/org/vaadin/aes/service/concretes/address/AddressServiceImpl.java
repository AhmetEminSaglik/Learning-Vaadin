package org.vaadin.aes.service.concretes.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.aes.model.concrete.Address;
import org.vaadin.aes.repository.abstracts.AddressRepository;
import org.vaadin.aes.service.abstracts.address.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        Address tmpAddres = addressRepository.findByStreetAndCity(address.getStreet(), address.getCity());
        if (tmpAddres != null) {
            return tmpAddres;
        }
        return addressRepository.save(address);
    }

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
