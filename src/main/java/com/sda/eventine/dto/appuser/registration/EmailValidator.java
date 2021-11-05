package com.sda.eventine.dto.appuser.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {


    //TODO: implement proper email validation using Apache Commons Validator or regex matcher
    @Override
    public boolean test(String email) {
        return true;
    }
}
