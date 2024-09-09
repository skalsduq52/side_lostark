package com.yeop.lostark.vo.gem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Skills {
    private int gemSlot;
    private String name;
    private String option;
    private String icon;
    private String tooltip;
}
