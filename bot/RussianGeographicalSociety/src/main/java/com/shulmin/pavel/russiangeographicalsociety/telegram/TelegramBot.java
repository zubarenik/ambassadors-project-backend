package com.shulmin.pavel.russiangeographicalsociety.telegram;

import com.shulmin.pavel.russiangeographicalsociety.services.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final String botName;
    private final MessageHandler messageHandler;

    private final ConcurrentMap<Long, Integer> replyMarkupMessages;

    private final PhotoService photoService;
    private final ConcurrentHashMap<Long, Integer> photoCounter = new ConcurrentHashMap<>();

    public TelegramBot(String botName,
                       String botToken,
                       MessageHandler messageHandler,
                       ConcurrentMap<Long, Integer> replyMarkupMessages,
                       PhotoService photoService) {
        super(botToken);
        this.botName = botName;
        this.messageHandler = messageHandler;
        this.replyMarkupMessages = replyMarkupMessages;
        this.photoService = photoService;
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {
            if (update.hasCallbackQuery() && replyMarkupMessages.containsKey(update.getCallbackQuery().getMessage().getChatId())) {
                execute(EditMessageReplyMarkup.builder()
                        .chatId(update.getCallbackQuery().getMessage().getChatId())
                        .messageId(replyMarkupMessages.get(update.getCallbackQuery().getMessage().getChatId()))
                        .replyMarkup(null)
                        .build());
            }
            if (update.hasMessage() && update.getMessage() != null && (update.getMessage().hasPhoto() || update.getMessage().hasDocument())) {
                photoService.processPhoto(update.getMessage());
                photoCounter.putIfAbsent(update.getMessage().getChatId(), 0);
                Integer i = photoCounter.get(update.getMessage().getChatId());
                photoCounter.put(update.getMessage().getChatId(), i + 1);
                if (photoCounter.get(update.getMessage().getChatId()) < 3) {
                    return;
                }
            }
            Message message = execute(messageHandler.answerMessage(update));
            if (message.hasReplyMarkup()) {
                replyMarkupMessages.put(message.getChatId(), message.getMessageId());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
