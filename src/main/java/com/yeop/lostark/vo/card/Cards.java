package com.yeop.lostark.vo.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cards {
    private String name;
    private String icon;
    private int awakeCount;
    private int awakeTotal;
    private String grade;
}
