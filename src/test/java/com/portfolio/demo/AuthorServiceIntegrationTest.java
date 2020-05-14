package com.portfolio.demo;

import com.portfolio.demo.model.Author;
import com.portfolio.demo.service.AuthorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
public class AuthorServiceIntegrationTest extends SingletonTestDB {

    @Autowired
    private AuthorService authorService;

    @Test
    public void addAuthor() {

        // arrange
        UUID id = UUID.randomUUID();
        String name = "testname";
        String title = "testtitle";
        List<String> list = IntStream.range(1, 6).mapToObj(value -> String.format("testfield%s", value)).collect(Collectors.toList());
        Author testData = new Author(id, name, title, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));

        // act
        int actual = authorService.addAuthor(testData);
        int expected = 1;

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAuthor() {
        // act
        Author actual = authorService.getAuthor().get();
        Object expected = Author.class;

        // assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
