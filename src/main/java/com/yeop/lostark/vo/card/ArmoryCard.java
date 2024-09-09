package com.yeop.lostark.vo.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmoryCard {
    private Cards cards;
    private Effects effects;
}
