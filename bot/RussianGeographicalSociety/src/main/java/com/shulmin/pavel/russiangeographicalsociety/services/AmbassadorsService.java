package com.shulmin.pavel.russiangeographicalsociety.services;

import com.shulmin.pavel.russiangeographicalsociety.models.QuestionAndAnswer;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface AmbassadorsService {
    Integer create(long chatId);
    void updateName(long chatId, String name);
    void updateAge(long chatId, int age);
    void updateSex(long chatId, String data);
    void updatePlaceBorn(long chatId, String placeBorn);
    void updatePlaceLive(long chatId, String placeLive);
    void updateSocials(long chatId, String socials);
    void updateType(long chatId, String type);
    void updateCategory(long chatId, String category);
    int getCategory(long chatId);
    void updateGeneralQuestions(long chatId, ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> questionsAndAnswers);
    void updateSpecialQuestions(long chatId, ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> questionsAndAnswers);

}
