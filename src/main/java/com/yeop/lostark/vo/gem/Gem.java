package com.yeop.lostark.vo.gem;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Gem {
    private int guphwa = 0;
    private int jakyeol = 0;
    private int myulhwa = 0;
    private int hongyeom = 0;
    private Map<String, Integer> map;
}
