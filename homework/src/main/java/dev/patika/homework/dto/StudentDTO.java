package dev.patika.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.homework.model.enumaration.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StudentDTO {

    private int id;
    private String studentName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date studentBirthDate;
    private String studentAdress;
    private Gender gender;
    private int age;
}
