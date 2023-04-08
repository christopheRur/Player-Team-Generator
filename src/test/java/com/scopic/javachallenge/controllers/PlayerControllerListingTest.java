// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class PlayerControllerListingTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(get(PLAYER_URL))
                .andExpect(content().string(CoreMatchers.notNullValue()));
    }
}
