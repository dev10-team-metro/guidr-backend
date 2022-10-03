//package learn.guidr.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import learn.guidr.data.CollectionRepository;
//import learn.guidr.models.SiteCollection;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;

//@WithMockUser(username = "admin", password = "admin", roles = {"ADMIN", "USER"})

//class SiteCollectionControllerTest {
//
//    @MockBean
//    CollectionRepository repository;
//
//    @Autowired
//    MockMvc mvc;
//
//    final String api = "/api/guidr/collection";
//
//    final SiteCollection TEST_COLLECTION = new SiteCollection(
//            1,
//            "NYC's Largest Parks",
//            "A Collection of NYC's Largest Parks");
//
//
//
//    @Test
//    void shouldFindAllReturning200() throws Exception {
//        List<SiteCollection> expected = new ArrayList<>();
//        expected.add(new SiteCollection());
//        expected.add(new SiteCollection());
//
//        when(repository.findAll()).thenReturn(expected);
//
//        mvc.perform(get(api))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectToJson(expected)));
//    }
//
//    @Test
//    void shouldFindByIdReturning200() throws Exception {
//        SiteCollection expected = TEST_COLLECTION;
//
//        when(repository.findById(1)).thenReturn(expected);
//
//        mvc.perform(get(api + "/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectToJson(expected)));
//    }
//
//    @Test
//    void shouldNotFindMissingReturning404() throws Exception {
//        mvc.perform(get(api + "/99"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void shouldAddReturning201() throws Exception {
//        SiteCollection siteCollection = new SiteCollection(
//                0,
//                "NYC's Largest Parks",
//                "A Collection of NYC's Largest Parks");;
//
//        SiteCollection expected = TEST_COLLECTION;
//
//        when(repository.create(any())).thenReturn(expected);
//
//        var request = post(api)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectToJson(siteCollection));
//
//        mvc.perform(request)
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectToJson(expected)));
//    }
//
//    @Test
//    void shouldNotAddReturning400() throws Exception {
//        var request = post(api)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectToJson(TEST_COLLECTION));
//
//        mvc.perform(request)
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldUpdateReturning204() throws Exception {
//        when(repository.update(any())).thenReturn(true);
//
//        var request = put(api + "/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectToJson(TEST_COLLECTION));
//
//        mvc.perform(request)
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void shouldNotUpdateReturning400() throws Exception {
//        SiteCollection siteCollection = new SiteCollection(
//                3,
//                "NYC's Largest Parks",
//                "A Collection of NYC's Largest Parks");
//
//        List<SiteCollection> all = new ArrayList<>();
//        all.add(TEST_COLLECTION);
//
//        when(repository.findAll()).thenReturn(all);
//
//        var request = put(api + "/3")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectToJson(siteCollection));
//
//        mvc.perform(request)
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldNotUpdateReturning409() throws Exception {
//        var request = put(api + "/4")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectToJson(TEST_COLLECTION));
//
//        mvc.perform(request)
//                .andExpect(status().isConflict());
//    }
//
//    @Test
//    void shouldDeleteReturning204() throws Exception {
//        when(repository.deleteById(1)).thenReturn(true);
//        when(repository.findById(1)).thenReturn(TEST_COLLECTION);
//
//        mvc.perform(delete(api + "/1"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void shouldNotDeleteReturning404() throws Exception {
//        mvc.perform(delete(api + "/2"))
//                .andExpect(status().isNotFound());
//    }
//
//    private String objectToJson(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return objectMapper.writeValueAsString(object);
//    }
//
//}
