package com.shulmin.pavel.russiangeographicalsociety.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionAndAnswer {
    private String question;
    private String answer;
}
