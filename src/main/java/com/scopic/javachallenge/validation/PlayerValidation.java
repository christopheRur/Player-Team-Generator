package com.scopic.javachallenge.validation;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PlayerValidation {

    private static final Logger log = LogManager.getLogger(PlayerValidation.class);

    /**
     * method will validate the body/player sent
     * @param player
     * @return Player
     */
    public static String validatePlayer(Player player) {

        try {

            if (player == null) return "Player information missing, please check and try again.";

            if (StringUtils.isBlank(player.getName())) return "Invalid value for name: " + player.getName();

            try {
                PlayerPosition.valueOf(player.getPosition().toString());
            } catch (IllegalArgumentException e) {
                return "Invalid value for position: " + player.getPosition().toString();

            }

            // Check if playerSkills is null or empty
            if (player.getPlayerSkills() == null || player.getPlayerSkills().isEmpty()) {
                return "PlayerSkills list is null or empty";
            }

            // Check if each playerSkill has a valid skill
            for (PlayerSkill playerSkill : player.getPlayerSkills()) {
                if (playerSkill.getSkill() == null) {
                    log.info("===>Invalid value for skill: "+playerSkill.getSkill());
                    return "Invalid value for skill: "  + playerSkill.getSkill();
                }
            }

            return null;
        }catch (Exception e){

            log.error("-> Error Occurred ====>  "+e.getMessage());

            return null;
        }
    }
}
