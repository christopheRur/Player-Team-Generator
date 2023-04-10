package com.scopic.javachallenge.service;

import com.scopic.javachallenge.models.PlayerSkill;
import com.scopic.javachallenge.models.TeamRequirement;
import com.scopic.javachallenge.exceptions.ErrorMessages;
import com.scopic.javachallenge.exceptions.PlayerException;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.repositories.PlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerService {
    private static final Logger log = LogManager.getLogger(PlayerService.class);
    private PlayerRepository playerRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public PlayerService(PlayerRepository playerRepo,
                         EntityManager entityMana){
        this.playerRepository=playerRepo;
        this.entityManager=entityMana;
    }

    /**
     * The method will create/save the player, and return the created player.
     * @param player
     * @return player
     */
    public Player createPlayer (Player player) {
            return playerRepository.save(player);
    }

    /**
     * The function will fetch the player by id, and return the player with that specified playerId,
     * else will throw exception playerId not found.
     * @param playerId
     * @return Player
     */
    public Player findPlayerById(Long playerId){
        return playerRepository.findById(playerId).orElseThrow(
                ()->new PlayerException("The specified PlayerId "+playerId+" not found!"));
    }

    /**
     * Will update the player, once found.
     * @param playerId
     * @return updated player
     */
    @Transactional
    public Player updatePlayer(Long playerId,Player player){

       Player updatedPlayer = findPlayerById(playerId);

        updatedPlayer.setName(player.getName());
        updatedPlayer.setPosition(player.getPosition());

        updatedPlayer.setPlayerSkills(player.getPlayerSkills());


        entityManager.refresh(updatedPlayer); // refresh the player entity to ensure it's up-to-date


        log.info("===>Updated player successfully...");


        return playerRepository.save(player);
    }

    /**
     * Method will list available players in database.
     * @return List
     */
    public List<Player> retrieveAllPlayers(){
        log.info("==> Listing players in progress...");
        return playerRepository.findAll();
    }

    /**
     * Deletes player after providing the id.
     * @param playerId Long
     * @return String
     */
    public String deletePlayer(Long playerId){
        Player delPlay = findPlayerById(playerId);

        playerRepository.delete(delPlay);
        log.info("==> Player with id "+delPlay.getId()+" was deleted successfully!");

        return ErrorMessages.sendSuccess("Player with id "+delPlay.getId()+" was deleted successfully!").toString();
    }

    /**
     * Helper method, to get skill value
     * @param player Player
     * @param skill String
     * @return int
     */
    public int retrieveSkillValue(Player player, String skill) {
        for (PlayerSkill playerSkill : player.getPlayerSkills()) {
            if (playerSkill.getSkill().equals(skill)) {
                return playerSkill.getValue();
            }
        }
        return 0;
    }

    /**
     * Will retrieve the best player
     * @param allRequirements List<TeamRequirement>
     * @return List<Player>
     */
    public List<Player> retrieveBestPlayers(List<TeamRequirement> allRequirements) {

        List<Player> theTeam = new ArrayList<>();

        try {

            for (TeamRequirement requirement : allRequirements) {

                String position = requirement.getPosition();
                String skill = requirement.getMainSkill();
                int numbOfPly = requirement.getNumberOfPlayers();

                List<Player> playersList = playerRepository.findByPosition(position);

                if (playersList.isEmpty()) {
                    throw new RuntimeException("Invalid number of players for position: " + position);
                }
                // sort players by the skill specified in the requirement
                Collections.sort(playersList, new Comparator<Player>() {
                    @Override
                    public int compare(Player player1, Player player2) {
                        int skillValue1 = retrieveSkillValue(player1, skill);
                        int skillValue2 = retrieveSkillValue(player2, skill);
                        return Integer.compare(skillValue2, skillValue1);
                    }
                });

                // Add the required number of players to the result list
                for (int i = 0; i < numbOfPly; i++) {
                    if (i < playersList.size()) {
                        Player player = playersList.get(i);
                        // Check if the player has already been added to the result list
                        if (!theTeam.contains(player)) {
                            theTeam.add(player);
                        }
                    } else {
                        // If there are not enough players with the required skill, add the best player in the position
                        Player player = playerRepository.findTopByPosition(position);
                        if (player != null) {
                            // verify if the player has been added to the result list
                            if (!theTeam.contains(player)) {
                                theTeam.add(player);
                            }
                        }
                    }
                }
            }
        }catch (Exception exception){

            log.info("-> Error Occurred ==>"+exception.getMessage());
            return theTeam;
        }
        return theTeam;
        }
}






