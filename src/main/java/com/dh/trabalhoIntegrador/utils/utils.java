package com.dh.trabalhoIntegrador.utils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class utils {

    public static LocalDate convertLocalDate(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          LocalDate localDate = LocalDate.parse(data, formatter);
          return localDate;
    }
}
