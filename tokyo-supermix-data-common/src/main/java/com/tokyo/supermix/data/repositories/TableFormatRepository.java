package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.TableFormat;

public interface TableFormatRepository extends JpaRepository<TableFormat, Long> {
	boolean  existsBytableName(String tableName);
}
