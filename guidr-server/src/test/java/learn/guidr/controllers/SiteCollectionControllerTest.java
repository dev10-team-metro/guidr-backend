package learn.guidr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SiteCollectionControllerTest {

    @MockBean
    SiteCollectionRepository repository;

    @Autowired
    MockMvc mvc;

    final String api = "/api/guidr/collection";

    final SiteCollection

    @Test
    void shouldFindAllReturning200() throws Exception {
        List<Review> expected = new ArrayList<>();
        expected.add(new Review());
        expected.add(new Review());

        when(repository.findAll()).thenReturn(expected);

        mvc.perform(get(api))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectToJson(expected)));
    }

    @Test
    void shouldFindByIdReturning200() throws Exception {
        Review expected = TEST_REVIEW;

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
        Review review = new Review(0, "Test Review", new BigDecimal("9.99"), 5);

        Review expected = TEST_REVIEW;

        when(repository.add(any())).thenReturn(expected);

        var request = post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(review));

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectToJson(expected)));
    }

    @Test
    void shouldNotAddReturning400() throws Exception {
        Review review = TEST_REVIEW;

        var request = post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(review));

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateReturning204() throws Exception {
        when(repository.update(any())).thenReturn(true);

        var request = put(api + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(TEST_REVIEW));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotUpdateReturning400() throws Exception {
        Review review = new Review(3, "Test Review", new BigDecimal("9.99"), 5);

        List<Review> all = new ArrayList<>();
        all.add(TEST_REVIEW);

        when(repository.findAll()).thenReturn(all);

        var request = put(api + "/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(review));

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotUpdateReturning409() throws Exception {
        var request = put(api + "/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(TEST_REVIEW));

        mvc.perform(request)
                .andExpect(status().isConflict());
    }

    @Test
    void shouldDeleteReturning204() throws Exception {
        when(repository.deleteById(1)).thenReturn(true);
        when(repository.findById(1)).thenReturn(TEST_REVIEW);

        mvc.perform(delete(api + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotDeleteReturning404() throws Exception {
        mvc.perform(delete(api + "/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldNotDeleteReturning400() throws Exception {
        when(repository.deleteById(1)).thenReturn(false);
        when(repository.findById(1)).thenReturn(TEST_REVIEW);

        mvc.perform(delete(api + "/1"))
                .andExpect(status().isBadRequest());
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);
    }

}