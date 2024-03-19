package com.destiny.project.framework.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum CallTypeEnum {
    MOCK("mock", "mock调用"),
    REAL("real", "真实调用");

    private String code;
    private String desc;

    public static CallTypeEnum valueOfCode(String code) {
        return Arrays.stream(CallTypeEnum.values()).filter(item -> item.getCode().equals(code))
                .findFirst().orElse(CallTypeEnum.REAL);
    }
}
