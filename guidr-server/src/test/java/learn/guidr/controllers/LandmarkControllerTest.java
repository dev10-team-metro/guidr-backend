package learn.guidr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import learn.guidr.models.Landmark;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LandmarkControllerTest {
    @MockBean
    LandmarkRepository repository;

    @Autowired
    MockMvc mvc;

    final String api = "/api/guidr/landmark";

    @Test
    void shouldFindAllReturning200() throws Exception {
        List<Landmark> expected = new ArrayList<>();
        expected.add(new Landmark());
        expected.add(new Landmark());

        when(repository.findAll()).thenReturn(expected);

        mvc.perform(get(api))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectToJson(expected)));
    }

    @Test
    void shouldFindByIdReturning200() throws Exception {
        Landmark expected = new Landmark(
                1,
                "Bronx Zoo",
                new BigDecimal("41.95"),
                "2300 Southern Boulevard",
                "Bronx",
                "NY",
                10460);

        when(repository.findById(1).thenReturn(expected));

        mvc.perform(get(api + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectToJson(expected)));
    }

    @Test
    void shouldNotFindMissingReturning404() throws Exception {
        mvc.perform(get(api + "/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddReturning201() throws Exception {
        Landmark landmark = new Landmark(
                0,
                "Bronx Zoo",
                new BigDecimal("41.95"),
                "2300 Southern Boulevard",
                "Bronx",
                "NY",
                10460);

        Landmark expected = new Landmark(
                1,
                "Bronx Zoo",
                new BigDecimal("41.95"),
                "2300 Southern Boulevard",
                "Bronx",
                "NY",
                10460);

        when(repository.add(any())).thenReturn(expected);

        var request = post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(landmark));

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectToJson(expected)));
    }

    @Test
    void shouldNotAddReturning400() throws Exception {
        Landmark landmark = new Landmark(
                1,
                "Bronx Zoo",
                new BigDecimal("41.95"),
                "2300 Southern Boulevard",
                "Bronx",
                "NY",
                10460);

        var request = post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(landmark));

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateReturning204() throws Exception {
        Landmark landmark = new Landmark(
                1,
                "Bronx Zoo",
                new BigDecimal("41.95"),
                "2300 Southern Boulevard",
                "Bronx",
                "NY",
                10460);

        when(repository.update(any())).thenReturn(true);

        var request = put(api + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(landmark));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);
    }
}