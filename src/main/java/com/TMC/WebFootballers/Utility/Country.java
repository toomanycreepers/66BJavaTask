package com.TMC.WebFootballers.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Country {

    RUSSIA(0,"Россия"),
    USA(1, "США"),
    ITALY(2,"Италия");

    private final int Id;
    private final String ruName;

    public static String getRuNameById(int id){
        try{
        return Country.values()[id].getRuName();
        }
        catch (Exception e){
            return "Неизвестно";
        }
    }
}
