package com.yeop.lostark.vo.arkPassive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Effects {
    private String name;
    private String description;
    private String icon;
    private String tooltip;
}
