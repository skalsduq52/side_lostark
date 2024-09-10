package com.yeop.lostark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeop.lostark.vo.character.CharacterInfo;
import com.yeop.lostark.vo.character.LoaCharacter;
import com.yeop.lostark.vo.character.Transcendence;
import com.yeop.lostark.vo.character.ViewCharacter;
import com.yeop.lostark.vo.equipment.ArmoryEquipment;
import com.yeop.lostark.vo.equipment.TooltipData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    static final String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDA1NDc3MDcifQ.mo9WzWy4tGvl9Ih2Ulv7-M3xRLzbY56zNvkN13LC-9kQmOjvIRTEgyso_7Gezk8jKwcif-R_7T6h4odGRhtzrpTRYSJU4lwYFN9k_vORisYK5rJomY6EGfB7Igep8sHl41TVP6m3BWNQJ2NYVVpDEHrUzXmpidXj2a1uFOCfwhCeCtx9VG3du4Dqa9C4yTFfYmB0AS7TcE9a6oeveYbe7IVElVY9StLvBNiodYGY6KdaUXM6_nBegCEDPZCyLZNovjO45IAfIhsPAkzpTD6FCQzz30DaekF6DO-WhemqfxHt-RbuxVC0ZjEU90IaLleHRMX7deuqjBEjQ4BxZXrlUw";
    private LoaCharacter character;
    private ViewCharacter viewCharacter = new ViewCharacter();
    private CharacterInfo characterInfo = new CharacterInfo();
    private Transcendence transcendence = new Transcendence();

    public ViewCharacter getUser(String characterName) throws IOException, InterruptedException {

        characterName = URLEncoder.encode(characterName,"utf-8");

        String url = "https://developer-lostark.game.onstove.com/armories/characters/"+characterName;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization","Bearer "+apiKey)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200) {
            String jsonResponse = response.body();

            ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
            character = objectMapper.readValue(jsonResponse, LoaCharacter.class);

            // 캐릭터 정보 세팅
            extArmoryEquipment(character);


        }else {
            System.out.println(response.statusCode());
        }

        // 캐릭터 정보 세팅
        viewCharacter.setCharacterInfo(characterInfo);
        // 초월 정보 세팅
        viewCharacter.setTranscendence(transcendence);
        // 전체 캐릭터 정보 세팅
        viewCharacter.setLoaCharacter(character);


        return viewCharacter;

    }

    public void extArmoryEquipment(LoaCharacter character) {

        // 무기 정보 뽑기
        Optional<ArmoryEquipment> weapon  = character.getArmoryEquipment().stream()
                .filter(equipment -> "무기".equals(equipment.getType()))
                .findFirst();

        weapon.ifPresentOrElse(equipment -> {
            // 무기 강화 수치
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(equipment.getName());
            if(matcher.find()){
                characterInfo.setWeaponValue(matcher.group());
            }

            // 무기 등급 수치
            String toolTip = equipment.getTooltip().replaceAll("<[^>]*>", "");
            Pattern weaponPattern = Pattern.compile("\"leftStr0\": \"(\\S+) (\\S+)\"");
            Matcher weaponMatcher = weaponPattern.matcher(toolTip);
            if(weaponMatcher.find()) {
                characterInfo.setWeaponGrade(weaponMatcher.group(1));
            }

            // 상급재련 수치 가져오기

            Pattern enhancementPattern = Pattern.compile("\\[상급 재련\\] (\\d+)단계");
            Matcher enhancementMatcher = enhancementPattern.matcher(toolTip);
            if(enhancementMatcher.find()){
                characterInfo.setEnhancementValue("+"+enhancementMatcher.group(1));
            }

            // 초월 수치 가져오기
            Pattern transcendencePattern = Pattern.compile("슬롯 효과\\[초월\\] (\\d+)단계 (\\d+)");
            Matcher transcendenceMatcher = transcendencePattern.matcher(toolTip);
            if(transcendenceMatcher.find()){
                transcendence.setWeaponValue("+"+transcendenceMatcher.group(2));
            }

        }, () -> characterInfo.setWeaponValue("0"));

        // 방어구
        List<ArmoryEquipment> armor = character.getArmoryEquipment().stream()
                .filter(equipment -> "투구".equals(equipment.getType())
                        ||"상의".equals(equipment.getType())
                        ||"하의".equals(equipment.getType())
                        ||"장갑".equals(equipment.getType())
                        ||"어깨".equals(equipment.getType()))
                .toList();

        int armorValue = 0;
        int elixirValue = 0;
        String elixirName = "";
        for(ArmoryEquipment equipment : armor){
            // 방어구 초월 수치
            String toolTip = equipment.getTooltip().replaceAll("<[^>]*>", "");
            Pattern transcendencePattern = Pattern.compile("슬롯 효과\\[초월\\] (\\d+)단계 (\\d+)");
            Matcher transcendenceMatcher = transcendencePattern.matcher(toolTip);
            if(transcendenceMatcher.find()){
                armorValue += Integer.parseInt(transcendenceMatcher.group(2));
            }

            // 엘릭서 이름
            Pattern elixirPattern = Pattern.compile("연성 추가 효과(\\S+)");
            Matcher elixirMatcher = elixirPattern.matcher(toolTip);
            if(elixirMatcher.find()){
                elixirName = elixirMatcher.group(1);
            }

            // 방어구 엘릭서 수치
            Pattern elixirValuePattern = Pattern.compile("Lv\\.(\\d+)");
            Matcher elixirValueMatcher = elixirValuePattern.matcher(toolTip);
            while(elixirValueMatcher.find()){
                elixirValue += Integer.parseInt(elixirValueMatcher.group(1));
            }

        }

        transcendence.setArmorValue("+"+armorValue);
        characterInfo.setElixirName(elixirName);
        characterInfo.setElixirValue(String.valueOf(elixirValue));

        System.out.println("s");

    }
}
