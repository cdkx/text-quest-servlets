package com.eremin.textquestservlets.model;

import lombok.Data;


@Data
public class Answer {
    private int id;
    private String name;
    private int nextQuestion;
}
