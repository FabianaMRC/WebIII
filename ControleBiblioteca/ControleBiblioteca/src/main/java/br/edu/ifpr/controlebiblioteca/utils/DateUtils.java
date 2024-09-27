package br.edu.ifpr.controlebiblioteca.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String localDateToBrazilianFormat(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return formatter.format(date);
    }
}
