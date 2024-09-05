package com.yeop.lostark.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmoryProfile {
    private String characterImage; // 캐릭터 사진
    private String expeditionLevel; // 원정대 레벨
    private String title; // 칭호
    private String serverName; // 서버명
    private String characterName; // 캐릭터명
    private String characterClassName; // 클래스명
    private int characterLevel; // 캐릭터 레벨
    private String itemMaxLevel; // 아이템 레벨
}
