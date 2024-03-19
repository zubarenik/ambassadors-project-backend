package com.shulmin.pavel.russiangeographicalsociety.constant.button_callback_datas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TouristCategoryCallBackData {
    SCIENCE("научный путешественник"),
    POPULARIZER("популяризатор"),
    MEDIA("медиаперсона");

    private final String data;

}
