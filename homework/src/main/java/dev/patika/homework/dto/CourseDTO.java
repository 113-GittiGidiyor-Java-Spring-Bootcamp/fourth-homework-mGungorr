package dev.patika.homework.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CourseDTO {

    private int id;
    private String courseName;
    private int courseCode;
    private int courseCreditPoint;
}
