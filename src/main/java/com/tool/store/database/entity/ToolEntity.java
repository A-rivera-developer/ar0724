package com.tool.store.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tools")
public class ToolEntity {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;
    @Column(name = "brand")
    private String brand;
}
