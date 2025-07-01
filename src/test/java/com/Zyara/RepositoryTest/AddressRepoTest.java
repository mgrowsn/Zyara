package com.Zyara.RepositoryTest;

import com.Zyara.Model.Address;
import com.Zyara.Repository.AddressRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AddressRepoTest {
    @Autowired
    AddressRepo addressRepo;

    @Test
    @DisplayName("Save Address")
    public void saveAddressTest(){
        Address address=getSampleAddress(101);
        Address saved=addressRepo.save(address);
        assertEquals(101,saved.getId());
        assertEquals("Delhi",saved.getCity());
    }

    @Test
    @DisplayName("Find By ID")
    public void findByIdTest(){
        Address address=getSampleAddress(102);
        addressRepo.save(address);
        Optional<Address> address1=addressRepo.findById(102);
        assertTrue(address1.isPresent());
        assertEquals("110001",address1.get().getPincode());
    }

    @Test
    @DisplayName("Delete Address")
    public void deleteAddressTest(){
        Address address=getSampleAddress(103);
        addressRepo.save(address);
        addressRepo.deleteById(103);
        Optional<Address> res=addressRepo.findById(103);
        assertFalse(res.isPresent());
    }
    private Address getSampleAddress(int id) {
        return new Address(
                id,
                "123 Main Road",
                "Delhi",
                "India",
                "9876543210",
                "110001",
                "Delhi",
                "Near Metro Station"
        );
    }

}
