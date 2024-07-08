package com.tool.store.database.repository;

import com.tool.store.database.entity.ToolChargeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface ToolChargeRepository extends CrudRepository<ToolChargeEntity, String> {

}
