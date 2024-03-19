package com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum SexButtonCallbackData {
    MAN("Men"),
    WOMAN("Woman");

    private final String data;
}
