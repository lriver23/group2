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

import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoSeller;
import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoSellerRepo;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@SpringBootTest
@AutoConfigureMockMvc
public class NoSellerServiceTest {
    
    @Autowired
    private NoSellerRepo noSellerRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
	private ObjectMapper objectMapper;

    private static final String NO_SELLER_URL = "http://localhost:8080/api/nosellers";

    private static final String NO_SELLER_ID1 = "someSellerId1";
    private static final String NO_SELLER_ID2 = "someSellerId2";

    private static RequestPostProcessor mockLogin;

    @BeforeAll
    public static void setup() {
        mockLogin = SecurityMockMvcRequestPostProcessors.httpBasic("admin", "adminPassword");
    }

    @BeforeEach
    public void setupRepoData() {
        noSellerRepo.deleteAll();
        
        NoSeller np1 = NoSeller.builder()
            .id(NO_SELLER_ID1)
            .name("someSellerName1")
            .income(10)
            .build();
        
            NoSeller np2 = NoSeller.builder()
            .id(NO_SELLER_ID2)
            .name("someSellerName2")
            .income(20)
            .build();

        noSellerRepo.insert(np1);
        noSellerRepo.insert(np2);
    }

    @Test
    public void testGet() throws Exception {
        ResultActions apiResponse = mockMvc.perform(MockMvcRequestBuilders.get(NO_SELLER_URL).with(mockLogin));
        int noSellerCount = (int)noSellerRepo.count();

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        apiResponse.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(noSellerCount)));
    }

    @Test
    public void testPost() throws Exception {
        NoSeller np3 = NoSeller.builder()
        .id("someSellerName3")
        .name("someSellerName3")
        .income(30)
        .build();
        String noSellerJSONString = objectMapper.writeValueAsString(np3);
        int initialRepoCount = (int)noSellerRepo.count();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(NO_SELLER_URL).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(noSellerJSONString);
        ResultActions apiResponse = mockMvc.perform(request);

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialRepoCount + 1, noSellerRepo.count());
    }

    @Test
    public void testPut() throws Exception {
        NoSeller originalNoSeller = noSellerRepo.findById(NO_SELLER_ID1).orElse(new NoSeller());
        String originalName = originalNoSeller.getName();
        double originalIncome = originalNoSeller.getIncome();
        String originalId = originalNoSeller.getId();
        originalNoSeller.setName("freshSellerName");
        String noSellerJSONString = objectMapper.writeValueAsString(originalNoSeller);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(NO_SELLER_URL).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(noSellerJSONString);
        ResultActions apiResponse = mockMvc.perform(request);
        NoSeller updatedNoSeller = noSellerRepo.findById(NO_SELLER_ID1).orElse(new NoSeller());
        
        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(originalId, updatedNoSeller.getId());
        assertEquals(originalIncome, updatedNoSeller.getIncome());
        assertNotEquals(originalName, updatedNoSeller.getName());
    }

    @Test
    public void testDelete() throws Exception {
        int initialRepoCount = (int)noSellerRepo.count();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(String.format("%s/%s", NO_SELLER_URL, NO_SELLER_ID1)).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        ResultActions apiResponse = mockMvc.perform(request);
        int updatedRepoCount = (int)noSellerRepo.count();

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialRepoCount - 1, updatedRepoCount);
    }
}
