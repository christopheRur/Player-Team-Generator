// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class PlayerControllerDeleteTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(
                delete(PLAYER_URL + 1))
                .andExpect(content().string(CoreMatchers.notNullValue()));
    }
}
