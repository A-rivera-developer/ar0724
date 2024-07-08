package com.tool.store.database.repository;

import com.tool.store.database.entity.ToolEntity;
import org.springframework.data.repository.CrudRepository;

public interface ToolRepository extends CrudRepository<ToolEntity, String> {
}
