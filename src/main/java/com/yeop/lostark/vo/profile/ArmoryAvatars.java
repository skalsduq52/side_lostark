package com.yeop.lostark.vo.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
