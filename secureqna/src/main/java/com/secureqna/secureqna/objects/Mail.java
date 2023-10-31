package com.secureqna.secureqna.objects;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Mail {

    private String content;

    private String username;

    private String email;

    private String title;
}
