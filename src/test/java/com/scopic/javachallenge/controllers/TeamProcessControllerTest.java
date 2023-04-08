// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class TeamProcessControllerTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {

        mvc.perform(
                post(TEAM_URL))
                .andExpect(content().string(Matchers.notNullValue()));
    }
}