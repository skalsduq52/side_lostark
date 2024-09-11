package com.yeop.lostark.vo.character;

import com.yeop.lostark.vo.arkPassive.Points;
import com.yeop.lostark.vo.profile.Stats;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

}
