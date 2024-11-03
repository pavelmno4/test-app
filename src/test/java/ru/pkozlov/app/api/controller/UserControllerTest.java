package ru.pkozlov.app.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import ru.pkozlov.app.AppApplicationTests;
import ru.pkozlov.app.service.user.dto.SearchResponseDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("Gennady")
public class UserControllerTest extends AppApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void searchUserByName() throws Exception {
        var result = mvc.perform(get("/v1/user").param("name", "Bo"))
                .andExpect(status().isOk())
                .andReturn();

        var searchResponse = objectMapper.readValue(result.getResponse().getContentAsString(), SearchResponseDto.class);

        Assertions.assertThat(searchResponse.getContent()).hasSize(1);
        Assertions.assertThat(searchResponse.getContent().get(0).getId()).isEqualTo(2L);
        Assertions.assertThat(searchResponse.getContent().get(0).getName()).isEqualTo("Boris");
    }
}
