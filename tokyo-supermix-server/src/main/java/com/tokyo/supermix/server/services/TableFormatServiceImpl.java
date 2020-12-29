package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.TableFormat;
import com.tokyo.supermix.data.repositories.TableFormatRepository;

@Service
public class TableFormatServiceImpl implements TableFormatService {
	@Autowired
	private TableFormatRepository tableFormatRepository;

	@Transactional
	public void saveTableFormat(TableFormat tableFormat) {
		tableFormatRepository.save(tableFormat);
	}

	@Transactional(readOnly = true)
	public boolean isTableFormatExist(Long id) {
		return tableFormatRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<TableFormat> getAllTableFormats() {
		return tableFormatRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteTableFormat(Long id) {
		tableFormatRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public TableFormat getTableFormatById(Long id) {
		return tableFormatRepository.getOne(id);
	}

	@Transactional(readOnly = true)
	public boolean existsTableFormatName(String name) {
		return tableFormatRepository.existsBytableName(name);
	}

}
