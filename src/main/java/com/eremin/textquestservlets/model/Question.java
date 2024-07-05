package com.eremin.textquestservlets.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private int id;
    private String question;
    private List<Integer> answers;
    private boolean failed;
    private boolean success;
}
