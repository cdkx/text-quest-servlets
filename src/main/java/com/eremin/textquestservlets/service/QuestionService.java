package com.eremin.textquestservlets.service;

import com.eremin.textquestservlets.model.Answer;
import com.eremin.textquestservlets.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eremin.textquestservlets.consts.Const.PATH_TO_JSON;

@Getter
@Slf4j
public class QuestionService {

    private Root data;

    public QuestionService() {

        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(PATH_TO_JSON).toURI());
            Stream<String> streamLines = Files.lines(path);
            String lines = streamLines.collect(Collectors.joining("\n"));
            this.data = new ObjectMapper().readValue(lines, Root.class);
            streamLines.close();
        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<Answer> getAnswersByQuestionId(Integer questionId) {
        return new ArrayList<>();
    }

}
