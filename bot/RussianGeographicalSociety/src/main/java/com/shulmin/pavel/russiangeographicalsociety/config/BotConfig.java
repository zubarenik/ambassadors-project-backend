package com.shulmin.pavel.russiangeographicalsociety.config;

import com.shulmin.pavel.russiangeographicalsociety.models.QuestionAndAnswer;
import com.shulmin.pavel.russiangeographicalsociety.services.PhotoService;
import com.shulmin.pavel.russiangeographicalsociety.telegram.MessageHandler;
import com.shulmin.pavel.russiangeographicalsociety.telegram.TelegramBot;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
@Getter
@PropertySource("classpath:application.yml")
public class BotConfig {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String botToken;

    @Bean
    public TelegramBot telegramBot(MessageHandler messageHandler, ConcurrentMap<Long, Integer> replyMarkupMessages, PhotoService photoService) {
        return new TelegramBot(botName, botToken, messageHandler, replyMarkupMessages, photoService);
    }

    @Bean
    public ConcurrentMap<Long, Integer> replyMarkupMessages() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public String botName() {
        return botName;
    }

    @Bean String botToken() {
        return botToken;
    }

    @Bean
    @Scope("prototype")
    public ConcurrentHashMap<Long, ArrayList<QuestionAndAnswer>> questions() {
        return new ConcurrentHashMap<>();
    }

}
