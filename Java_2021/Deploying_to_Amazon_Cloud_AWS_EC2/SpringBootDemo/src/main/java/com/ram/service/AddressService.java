package com.ram.service;

import java.util.List;

import com.ram.dto.AddressDTO;

public interface AddressService
{
	List<AddressDTO> getAddresses(String userId);
	AddressDTO getAddress(String userId,String addressId);
}
