package com.eremin.textquestservlets.service;

import com.eremin.textquestservlets.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eremin.textquestservlets.consts.Const.OBJECT_MAPPER;
import static com.eremin.textquestservlets.consts.Const.PATH_TO_JSON;


@Getter
@Slf4j
public class DataService {
    private Root data;

    public DataService(ServletContext context) {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(PATH_TO_JSON).toURI());
            Stream<String> streamLines = Files.lines(path);
            String lines = streamLines.collect(Collectors.joining("\n"));

            ObjectMapper om = (ObjectMapper) context.getAttribute(OBJECT_MAPPER);
            this.data = om.readValue(lines, Root.class);
            streamLines.close();
        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
