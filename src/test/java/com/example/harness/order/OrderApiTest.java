package com.example.harness.order;

import com.example.harness.HarnessApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = HarnessApplication.class)
@AutoConfigureMockMvc
class OrderApiTest {
    @Autowired private MockMvc mvc;
    @Test void createsAndReadsOrder() throws Exception {
        String response = mvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerId\":\"cust-1\",\"amount\":19.90}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.status").value("CREATED"))
                .andReturn().getResponse().getContentAsString();
        String id = response.replaceAll(".*\\\"id\\\":([0-9]+).*", "$1");
        mvc.perform(get("/api/orders/" + id)).andExpect(status().isOk()).andExpect(jsonPath("$.customerId").value("cust-1"));
    }
    @Test void rejectsInvalidOrder() throws Exception {
        mvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content("{\"customerId\":\"\",\"amount\":0}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("Invalid request"));
    }
}
