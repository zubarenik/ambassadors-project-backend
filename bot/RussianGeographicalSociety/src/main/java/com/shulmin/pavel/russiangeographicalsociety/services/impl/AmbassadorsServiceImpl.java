package com.shulmin.pavel.russiangeographicalsociety.services.impl;

import com.google.gson.Gson;
import com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas.TouristCategoryCallBackData;
import com.shulmin.pavel.russiangeographicalsociety.entity.AmbassadorEntity;
import com.shulmin.pavel.russiangeographicalsociety.models.QuestionAndAnswer;
import com.shulmin.pavel.russiangeographicalsociety.repositories.AmbassadorsRepository;
import com.shulmin.pavel.russiangeographicalsociety.services.AmbassadorsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas.SexButtonCallbackData.MAN;

@Service
public class AmbassadorsServiceImpl implements AmbassadorsService {
    private final AmbassadorsRepository ambassadorsRepository;

    public AmbassadorsServiceImpl(AmbassadorsRepository ambassadorsRepository) {
        this.ambassadorsRepository = ambassadorsRepository;
    }

    @Override
    public Integer create(long chatId) {
        AmbassadorEntity ambassadorEntity = new AmbassadorEntity();
        ambassadorEntity.setChatId(chatId);
        return ambassadorsRepository
                .findFirstByChatId(chatId)
                .orElseGet(() -> ambassadorsRepository.save(ambassadorEntity))
                .getId();
    }

    @Override
    public void updateName(long chatId, String name) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setFullName(name);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateAge(long chatId, int age) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setAge(age);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateSex(long chatId, String data) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        boolean sex = data.equals(MAN.getData());
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setSex(sex);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updatePlaceBorn(long chatId, String placeBorn) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setPlaceBorn(placeBorn);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updatePlaceLive(long chatId, String placeLive) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setPlaceLive(placeLive);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateSocials(long chatId, String socials) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setSocial(socials);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateType(long chatId, String type) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setTypeTourist(type);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateCategory(long chatId, String category) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        AmbassadorEntity ambassadorEntity;
        int categoryNumber = 0;
        switch (category) {
            case "научный путешественник" -> categoryNumber = 1;
            case "популяризатор" -> categoryNumber = 2;
            case "медиаперсона" -> categoryNumber = 3;
        }
        if (optional.isPresent()) {
            ambassadorEntity = optional.get();
            ambassadorEntity.setCategory(categoryNumber);
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int getCategory(long chatId) {
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        if (optional.isPresent()) {
            return optional.get().getCategory();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateGeneralQuestions(long chatId,
                                       ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> questionsAndAnswers) {
        Gson gson = new Gson();
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        if (optional.isPresent()) {
            AmbassadorEntity ambassadorEntity = optional.get();
            ambassadorEntity.setGeneralQuestions(gson.toJson(questionsAndAnswers.get(chatId)));
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateSpecialQuestions(long chatId,
                                       ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> questionsAndAnswers) {
        Gson gson = new Gson();
        Optional<AmbassadorEntity> optional = ambassadorsRepository.findFirstByChatId(chatId);
        if (optional.isPresent()) {
            AmbassadorEntity ambassadorEntity = optional.get();
            ambassadorEntity.setSpecialQuestions(gson.toJson(questionsAndAnswers.get(chatId)));
            ambassadorsRepository.save(ambassadorEntity);
        } else {
            throw new NoSuchElementException();
        }
    }
}
