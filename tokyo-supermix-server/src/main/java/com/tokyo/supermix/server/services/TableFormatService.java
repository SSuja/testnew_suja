package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.TableFormat;

public interface TableFormatService {
  public void saveTableFormat(TableFormat tableFormat);

  public boolean isTableFormatExist(Long id);

  public List<TableFormat> getAllTableFormats();

  public void deleteTableFormat(Long id);

  public TableFormat getTableFormatById(Long id);
}
