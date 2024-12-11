package com.yeop.lostark.vo.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmoryEquipment {
    private String type;
    private String name;
    private String icon;
    private String value = "";
    private String enhancementValue;
    private int transcendenceValue;
    private String quality;
    private String qualityColor;
    private String tooltip;
}
