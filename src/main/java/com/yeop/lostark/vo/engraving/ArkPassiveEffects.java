package com.yeop.lostark.vo.engraving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArkPassiveEffects {
    private int abilityStoneLevel;
    private String grade;
    private int level;
    private String name;
}
