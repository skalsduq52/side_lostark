package com.yeop.lostark.vo.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
