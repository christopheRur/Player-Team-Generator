// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.exceptions.ErrorMessages;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;
import com.scopic.javachallenge.service.PlayerService;
import com.scopic.javachallenge.validation.AuthTokenValidation;
import com.scopic.javachallenge.validation.PlayerValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.scopic.javachallenge.constants.CommonConstant.REASON;


@RestController
public class PlayerController {

    private static final Logger log = LogManager.getLogger(PlayerController.class);

    private final PlayerService playerService;


    public PlayerController(PlayerService playerServ){
        this.playerService=playerServ;
    }

    @GetMapping("/player")
    public ResponseEntity<List<Player>> index() {

        return new ResponseEntity<>(playerService.retrieveAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Void> show(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * The method creates a new player.
     * @param player Player
     * @return ?
     */
    @PostMapping("/player")
    public ResponseEntity<?> create(@RequestBody Player player) {


        try {

           if(PlayerValidation.validatePlayer(player)!=null){
              log.info("===>Creating player failed -> "+PlayerValidation.validatePlayer(player));
               return ResponseEntity.badRequest().body(
                       ErrorMessages.sendError(PlayerValidation.validatePlayer(player)));
           }

            Player newPlayer=playerService.createPlayer(player);
            log.info("==> Successfully Created player!");

            return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("===> Failed to create player. "+REASON,e.getMessage());

            return ResponseEntity.badRequest().body(
                    ErrorMessages.sendError(PlayerValidation.validatePlayer(player)).toString());
        }
    }

    /**
     * Function will update the player, first validate the payload.
     * @param id Long
     * @param player Player
     * @return ?
     */
    @PutMapping("/player/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                       @RequestBody Player player) {

        try{

            if(PlayerValidation.validatePlayer(player)!=null){

                log.info("===>Updating player failed -> "+PlayerValidation.validatePlayer(player));

                return ResponseEntity.badRequest().body(
                        ErrorMessages.sendError(PlayerValidation.validatePlayer(player)));
            }

            log.info("===>Updating player in progress...");

            return new ResponseEntity<>(playerService.updatePlayer(id,player),HttpStatus.OK);


        }catch(Exception e){
            log.error(" ===> Failed to update player. "+e.getMessage());

            return ResponseEntity.badRequest().body(
                    ErrorMessages.sendError(PlayerValidation.validatePlayer(player)).toString());
        }
    }

    /**
     * Remove specified player from database.
     * @param id Long
     * @param authToken String.
     * @return ?
     */
    @DeleteMapping("/player/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id, @RequestHeader("Authorization") String authToken) {
        try{

            if(!AuthTokenValidation.validateAuthToken(authToken)){
                log.info(" ===> Check AuthToken!");

                return new ResponseEntity<>(ErrorMessages.sendError("Invalid authorization token.").toString(),
                        HttpStatus.UNAUTHORIZED);
            }


            return new ResponseEntity<>(playerService.deletePlayer(id),HttpStatus.OK);


        }catch(Exception e){
            log.error(" ===> Failed to delete player. "+e.getMessage());

            return ResponseEntity.badRequest().body(playerService.deletePlayer(id));
        }
    }
}
