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

import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoProduct;
import edu.depaul.cdm.se452.group2.inventory.nonrelational.data.NoProductRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class NoProductServiceTest {

    @Autowired
    private NoProductRepo noProductRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
	private ObjectMapper objectMapper;

    private static final String NO_PRODUCT_URL = "http://localhost:8080/api/noproducts";

    private static final String NO_PRODUCT_ID1 = "someProductId1";
    private static final String NO_PRODUCT_ID2 = "someProductId2";

    private static RequestPostProcessor mockLogin;

    @BeforeAll
    public static void setup() {
        mockLogin = SecurityMockMvcRequestPostProcessors.httpBasic("admin", "adminPassword");
    }

    @BeforeEach
    public void setupRepoData() {
        log.info("Setting up test");
        
        noProductRepo.deleteAll();
        
        NoProduct np1 = NoProduct.builder()
            .id(NO_PRODUCT_ID1)
            .name("someProductName1")
            .description("someDescription1")
            .price(10)
            .build();
        
            NoProduct np2 = NoProduct.builder()
            .id(NO_PRODUCT_ID2)
            .name("someProductName2")
            .description("someDescription2")
            .price(20)
            .build();

        noProductRepo.insert(np1);
        noProductRepo.insert(np2);
    }

    @Test
    public void testGet() throws Exception {
        ResultActions apiResponse = mockMvc.perform(MockMvcRequestBuilders.get(NO_PRODUCT_URL).with(mockLogin));
        int noProductCount = (int)noProductRepo.count();

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        apiResponse.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(noProductCount)));
    }

    @Test
    public void testPost() throws Exception {
        NoProduct np3 = NoProduct.builder()
        .id("someProductName3")
        .name("someProductName3")
        .description("someDescription3")
        .price(30)
        .build();
        String noProductJSONString = objectMapper.writeValueAsString(np3);
        int initialRepoCount = (int)noProductRepo.count();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(NO_PRODUCT_URL).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(noProductJSONString);
        ResultActions apiResponse = mockMvc.perform(request);

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialRepoCount + 1, noProductRepo.count());
    }

    @Test
    public void testPut() throws Exception {
        NoProduct originalNoProduct = noProductRepo.findById(NO_PRODUCT_ID1).orElse(new NoProduct());
        String originalName = originalNoProduct.getName();
        String originalDescription = originalNoProduct.getDescription();
        String originalId = originalNoProduct.getId();
        originalNoProduct.setName("freshProductName");
        String noProductJSONString = objectMapper.writeValueAsString(originalNoProduct);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(NO_PRODUCT_URL).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        request.contentType(MediaType.APPLICATION_JSON);
        request.content(noProductJSONString);
        ResultActions apiResponse = mockMvc.perform(request);
        NoProduct updatedNoProduct = noProductRepo.findById(NO_PRODUCT_ID1).orElse(new NoProduct());
        
        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(originalId, updatedNoProduct.getId());
        assertEquals(originalDescription, updatedNoProduct.getDescription());
        assertNotEquals(originalName, updatedNoProduct.getName());
    }

    @Test
    public void testDelete() throws Exception {
        int initialRepoCount = (int)noProductRepo.count();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(String.format("%s/%s", NO_PRODUCT_URL, NO_PRODUCT_ID1)).with(mockLogin).with(SecurityMockMvcRequestPostProcessors.csrf());
        ResultActions apiResponse = mockMvc.perform(request);
        int updatedRepoCount = (int)noProductRepo.count();

        apiResponse.andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialRepoCount - 1, updatedRepoCount);
    }
}
