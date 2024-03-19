package com.shulmin.pavel.russiangeographicalsociety.telegram;

import com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas.TouristCategoryCallBackData;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas.SexButtonCallbackData.*;
import static com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas.TouristCategoryCallBackData.*;

public class KeyBoardMaker {
    public static InlineKeyboardMarkup chooseSex() {
        InlineKeyboardButton menButton = InlineKeyboardButton.builder()
                .text("М")
                .callbackData(MAN.getData())
                .build();
        InlineKeyboardButton womenButton = InlineKeyboardButton.builder()
                .text("Ж")
                .callbackData(WOMAN.getData())
                .build();
        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(List.of(menButton, womenButton)))
                .build();
    }

    public static InlineKeyboardMarkup chooseCategory() {
        InlineKeyboardButton scienceButton = InlineKeyboardButton.builder()
                .text(SCIENCE.getData())
                .callbackData(SCIENCE.getData())
                .build();
        InlineKeyboardButton popularizerButton = InlineKeyboardButton.builder()
                .text(POPULARIZER.getData())
                .callbackData(POPULARIZER.getData())
                .build();
        InlineKeyboardButton mediaButton = InlineKeyboardButton.builder()
                .text(MEDIA.getData())
                .callbackData(MEDIA.getData())
                .build();
        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(List.of(scienceButton), List.of(popularizerButton), List.of(mediaButton)))
                .build();
    }
}
