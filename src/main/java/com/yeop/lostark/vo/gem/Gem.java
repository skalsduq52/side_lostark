package com.yeop.lostark.vo.gem;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Gem {
    private String name = "";
    private int count = 0;
    private double average = 0.0;

    public Gem(String name){
        this.name = name;
    }

}
