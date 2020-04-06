package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ConcreteTestElement;

public interface ConcreteTestElementService {
  public void saveConcreteTestElement(ConcreteTestElement ConcreteTestElement);

  public List<ConcreteTestElement> getAllConcreteTestElement();

  public ConcreteTestElement getConcreteTestElementById(Long id);

  public void deleteConcreteTestElement(Long id);

  public boolean isConcreteTestElementExists(Long id);

  public boolean isConcreteTestElementNameExists(String name);

  public boolean isConcreteTestElementAbbreviationExists(String abbreviation);

  public boolean isUpdateConcreteTestElementNameExists(Long id, String name);

  public boolean isUpdateConcreteTestElementAbbreviationExists(Long id, String abbreviation);
}
