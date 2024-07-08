package com.tool.store.service;

import com.tool.store.service.model.ToolInfoModel;

public interface ToolInformationService {
    ToolInfoModel getToolInformation(String toolCode);

}
