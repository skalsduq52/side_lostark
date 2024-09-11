package com.yeop.lostark.vo.character;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yeop.lostark.vo.arkPassive.ArkPassive;
import com.yeop.lostark.vo.avatar.ArmoryAvatars;
import com.yeop.lostark.vo.engraving.ArmoryEngraving;
import com.yeop.lostark.vo.gem.ArmoryGem;
import com.yeop.lostark.vo.equipment.ArmoryEquipment;
import com.yeop.lostark.vo.profile.ArmoryProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoaCharacter {
    private ArmoryProfile armoryProfile;
    private List<ArmoryEquipment> armoryEquipment;
    private List<ArmoryAvatars> armoryAvatars;
    private ArmoryEngraving armoryEngraving;
    private ArmoryGem armoryGem;
    private ArkPassive arkPassive;


}
