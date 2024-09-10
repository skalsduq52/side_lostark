package com.yeop.lostark.vo.character;

import lombok.Getter;
import lombok.Setter;

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

}
