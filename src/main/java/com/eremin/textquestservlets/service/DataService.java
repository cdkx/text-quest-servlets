package com.eremin.textquestservlets.service;

import com.eremin.textquestservlets.model.Root;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.eremin.textquestservlets.consts.Const.*;


@Getter
public class DataService {
    private final Root data;

    @SneakyThrows
    public DataService(ObjectMapper objectMapper) {
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(PATH_TO_JSON)).toURI());
        String allLines = String.join(NEW_LINE_CHARACTER, Files.readAllLines(path));
        this.data = objectMapper.readValue(allLines, Root.class);
    }
}
