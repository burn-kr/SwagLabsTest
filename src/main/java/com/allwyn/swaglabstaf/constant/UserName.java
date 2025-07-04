package com.allwyn.swaglabstaf.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserName {
    STANDARD_USER("standard_user"),
    LOCKED_OUT_USER("locked_out_user"),
    PROBLEM_USER("problem_user"),
    PERFORMANCE_GLITCH_USER("performance_glitch_user"),
    ERROR_USER("error_user"),
    VISUAL_USER("visual_user");

    private final String name;
}
