package com.yeop.lostark.vo.equipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TooltipData {
    private Map<String, Element> elements;
}
