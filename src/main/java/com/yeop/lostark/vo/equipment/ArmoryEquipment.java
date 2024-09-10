package com.yeop.lostark.vo.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmoryEquipment {
    private String type;
    private String icon;
    private String name;
    private String grade;
    private String tooltip;
}
