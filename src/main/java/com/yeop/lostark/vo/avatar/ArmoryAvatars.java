package com.yeop.lostark.vo.avatar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmoryAvatars {
    private String type;
    private String name;
    private String icon;
    private String grade;
    private String isInner;
}
