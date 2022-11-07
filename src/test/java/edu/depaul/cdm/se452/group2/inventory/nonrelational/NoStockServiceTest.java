package edu.depaul.cdm.se452.group2.inventory.nonrelational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoStock;
import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoStockRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class NoStockServiceTest {
    
    @Autowired
    private NoStockRepo noStockRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
	private ObjectMapper objectMapper;

    private static final String NO_STOCK_URL = "http://localhost:8080/api/nostocks";

    private static final String NO_STOCK_ID1 = "someStockId1";
    private static final String NO_STOCK_ID2 = "someStockId2";

    private static RequestPostProcessor mockLogin;

    @BeforeAll
    public static void setup() {
        mockLogin = SecurityMockMvcRequestPostProcessors.httpBasic("admin", "adminPassword");
    }

    @BeforeEach
    public void setupRepoData() {
        log.info("Setting up test");

        noStockRepo.deleteAll();
        
        NoStock np1 = NoStock.builder()
            .id(NO_STOCK_ID1)
            .quantityAvailable(10)
            .build();
        
            NoStock np2 = NoStock.builder()
            .id(NO_STOCK_ID2)
            .quantityAvailable(20)
            .build();

        noStockRepo.insert(np1);
        noStockRepo.insert(np2);
    }

    @Test
    public void testGet() throws Exception {
        ResultActions apiResponse = mockMvc.perform(MockMvcRequestBuilders.get(NO_STOCK_URL).with(mockLogin));
        int noStockCount = (int)noStockRepo.count();

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        apiResponse.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(noStockCount)));
    }

    @Test
    public void testPost() throws Exception {
        NoStock np3 = NoStock.builder()
        .id("someStockName3")
        .quantityAvailable(30)
        .build();
        String noStockJSONString = objectMapper.writeValueAsString(np3);
        int initialRepoCount = (int)noStockRepo.count();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(NO_STOCK_URL).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(noStockJSONString);
        ResultActions apiResponse = mockMvc.perform(request);

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialRepoCount + 1, noStockRepo.count());
    }

    @Test
    public void testPut() throws Exception {
        NoStock originalNoStock = noStockRepo.findById(NO_STOCK_ID1).orElse(new NoStock());
        double originalQuantityAvailable = originalNoStock.getQuantityAvailable();
        String originalId = originalNoStock.getId();
        originalNoStock.setQuantityAvailable(100);
        String noStockJSONString = objectMapper.writeValueAsString(originalNoStock);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(NO_STOCK_URL).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(noStockJSONString);
        ResultActions apiResponse = mockMvc.perform(request);
        NoStock updatedNoStock = noStockRepo.findById(NO_STOCK_ID1).orElse(new NoStock());
        
        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(originalId, updatedNoStock.getId());
        assertNotEquals(originalQuantityAvailable, updatedNoStock.getQuantityAvailable());
    }

    @Test
    public void testDelete() throws Exception {
        int initialRepoCount = (int)noStockRepo.count();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(String.format("%s/%s", NO_STOCK_URL, NO_STOCK_ID1)).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        ResultActions apiResponse = mockMvc.perform(request);
        int updatedRepoCount = (int)noStockRepo.count();

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialRepoCount - 1, updatedRepoCount);
    }
}
