package com.yeop.lostark.controller;

import com.yeop.lostark.service.CharacterService;
import com.yeop.lostark.vo.ArmoryProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/loa")
public class LostarkController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("")
    public ArmoryProfile getUser(@RequestParam String characterName) throws IOException, InterruptedException {
        return characterService.getUser(characterName);
    }
}
