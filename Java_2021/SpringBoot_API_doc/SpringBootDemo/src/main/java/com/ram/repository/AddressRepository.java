package com.ram.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ram.entity.AddressEntity;
import com.ram.entity.UserEntity;  

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>
{
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
	AddressEntity findByUserDetailsAndAddressId(UserEntity userEntity,String addressId);
}
