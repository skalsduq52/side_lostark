package com.yeop.lostark.vo.arkPassive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArkPassive {
    private boolean isArkPassive;
    private List<Points> points;
    private List<Effects> effects;
}
