package com.yeop.lostark.vo.character;

import com.yeop.lostark.vo.arkPassive.Points;
import com.yeop.lostark.vo.card.Card;
import com.yeop.lostark.vo.card.Cards;
import com.yeop.lostark.vo.engraving.Engraving;
import com.yeop.lostark.vo.gem.Gem;
import com.yeop.lostark.vo.profile.Stats;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CharacterInfo {
    // 무기 등급
    private String weaponGrade;
    // 무기 강화 수치
    private String weaponValue;
    // 무기 상재 수치
    private String EnhancementValue;
    // 엘릭서
    private String elixirName;
    private String elixirValue;
    // 전압 착용
    private int avatars;
    // 스탯 (치 특 신)
    private List<Stats> stats;
    // 아크패시브 스탯 ( 진화 깨달음 도약)
    private List<Points> arkPassiveStats;
    // 각인 정보
    private List<Engraving> engravings;
    // 시너지 정보
    private List<String> synergys;
    // 카드 정보
    private List<Cards> cards;
    // 보석 정보
    private Gem gem;
    // 아크패시브 정보
    private Map<String, List<String>> arkPassiveEffects;

}
