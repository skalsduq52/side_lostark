package com.yeop.lostark.vo.engraving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmoryEngraving {
    private List<Effects> effects;
    private List<ArkPassiveEffects> arkPassiveEffects;

}
