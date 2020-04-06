package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteTestElement;
import com.tokyo.supermix.data.repositories.ConcreteTestElementRepository;

@Service
public class ConcreteTestElementServiceImpl implements ConcreteTestElementService {
  @Autowired
  private ConcreteTestElementRepository ConcreteTestElementRepository;

  @Transactional
  public void saveConcreteTestElement(ConcreteTestElement ConcreteTestElement) {
    ConcreteTestElementRepository.save(ConcreteTestElement);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestElement> getAllConcreteTestElement() {
    return ConcreteTestElementRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ConcreteTestElement getConcreteTestElementById(Long id) {
    return ConcreteTestElementRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteTestElement(Long id) {
    ConcreteTestElementRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestElementExists(Long id) {
    return ConcreteTestElementRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestElementNameExists(String name) {
    return ConcreteTestElementRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestElementAbbreviationExists(String abbreviation) {
    return ConcreteTestElementRepository.existsByAbbreviation(abbreviation);
  }

  @Transactional(readOnly = true)
  public boolean isUpdateConcreteTestElementNameExists(Long id, String name) {
    if ((!getConcreteTestElementById(id).getName().equalsIgnoreCase(name))
        && (isConcreteTestElementNameExists(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUpdateConcreteTestElementAbbreviationExists(Long id, String abbreviation) {
    if ((!getConcreteTestElementById(id).getAbbreviation().equalsIgnoreCase(abbreviation))
        && (isConcreteTestElementNameExists(abbreviation))) {
      return true;
    }
    return false;
  }
}
