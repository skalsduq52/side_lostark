package com.yeop.lostark.controller;

import com.yeop.lostark.service.CharacterService;
import com.yeop.lostark.vo.character.ViewCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class LostarkController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/char/{characterName}")
    public ViewCharacter getUser(@PathVariable String characterName) throws IOException, InterruptedException {
        return characterService.getUser(characterName);
    }
}
