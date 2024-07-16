package com.eremin.textquestservlets.model;

import lombok.Data;

import java.util.List;

@Data
public class Root {
    private List<Question> questions;
    private List<Answer> answers;
}
