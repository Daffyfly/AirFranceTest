package com.meritis.airfrance.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.meritis.airfrance.AirFranceAppController;
import com.meritis.airfrance.exceptions.UserNotAllowedException;
import com.meritis.airfrance.exceptions.UserNotFoundException;
import com.meritis.airfrance.model.AirFranceUser;
import com.meritis.airfrance.repository.AirFranceRepository;
import com.meritis.airfrance.repository.AirFranceRepositoryImpl;
import com.meritis.airfrance.service.AirFranceService;
import com.meritis.airfrance.service.AirFranceServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AirFranceAppControllerTest {

    @Autowired
    AirFranceAppController controller;
 
    @Autowired
    AirFranceService service;
 
    MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        try {
			mockMvc.perform(post(AirFranceAppController.CREATE_URL + "?name=Vincent&age=22&location=France&hobby=play"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Configuration
    static class Config {
        @Bean
        public AirFranceAppController getAirFranceAppController() {
            return new AirFranceAppController();
        }
 
        @Bean
        public AirFranceService getAirFranceService() {
            return new AirFranceServiceImpl();
        }
        
        @Bean 
        public AirFranceRepository getAirFranceRepository() {
        	return new AirFranceRepositoryImpl();
        }
    }
 
    @Test
    public void testAddUser() throws Exception {
        mockMvc.perform(post(AirFranceAppController.CREATE_URL + "?name=Vincent&age=22&location=France&hobby=play"))
        .andExpect(status().isOk());
    }
    
    @Test
    public void testAddUserWithNoHobby() throws Exception {
        mockMvc.perform(post(AirFranceAppController.CREATE_URL + "?name=Vincent&age=22&location=France"))
        .andExpect(status().isOk());
    }
    
    @Test
    public void testAddUserWithWrongAge() {
        assertThatThrownBy(() -> mockMvc.perform(post(AirFranceAppController.CREATE_URL + "?name=Vincent&age=17&location=France&hobby=play"))).hasCauseInstanceOf(UserNotAllowedException.class);
    }

    @Test
    public void testAddUserWithWrongLocation(){
    	 assertThatThrownBy(() -> mockMvc.perform(post(AirFranceAppController.CREATE_URL + "?name=Vincent&age=22&location=Italy&hobby=play"))).hasCauseInstanceOf(UserNotAllowedException.class);
    }
    
    @Test
    public void testAddUserWithNoName() throws Exception {
        mockMvc.perform(post(AirFranceAppController.CREATE_URL + "?age=22&location=France&hobby=play"))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void findUserExisting() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(AirFranceAppController.FIND_URL, 1))
                .andExpect(status().isOk()).andReturn();
        
        AirFranceUser expected = new AirFranceUser(1, "Vincent", 22, "France", "play");
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println(expected.toString());
        Assert.assertEquals("Wrong result String not correct", expected.toString(), mvcResult.getResponse().getContentAsString());
    }
    
    @Test
    public void findUserNotExisting(){
    	assertThatThrownBy(() -> mockMvc.perform(get(AirFranceAppController.FIND_URL, 10))).hasCauseInstanceOf(UserNotFoundException.class);
    }
}
 
