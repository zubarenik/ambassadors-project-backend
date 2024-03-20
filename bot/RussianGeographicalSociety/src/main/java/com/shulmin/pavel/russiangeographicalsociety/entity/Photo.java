package com.shulmin.pavel.russiangeographicalsociety.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Photos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "url")
    String uri;
    int ambassador;
}
