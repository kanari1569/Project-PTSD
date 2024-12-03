package com.ptsd.authservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    FREE(0),
    MINIMUM(1),
    STANDARD(2),
    PREMIUM(3);

    private final int roleNumber;


}
