package com.yeop.lostark.vo.gem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gems {
    private int slot;
    private String name;
    private String icon;
    private int level;
    private String grade;
    private String tooltip;
}
