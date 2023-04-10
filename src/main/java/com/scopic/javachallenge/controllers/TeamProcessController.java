// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.TeamRequirement;
import com.scopic.javachallenge.service.PlayerService;
import org.aspectj.bridge.Message;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.scopic.javachallenge.constants.CommonConstant.MESSAGE;


@RestController
public class TeamProcessController {

    private final PlayerService playerService;

    public TeamProcessController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Finds the best player in the database with that skill and position
     * @param allRequirements List<TeamRequirement>
     * @return ?
     */
    @PostMapping("/team/process")
    public ResponseEntity<?> create(@RequestBody List<TeamRequirement> allRequirements) {


        return new ResponseEntity<>(playerService.retrieveBestPlayers(allRequirements),HttpStatus.OK);
    }
}
