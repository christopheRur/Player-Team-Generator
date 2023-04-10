package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.scopic.javachallenge.constants.CommonConstant.AUTH_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest {

    @Mock
    private Player player;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;


    @Test
    public void testRetrievingPlayersList(){

           player.setName("Tester1");
           player.setPosition(PlayerPosition.DEFENDER);

        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(player);
        expectedPlayers.add(new Player());
        when(playerService.retrieveAllPlayers()).thenReturn(expectedPlayers);


        ResponseEntity<List<Player>> response = playerController.index();
        List<Player> actualPlayers = response.getBody();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPlayers, actualPlayers);

    }

    @Test
    public void testDeletePlayer() {

        player.setName("Tester1");
        player.setPosition(PlayerPosition.DEFENDER);
        player.setId(1L);


        ResponseEntity<?> responseEntity = playerController.delete(1L,AUTH_TOKEN);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }



}
