package com.yeop.lostark.vo.character;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Synergy {
    private Map<String, String> synergyMap;

    public Synergy() {
        synergyMap = new HashMap<>();

        //슈사
        synergyMap.put("홀나","공증,이속");
        synergyMap.put("버서커","피증");
        synergyMap.put("슬레이어","피증");
        synergyMap.put("워로드","피증,백/헤드 피증,방깎");
        synergyMap.put("디스트로이어","방깎");

        //데런
        synergyMap.put("데모닉","피증");
        synergyMap.put("소울이터","피증");
        synergyMap.put("블레이드","피증,백/헤드 피증,공/이속");
        synergyMap.put("리퍼","방깎");

        //애니츠
        synergyMap.put("기공사","공증");
        synergyMap.put("창술사","치피증");
        synergyMap.put("인파이터","피증");
        synergyMap.put("배틀마스터","치적,공/이속");
        synergyMap.put("브레이커","피증");
        synergyMap.put("스트라이커","치적,공속");

        //아르데
        synergyMap.put("스카우터","공증");
        synergyMap.put("데빌헌터","치적");
        synergyMap.put("블래스터","방깎,무력 증가");
        synergyMap.put("호크아이","피증");
        synergyMap.put("건슬링어","치적");

        //스페셜리스트
        synergyMap.put("도화가","공증,공/이속");
        synergyMap.put("기상술사","치적");

        //실린
        synergyMap.put("바드","공증,공속");
        synergyMap.put("아르카나","치적");
        synergyMap.put("소서리스","피증");
        synergyMap.put("서머너","방깎");

    }
}
