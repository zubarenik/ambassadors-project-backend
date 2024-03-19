package com.shulmin.pavel.russiangeographicalsociety.telegram;

import com.shulmin.pavel.russiangeographicalsociety.constant.BotAnswers;
import com.shulmin.pavel.russiangeographicalsociety.models.QuestionAndAnswer;
import com.shulmin.pavel.russiangeographicalsociety.services.AmbassadorsService;
import com.shulmin.pavel.russiangeographicalsociety.services.BotStatesService;
import com.shulmin.pavel.russiangeographicalsociety.services.PhotoService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.shulmin.pavel.russiangeographicalsociety.constant.BotAnswers.*;
import static com.shulmin.pavel.russiangeographicalsociety.constant.BotStates.*;

@Component
public class MessageHandler {
    private final AmbassadorsService ambassadorsService;
    private final BotStatesService botStatesService;
    private final PhotoService photoService;
    private final ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> generalQuestions;
    private final ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> specialQuestions;

    public MessageHandler(AmbassadorsService ambassadorsService,
                          BotStatesService botStatesService, PhotoService photoService,
                          ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> generalQuestions,
                          ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> specialQuestions) {
        this.ambassadorsService = ambassadorsService;
        this.botStatesService = botStatesService;
        this.photoService = photoService;
        this.generalQuestions = generalQuestions;
        this.specialQuestions = specialQuestions;
    }

    public SendMessage answerMessage(Update update) throws TelegramApiException {
        if (update.hasMessage() && update.getMessage() != null && update.getMessage().hasText()) {
            return answerText(update.getMessage());
        } else if (update.hasCallbackQuery() && update.getCallbackQuery() != null) {
            return answerCallbackQuery(update.getCallbackQuery());
        } else if (update.hasMessage() && update.getMessage() != null && update.getMessage().hasPhoto()) {
            return answerPhoto(update.getMessage());
        } else {
            return null;
        }
    }

    private SendMessage answerText(Message message) {
        Long chatId = message.getChatId();
        String messageText = message.getText();

        if (messageText.equals("/start")) {
            ambassadorsService.create(chatId);
            botStatesService.create(chatId);
        }

        switch (botStatesService.getState(chatId)) {
            case START -> {
                return startAndAskName(chatId);
            }
            case NAME_RECEIVED -> {
                return askAge(chatId, messageText);
            }
            case AGE_RECEIVED -> {
                return checkAge(chatId, messageText) == null ? askSex(chatId, messageText) : checkAge(chatId, messageText);
            }
            case PLACE_BORN_RECEIVED -> {
                return askPlaceLive(chatId, messageText);
            }
            case PLACE_LIVE_RECEIVED -> {
                return askSocials(chatId, messageText);
            }
            case SOCIAL_RECEIVED -> {
                return askType(chatId, messageText);
            }
            case TYPE_RECEIVED -> {
                return askCategory(chatId, messageText);
            }
            case FIRST_GENERAL_ANSWER_RECEIVED -> {
                return askSecondGeneralQuestion(chatId, messageText);
            }
            case SECOND_GENERAL_ANSWER_RECEIVED -> {
                return askThirdGeneralQuestion(chatId, messageText);
            }
            case THIRD_GENERAL_ANSWER_RECEIVED -> {
                return askFistSpecialQuestion(chatId, messageText);
            }
            case FIRST_ANSWER_RECEIVED -> {
                return askSecondSpecialQuestion(chatId, messageText);
            }
            case SECOND_ANSWER_RECEIVED -> {
                return askThirdSpecialQuestion(chatId, messageText);
            }
            case THIRD_ANSWER_RECEIVED, PHOTOS_RECEIVED -> {
                return askPhotos(chatId, messageText);
            }
            case FINAL -> {
                return refillMessage(chatId);
            }
            default -> {
                return errorCallbackAnswer(chatId);
            }
        }
    }

    private SendMessage answerCallbackQuery(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getMessage().getChatId();
        String data = callbackQuery.getData();

        switch (botStatesService.getState(chatId)) {
            case SEX_RECEIVED -> {
                return askPlaceBorn(chatId, data);
            }
            case CATEGORY_RECEIVED -> {
                return askFirstGeneralQuestion(chatId, data);
            }
            case FINAL -> {
                return refillMessage(chatId);
            }
            default -> {
                return errorAnswer(chatId);
            }
        }
    }

    private SendMessage answerPhoto(Message message) {
        if (botStatesService.getState(message.getChatId()) == PHOTOS_RECEIVED) {
            return finalMessage(message.getChatId());
        }
        if (botStatesService.getState(message.getChatId()) == FINAL) {
            return refillMessage(message.getChatId());
        }
        return errorAnswer(message.getChatId());
    }

    private SendMessage startAndAskName(Long chatId) {
        botStatesService.updateState(chatId, NAME_RECEIVED);
        return SendMessage.builder()
                .chatId(chatId)
                .text(START_MESSAGE.GetMessage() + "\n\n" + ASK_NAME.GetMessage())
                .build();
    }

    private SendMessage askAge(Long chatId, String messageText) {
        botStatesService.updateState(chatId, AGE_RECEIVED);
        ambassadorsService.updateName(chatId, messageText);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_AGE.GetMessage())
                .build();
    }

    private SendMessage checkAge(Long chatId, String messageText) {
        int age;
        try {
            age = Integer.parseInt(messageText);
            if (age < 0) {
                throw new NumberFormatException();
            }
            ambassadorsService.updateAge(chatId, age);
            return null;
        } catch (NumberFormatException e) {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(WRONG_AGE.GetMessage() + "\n\n" + ASK_AGE.GetMessage())
                    .build();
        }
    }

    private SendMessage askSex(Long chatId, String messageText) {
        botStatesService.updateState(chatId, SEX_RECEIVED);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_SEX.GetMessage())
                .replyMarkup(KeyBoardMaker.chooseSex())
                .build();
    }

    private SendMessage askPlaceBorn(Long chatId, String data) {
        botStatesService.updateState(chatId, PLACE_BORN_RECEIVED);
        ambassadorsService.updateSex(chatId, data);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_PLACE_BORN.GetMessage())
                .build();
    }

    private SendMessage askPlaceLive(Long chatId, String messageText) {
        botStatesService.updateState(chatId, PLACE_LIVE_RECEIVED);
        ambassadorsService.updatePlaceBorn(chatId, messageText);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_PLACE_LIVE.GetMessage())
                .build();
    }

    private SendMessage askSocials(Long chatId, String messageText) {
        botStatesService.updateState(chatId, SOCIAL_RECEIVED);
        ambassadorsService.updatePlaceLive(chatId, messageText);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_SOCIALS.GetMessage())
                .build();
    }

    private SendMessage askType(Long chatId, String messageText) {
        botStatesService.updateState(chatId, TYPE_RECEIVED);
        ambassadorsService.updateSocials(chatId, messageText);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_TYPE.GetMessage())
                .build();
    }

    private SendMessage askCategory(Long chatId, String messageText) {
        botStatesService.updateState(chatId, CATEGORY_RECEIVED);
        ambassadorsService.updateType(chatId, messageText);
        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_CATEGORY.GetMessage())
                .replyMarkup(KeyBoardMaker.chooseCategory())
                .build();
    }

    private SendMessage askFirstGeneralQuestion(Long chatId, String data) {
        botStatesService.updateState(chatId, FIRST_GENERAL_ANSWER_RECEIVED);
        ambassadorsService.updateCategory(chatId, data);
        return SendMessage.builder()
                .chatId(chatId)
                .text(FIRST_GENERAL_QUESTION.GetMessage())
                .build();
    }

    private SendMessage askSecondGeneralQuestion(Long chatId, String messageText) {
        botStatesService.updateState(chatId, SECOND_GENERAL_ANSWER_RECEIVED);
        generalQuestions.put(chatId, new ArrayList<>(List.of(new QuestionAndAnswer(FIRST_GENERAL_QUESTION.GetMessage(), messageText))));
        return SendMessage.builder()
                .chatId(chatId)
                .text(SECOND_GENERAL_QUESTION.GetMessage())
                .build();
    }

    private SendMessage askThirdGeneralQuestion(Long chatId, String messageText) {
        botStatesService.updateState(chatId, THIRD_GENERAL_ANSWER_RECEIVED);
        generalQuestions.get(chatId).add(new QuestionAndAnswer(SECOND_GENERAL_QUESTION.GetMessage(), messageText));
        return SendMessage.builder()
                .chatId(chatId)
                .text(THIRD_GENERAL_QUESTION.GetMessage())
                .build();
    }

    private SendMessage askFistSpecialQuestion(Long chatId, String messageText) {
        botStatesService.updateState(chatId, FIRST_ANSWER_RECEIVED);
        generalQuestions.get(chatId).add(new QuestionAndAnswer(THIRD_GENERAL_QUESTION.GetMessage(), messageText));
        ambassadorsService.updateGeneralQuestions(chatId, generalQuestions);

        int category = ambassadorsService.getCategory(chatId);
        String question = getSpecialQuestion(category, 1);

        return SendMessage.builder()
                .chatId(chatId)
                .text(question)
                .build();
    }

    private SendMessage askSecondSpecialQuestion(Long chatId, String messageText) {
        botStatesService.updateState(chatId, SECOND_ANSWER_RECEIVED);
        int category = ambassadorsService.getCategory(chatId);
        specialQuestions.put(chatId, new ArrayList<>(List.of(new QuestionAndAnswer(getSpecialQuestion(category, 1), messageText))));

        String question = getSpecialQuestion(category, 2);

        return SendMessage.builder()
                .chatId(chatId)
                .text(question)
                .build();
    }

    private SendMessage askThirdSpecialQuestion(Long chatId, String messageText) {
        botStatesService.updateState(chatId, THIRD_ANSWER_RECEIVED);
        int category = ambassadorsService.getCategory(chatId);
        specialQuestions.get(chatId).add(new QuestionAndAnswer(getSpecialQuestion(category, 2), messageText));

        String question = getSpecialQuestion(category, 3);

        return SendMessage.builder()
                .chatId(chatId)
                .text(question)
                .build();
    }

    private SendMessage askPhotos(Long chatId, String messageText) {
        botStatesService.updateState(chatId, PHOTOS_RECEIVED);
        if (specialQuestions.containsKey(chatId) && specialQuestions.get(chatId).size() >= 2) {
            int category = ambassadorsService.getCategory(chatId);
            specialQuestions.get(chatId).add(new QuestionAndAnswer(getSpecialQuestion(category, 3), messageText));
            ambassadorsService.updateSpecialQuestions(chatId, specialQuestions);
            specialQuestions.get(chatId).clear();
        }

        return SendMessage.builder()
                .chatId(chatId)
                .text(ASK_PHOTOS.GetMessage())
                .build();
    }

    private SendMessage finalMessage(Long chatId) {
        botStatesService.updateState(chatId, FINAL);

        return SendMessage.builder()
                .chatId(chatId)
                .text(FINAL_MESSAGE.GetMessage() + " " + REFILL.GetMessage())
                .build();
    }

    private SendMessage refillMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(REFILL.GetMessage())
                .build();
    }

    private SendMessage errorAnswer(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(ERROR_MESSAGE.GetMessage())
                .build();
    }

    private SendMessage errorCallbackAnswer(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(NO_CALLBACK_IN_ANSWER.GetMessage())
                .build();
    }

    private String getSpecialQuestion(int category, int questionNumber) {
        return BotAnswers.values()[THIRD_GENERAL_QUESTION.ordinal() + questionNumber + (category - 1) * 3].GetMessage();
    }
}
