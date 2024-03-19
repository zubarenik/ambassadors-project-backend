package com.shulmin.pavel.russiangeographicalsociety.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.ws.rs.DefaultValue;
import lombok.*;

@Entity
@Table(name = "chat_states")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BotStateEntity {
    @Id
    @Column(name = "chat_id")
    long chatId;
    String state = "START";
}
