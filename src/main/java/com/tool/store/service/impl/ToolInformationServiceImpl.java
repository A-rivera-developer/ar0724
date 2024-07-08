package com.tool.store.service.impl;

import com.tool.store.database.entity.ToolChargeEntity;
import com.tool.store.database.entity.ToolEntity;
import com.tool.store.database.repository.ToolChargeRepository;
import com.tool.store.database.repository.ToolRepository;
import com.tool.store.exception.InvalidToolCodeException;
import com.tool.store.exception.InvalidToolTypeException;
import com.tool.store.service.model.ToolChargeModel;
import com.tool.store.service.model.ToolInfoModel;
import com.tool.store.service.model.ToolModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToolInformationServiceImpl implements com.tool.store.service.ToolInformationService {
    private final ToolRepository toolRepository;
    private final ToolChargeRepository toolChargeRepository;
    private final ModelMapper modelMapper;

    public ToolInformationServiceImpl(ToolRepository toolRepository, ToolChargeRepository toolChargeRepository, ModelMapper modelMapper) {
        this.toolRepository = toolRepository;
        this.toolChargeRepository = toolChargeRepository;
        this.modelMapper = modelMapper;
    }

    public ToolInfoModel getToolInformation(String toolCode) {

        Optional<ToolEntity> optionalTool = toolRepository.findById(toolCode);
        if (optionalTool.isEmpty()) {
            throw new InvalidToolCodeException("Tool code does not exist");
        }
        ToolEntity toolEntity = optionalTool.get();

        Optional<ToolChargeEntity> optionalToolCharge = toolChargeRepository.findById(toolEntity.getType());
        if (optionalToolCharge.isEmpty()) {
            throw new InvalidToolTypeException("Tool type does not exist");
        }
        ToolChargeEntity toolChargeEntity = optionalToolCharge.get();

        ToolModel toolModel = modelMapper.map(toolEntity, ToolModel.class);
        ToolChargeModel toolChargeModel = modelMapper.map(toolChargeEntity, ToolChargeModel.class);

        return new ToolInfoModel(toolModel, toolChargeModel);
    }
}
