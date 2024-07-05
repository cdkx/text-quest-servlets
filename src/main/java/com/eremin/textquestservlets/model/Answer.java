package com.eremin.textquestservlets.model;

import lombok.Data;

import java.util.List;

@Data
public class Answer {
    private int id;
    private String name;
    private List<Integer> question;
}
