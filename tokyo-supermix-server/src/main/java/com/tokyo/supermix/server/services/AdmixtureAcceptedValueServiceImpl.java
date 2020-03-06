package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokyo.supermix.data.entities.AdmixtureAcceptedValue;
import com.tokyo.supermix.data.repositories.AdmixtureAcceptedValueRepository;

@Service
public class AdmixtureAcceptedValueServiceImpl implements AdmixtureAcceptedValueService{

	 @Autowired
	  private AdmixtureAcceptedValueRepository admixtureAcceptedValueRepository;
	@Override
	public void saveAdmixtureAcceptedValue(AdmixtureAcceptedValue admixtureAcceptedValue) {
		admixtureAcceptedValueRepository.save(admixtureAcceptedValue);
		
	}

	@Override
	public List<AdmixtureAcceptedValue> getAllAdmixtureAcceptedValues() {
		
		return admixtureAcceptedValueRepository.findAll();
	}

	@Override
	public boolean isAdmixtureAcceptedValueExist(Long id) {
		// TODO Auto-generated method stub
		return admixtureAcceptedValueRepository.existsById(id);
	}

	@Override
	public AdmixtureAcceptedValue getAdmixtureAcceptedValueById(Long id) {
		// TODO Auto-generated method stub
		return admixtureAcceptedValueRepository.findById(id).get();
	}

	@Override
	public void deleteAdmixtureAcceptedValue(Long id) {
		admixtureAcceptedValueRepository.deleteById(id);
		
	}

}
