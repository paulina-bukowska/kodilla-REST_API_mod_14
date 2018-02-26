package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String toCc;

    public Optional getToCc() {
        return Optional.ofNullable(toCc);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mailTo='" + mailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", toCc='" + Optional.ofNullable(toCc).orElse("") + '\'' +
                '}';
    }
}
