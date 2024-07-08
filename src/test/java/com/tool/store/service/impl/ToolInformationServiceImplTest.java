package com.tool.store.service.impl;

import com.tool.store.database.entity.ToolChargeEntity;
import com.tool.store.database.entity.ToolEntity;
import com.tool.store.database.repository.ToolChargeRepository;
import com.tool.store.database.repository.ToolRepository;
import com.tool.store.exception.InvalidToolCodeException;
import com.tool.store.exception.InvalidToolTypeException;
import com.tool.store.service.model.ToolInfoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToolInformationServiceImplTest {
    @Mock
    private ToolRepository toolRepository;
    @Mock
    private ToolChargeRepository toolChargeRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private ToolInformationServiceImpl sut;

    @BeforeEach
    public void initialize() {
        sut = new ToolInformationServiceImpl(toolRepository, toolChargeRepository, modelMapper);
    }

    @Test
    public void shouldGetToolInformation() {
        final String TOOL_CODE = "TOOL CODE";
        final String TOOL_TYPE = "Type";

        ToolEntity foundTool = new ToolEntity("", TOOL_TYPE, "");
        when(toolRepository.findById(TOOL_CODE)).thenReturn(Optional.of(foundTool));
        when(toolChargeRepository.findById(TOOL_TYPE)).thenReturn(Optional.of(new ToolChargeEntity()));

        ToolInfoModel result = sut.getToolInformation(TOOL_CODE);
        assertNotNull(result.getTool());
        assertNotNull(result.getToolCharge());
    }

    @Test
    public void shouldThrowToolNotFoundExceptionWhenToolDoesntExist() {
        final String TOOL_CODE = "TOOL CODE";
        assertThrows(InvalidToolCodeException.class, () -> sut.getToolInformation(TOOL_CODE));
    }

    @Test
    public void shouldThrowToolTypeNotFoundExpWhenChargeNotFound() {
        final String TOOL_CODE = "TOOL CODE";
        final String TOOL_TYPE = "Type";

        ToolEntity foundTool = new ToolEntity("", TOOL_TYPE, "");
        when(toolRepository.findById(TOOL_CODE)).thenReturn(Optional.of(foundTool));

        assertThrows(InvalidToolTypeException.class, () -> sut.getToolInformation(TOOL_CODE));
    }
}