package learn.guidr.domain;

import learn.guidr.data.LandmarkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LandmarkServiceTest {

    @Autowired
    LandmarkService service;

    @MockBean
    LandmarkRepository landmarkRepository;


}
