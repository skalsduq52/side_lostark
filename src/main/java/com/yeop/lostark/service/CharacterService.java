package com.yeop.lostark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeop.lostark.vo.avatar.ArmoryAvatars;
import com.yeop.lostark.vo.card.ArmoryCard;
import com.yeop.lostark.vo.card.Card;
import com.yeop.lostark.vo.card.Cards;
import com.yeop.lostark.vo.card.Items;
import com.yeop.lostark.vo.character.*;
import com.yeop.lostark.vo.engraving.ArkPassiveEffects;
import com.yeop.lostark.vo.engraving.Effects;
import com.yeop.lostark.vo.engraving.Engraving;
import com.yeop.lostark.vo.equipment.Accesory;
import com.yeop.lostark.vo.equipment.ArmoryEquipment;
import com.yeop.lostark.vo.gem.Gem;
import com.yeop.lostark.vo.gem.Gems;
import com.yeop.lostark.vo.profile.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    static final String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDA1NDc3MDcifQ.mo9WzWy4tGvl9Ih2Ulv7-M3xRLzbY56zNvkN13LC-9kQmOjvIRTEgyso_7Gezk8jKwcif-R_7T6h4odGRhtzrpTRYSJU4lwYFN9k_vORisYK5rJomY6EGfB7Igep8sHl41TVP6m3BWNQJ2NYVVpDEHrUzXmpidXj2a1uFOCfwhCeCtx9VG3du4Dqa9C4yTFfYmB0AS7TcE9a6oeveYbe7IVElVY9StLvBNiodYGY6KdaUXM6_nBegCEDPZCyLZNovjO45IAfIhsPAkzpTD6FCQzz30DaekF6DO-WhemqfxHt-RbuxVC0ZjEU90IaLleHRMX7deuqjBEjQ4BxZXrlUw";
    private LoaCharacter character;
    private ViewCharacter viewCharacter = new ViewCharacter();
    private CharacterInfo characterInfo;
    private Transcendence transcendence;
    private Synergy synergy = new Synergy();
    private OwnEngraving ownEngraving = new OwnEngraving();

    public ViewCharacter getUser(String characterName) throws IOException, InterruptedException {

            characterName = URLEncoder.encode(characterName,"utf-8");

        characterInfo = new CharacterInfo();
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


            if(character != null){
                List<String> synergys = List.of(synergy.getSynergyMap().get(character.getArmoryProfile().getCharacterClassName()).split(","));

                // 시너지 세팅
                characterInfo.setSynergys(synergys);
                // 장비 정보
                extArmoryEquipment(character);
                // 아바타 정보
                extArmoryAvatar(character);
                // 아크패시브 정보
                extArkPassive(character);
                // 각인 정보
                extEngraving(character);
                // 카드 정보
                extArmoryCard(character);
                // 보석 정보
                extArmoryGem(character);

                viewCharacter.setCharacterInfo(characterInfo);
                // 초월 정보 세팅
                viewCharacter.setTranscendence(transcendence);
                // 전체 캐릭터 정보 세팅
                viewCharacter.setLoaCharacter(character);

                viewCharacter.setFlagSuccess(true);
            }else{
                viewCharacter = new ViewCharacter();
            }
        }else {
            System.out.println(response.statusCode());
        }

        return viewCharacter;
    }

    public void extArmoryEquipment(LoaCharacter character) {

        transcendence = new Transcendence();

        List<ArmoryEquipment> armor = character.getArmoryEquipment().stream()
                .filter(equipment -> "투구".equals(equipment.getType())
                        ||"상의".equals(equipment.getType())
                        ||"하의".equals(equipment.getType())
                        ||"장갑".equals(equipment.getType())
                        ||"어깨".equals(equipment.getType())
                        ||"무기".equals(equipment.getType()))
                .toList();

        int totalValue = 0;
        int elixirValue = 0;
        String elixirName = "";
        for(ArmoryEquipment equipment : armor){
            String toolTip = equipment.getTooltip().replaceAll("<[^>]*>", "");

            // 강화 수치
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(equipment.getName());
            if(matcher.find()){
                equipment.setValue(matcher.group());
            }

            // 상재 수치
            Pattern enhancementPattern = Pattern.compile("\\[상급 재련\\] (\\d+)단계");
            Matcher enhancementMatcher = enhancementPattern.matcher(toolTip);
            if(enhancementMatcher.find()){
                equipment.setEnhancementValue("+"+enhancementMatcher.group(1));
            }

            // 초월 수치
            Pattern transcendencePattern = Pattern.compile("슬롯 효과\\[초월\\] (\\d+)단계 (\\d+)");
            Matcher transcendenceMatcher = transcendencePattern.matcher(toolTip);
            if(transcendenceMatcher.find()){
                equipment.setTranscendenceValue(Integer.parseInt(transcendenceMatcher.group(2)));
                totalValue += Integer.parseInt(transcendenceMatcher.group(2));
            }

            // 품질 수치
            Pattern qualityPattern = Pattern.compile("\"qualityValue\"\\s*:\\s*(\\d+)");
            Matcher qualityMatcher = qualityPattern.matcher(toolTip);
            if(qualityMatcher.find()){
                equipment.setQuality(qualityMatcher.group(1));
                equipment.setQualityColor(getColorByQuality(qualityMatcher.group(1)));
            }

            // 엘릭서 이름
            Pattern elixirPattern = Pattern.compile("연성 추가 효과(\\S+)");
            Matcher elixirMatcher = elixirPattern.matcher(toolTip);
            if(elixirMatcher.find()){
                elixirName = elixirMatcher.group(1);
            }

            // 방어구 엘릭서 수치
            int index = 0;
            if(!elixirName.isEmpty()){
                Pattern elixirValuePattern = Pattern.compile("Lv\\.(\\d+)");
                Matcher elixirValueMatcher = elixirValuePattern.matcher(toolTip);
                while(elixirValueMatcher.find()&&index!=2){
                    index++;
                    elixirValue += Integer.parseInt(elixirValueMatcher.group(1));
                }
            }
        }

        List<ArmoryEquipment> accessories = character.getArmoryEquipment().stream()
                .filter(equipment -> "귀걸이".equals(equipment.getType())
                        ||"목걸이".equals(equipment.getType())
                        ||"팔찌".equals(equipment.getType())
                        ||"반지".equals(equipment.getType())
                        ||"어빌리티 스톤".equals(equipment.getType())                       )
                .toList();
        for(ArmoryEquipment accessory : accessories){
            String toolTip = accessory.getTooltip().replaceAll("<[^>]*>", "");
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                JsonNode rootNode = objectMapper.readTree(toolTip);
                JsonNode element005 = rootNode.path("Element_005");
                String accesoryValue = element005.path("value").path("Element_001").asText();
                checkAccesoryValue(accessory,accesoryValue, character.getArmoryProfile().getCharacterClassName());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

        transcendence.setTotalValue(String.valueOf(totalValue));
        characterInfo.setArmorEquipment(armor);
        characterInfo.setAccesoryEquipment(accessories);
        characterInfo.setElixirName(elixirName);
        characterInfo.setElixirValue(String.valueOf(elixirValue));


    }

    public void extArmoryAvatar(LoaCharacter character) {
        List<ArmoryAvatars> avatars = character.getArmoryAvatars().stream()
                .filter(avatar -> "전설".equals(avatar.getGrade()) &&
                        ("무기 아바타".equals(avatar.getType())
                        ||"머리 아바타".equals(avatar.getType())
                        ||"상의 아바타".equals(avatar.getType())
                        ||"하의 아바타".equals(avatar.getType())
                        ||"어깨".equals(avatar.getType())))
                .toList();

        characterInfo.setAvatars(avatars.size());

    }

    public void extArkPassive(LoaCharacter character) {

        characterInfo.setArkPassiveStats(new ArrayList<>());

        // 스탯 추가
        List<Stats> stats = character.getArmoryProfile().getStats().stream()
                .filter(stat -> "치명".equals(stat.getType())
                        ||"특화".equals(stat.getType())
                        ||"신속".equals(stat.getType()))
                        .toList();

        stats = stats.stream()
                .sorted(new Comparator<Stats>() {
                    @Override
                    public int compare(Stats s1, Stats s2) {
                        return Integer.compare(
                                Integer.parseInt(s2.getValue()),
                                Integer.parseInt(s1.getValue())
                        );
                    }
                })
                .collect(Collectors.toList());
        characterInfo.setStats(stats);

        List<Stats> basicStats = character.getArmoryProfile().getStats().stream()
                .filter(stat -> "최대 생명력".equals(stat.getType())
                        ||"공격력".equals(stat.getType())).toList();

        basicStats = basicStats.stream()
                .sorted(Comparator.comparing(Stats::getType))
                .toList();

        characterInfo.setBasicStats(basicStats);


        if(character.getArkPassive().isArkPassive()){
            // 아크패시브 스탯 넣기
            characterInfo.setArkPassiveStats(character.getArkPassive().getPoints());
        }
    }

    public void extEngraving(LoaCharacter character) {
        List<Engraving> engravings = new ArrayList<>();
        String[] grades = {"영웅", "전설", "유물"};
        List<String> grade = Arrays.stream(grades).toList();
        Map<String, List<String>> arkPassiveEffects = new HashMap<>();
        if(character.getArkPassive().isArkPassive()){
            List<ArkPassiveEffects> list = character.getArmoryEngraving().getArkPassiveEffects();
            for(ArkPassiveEffects effect : list){
                Engraving engraving = new Engraving();
                String temp1 = "";
                int temp2 = 0;
                if(effect.getLevel()==0&&!effect.getGrade().equals("영웅")){
                    temp1 = grade.get(grade.indexOf(effect.getGrade())-1);
                    temp2 = 4;
                }else{
                    temp1 = effect.getGrade();
                    temp2 = effect.getLevel();
                }
                engraving.setName(effect.getName());
                engraving.setGrade(temp1);
                engraving.setLevel(temp2);
                engravings.add(engraving);
            }

            List<String> engraving = List.of(ownEngraving.getOwnEngravingMap().get(character.getArmoryProfile().getCharacterClassName()).split(","));
            List<com.yeop.lostark.vo.arkPassive.Effects> effectsList = character.getArkPassive().getEffects();

            for (int i = 0; i < engraving.size(); i++) {
                for (com.yeop.lostark.vo.arkPassive.Effects a : effectsList) {
                    if (a.getDescription().contains(engraving.get(i))) {
                        // 조건을 만족하는 경우의 처리
                        characterInfo.setOwnEngraving(engraving.get(i));
                        break;

                    }
                }
            }



            List<com.yeop.lostark.vo.arkPassive.Effects> effects = character.getArkPassive().getEffects();

            for(com.yeop.lostark.vo.arkPassive.Effects effect : effects){
                String description = effect.getDescription().replaceAll("<[^>]*>", "");;
                String[] parts = description.split(" ",3);
                if(parts.length == 3) {
                    String category = parts[0]; // 카테고리 추출 (깨달음, 진화, 도약 등)
                    String detail = parts[2];

                    arkPassiveEffects.putIfAbsent(category, new ArrayList<>());
                    arkPassiveEffects.get(category).add(detail);
                }
            }

        }else{
            List<Effects> list = character.getArmoryEngraving().getEffects();
            Pattern pattern = Pattern.compile("([가-힣\\s]+)\\s*Lv\\.\\s*(\\d+)");
            for(Effects effect : list){
                Engraving engraving = new Engraving();
                Matcher matcher = pattern.matcher(effect.getName());
                if(matcher.find()){
                    engraving.setName(matcher.group(1));
                    engraving.setLevel(Integer.parseInt(matcher.group(2)));
                }
                engravings.add(engraving);
            }

        }
        characterInfo.setArkPassiveEffects(arkPassiveEffects);
        characterInfo.setEngravings(engravings);

    }

    public void extArmoryCard(LoaCharacter character) {
        List<Cards> cardList = new ArrayList<>();
        List<com.yeop.lostark.vo.card.Effects> list = character.getArmoryCard().getEffects();
        for(int i =0; i<list.size(); i++){
            Cards cards = new Cards();
            List<Items> items = list.get(i).getItems();
            String name = items.get(items.size()-1).getName();

            Pattern pattern = Pattern.compile("([가-힣\\s]+)\\s*(\\d+)세트.*\\((\\d+)각성");
            Matcher matcher = pattern.matcher(name);

            if(matcher.find()){
                String cardName = matcher.group(1).trim();
                if(cardName.equals("세상을 구하는 빛")){
                    cardName = "세구빛";
                }else if(cardName.equals("너는 계획이 다 있구나")){
                    cardName = "너계획";
                }else if(cardName.equals("남겨진 바람의 절벽")){
                    cardName = "남바절";
                }else if(cardName.equals("카제로스의 군단장")){
                    cardName = "암구빛";
                }else if(cardName.equals("세 우마르가 오리라")){
                    cardName = "세 우마르";
                }else if(cardName.equals("라제니스의 운명")){
                    cardName = "라제";
                }
                
                cards.setName(cardName);
                cards.setAwakeTotal(Integer.parseInt(matcher.group(3)));
            }
    
            cardList.add(cards);
        }

        characterInfo.setCards(cardList);
    }

    public void extArmoryGem(LoaCharacter character) {
        List<Gems> gems = character.getArmoryGem().getGems();
        List<Gem> list = new ArrayList<>();
        Gem tier4 = new Gem("4T");
        Gem tier3 = new Gem("3T");
        if(gems != null){
            for(Gems a : gems){
                if(a.getName().contains("겁화") || a.getName().contains("작열") ){
                    tier4.setCount(tier4.getCount()+1);
                    tier4.setAverage(tier4.getAverage()+a.getLevel());
                } else if (a.getName().contains("멸화") || a.getName().contains("홍염")) {
                    tier3.setCount(tier3.getCount()+1);
                    tier3.setAverage(tier3.getAverage()+a.getLevel());
                }
            }
        }

        if (tier4.getCount() > 0){
            tier4.setAverage(Math.floor((tier4.getAverage() / tier4.getCount()) * 10) / 10.0);
            list.add(tier4);
        }
        if (tier3.getCount() > 0){
            tier3.setAverage(Math.floor((tier3.getAverage() / tier3.getCount()) * 10) / 10.0);
            list.add(tier3);
        }

        characterInfo.setGem(list);

    }

    public String getColorByQuality(String quality) {
        String color = "rgb(35,36,45)";
        int value = Integer.parseInt(quality);

        if (value >= 1 && value <= 10) color = "red";
        if (value >= 11 && value <= 30) color = "yellow";
        if (value >= 31 && value <= 69) color = "rgb(172,255,20)";
        if (value >= 70 && value <= 89) color = "rgb(14,178,245)";
        if (value >= 90 && value <= 99) color = "rgb(140,11,203)";
        if (value == 100) color = "rgb(251,146,44)";

        return color;
    }

    public ArmoryEquipment checkAccesoryValue(ArmoryEquipment accesory,String accesoryValue, String className) {
        Accesory accesoryCheck = new Accesory();
        if(className.equals("도화가")||className.equals("바드")||className.equals("홀리나이트")){
            String regex = "(세레나데, 신앙, 조화 게이지 획득량|낙인력|무기 공격력|공격력|아군 공격력 강화 효과|아군 피해량 강화 효과) \\+\\d+(\\.\\d{2})?%";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(accesoryValue);
            while(matcher.find()){
                accesory.setValue(accesory.getValue()+accesoryCheck.getSupportMap().get(matcher.group()));
            }
        }else{
            String regex = "(적에게 주는 피해|추가 피해|무기 공격력|공격력|치명타 적중률|치명타 피해) \\+\\d+(\\.\\d{2})?%";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(accesoryValue);
            while(matcher.find()){
                accesory.setValue(accesory.getValue()+accesoryCheck.getDealMap().get(matcher.group()));
            }
        }

        if(accesory.getValue().length()==0){
            accesory.setValue("떡작");
        }

        return accesory;
    }
}
