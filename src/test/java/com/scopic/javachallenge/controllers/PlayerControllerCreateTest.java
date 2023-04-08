// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class PlayerControllerCreateTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(
                post(PLAYER_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(notNullValue()));
    }
}