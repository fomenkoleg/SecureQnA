package com.secureqna.secureqna.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    public Answers(String content) {
        this.content = content;
    }





}
