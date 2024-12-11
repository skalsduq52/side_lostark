package com.yeop.lostark.vo.character;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public class OwnEngraving {
    private final Map<String, String> ownEngravingMap;

    public OwnEngraving() {
        ownEngravingMap = new HashMap<>();

        //슈사
        ownEngravingMap.put("홀리나이트","심판자,축복의 오라");
        ownEngravingMap.put("버서커","광전사의 비기,광기");
        ownEngravingMap.put("슬레이어","처단자,포식자");
        ownEngravingMap.put("워로드","전투 태세,고독한 기사");
        ownEngravingMap.put("디스트로이어","분노의 망치,중력 수련");

        //데런
        ownEngravingMap.put("데모닉","멈출 수 없는 충동,완벽한 억제");
        ownEngravingMap.put("소울이터","만월의 집행자,그믐의 경계");
        ownEngravingMap.put("블레이드","잔재된 기운,버스트");
        ownEngravingMap.put("리퍼","달의 소리,갈증");

        //애니츠
        ownEngravingMap.put("기공사","역천지체,세맥타통");
        ownEngravingMap.put("창술사","절정,절제");
        ownEngravingMap.put("인파이터","극의 : 체술,충격 단련");
        ownEngravingMap.put("배틀마스터","초심,오의 강화");
        ownEngravingMap.put("브레이커","권왕파천무,수라의 길");
        ownEngravingMap.put("스트라이커","일격필살,오의난무");

        //아르데
        ownEngravingMap.put("스카우터","아르데타인의 기술,진화의 유산");
        ownEngravingMap.put("데빌헌터","전술 탄환,핸드 거너");
        ownEngravingMap.put("블래스터","화력 강화,포격 강화");
        ownEngravingMap.put("호크아이","두 번째 동료,죽음의 습격");
        ownEngravingMap.put("건슬링어","평화주의자,사냥의 시간");

        //스페셜리스트
        ownEngravingMap.put("도화가","만개,회귀");
        ownEngravingMap.put("기상술사","질풍노도,이슬비");

        //실린
        ownEngravingMap.put("바드","절실한 구원,진실된 용맹");
        ownEngravingMap.put("아르카나","황제의 칙령,황후의 은총");
        ownEngravingMap.put("소서리스","점화,환류");
        ownEngravingMap.put("서머너","상급 소환사,넘치는 교감");

    }
}
