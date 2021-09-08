package dev.patika.homework.service;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.mappers.LoggerMapper;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Logger;
import dev.patika.homework.repository.LoggerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoggerService {

    private final LoggerDAO loggerDAO;
    private final LoggerMapper loggerMapper;

    public List<LoggerDTO> findByMessageOrTypeOrDate(String msg, String type, String date) {

        if (date == null) {
            List<LoggerDTO> loggerList = new ArrayList<>();
            List<Logger> loggers = loggerDAO.findByStatusCodeContaining(type);
            for (Logger logger : loggers) {
                LoggerDTO loggerDTO = loggerMapper.mapFromLoggertoLoggerDTO(logger);
                loggerList.add(loggerDTO);
            }
            return loggerList;

        } else {
            LocalDate localDate = LocalDate.parse(date);
            List<LoggerDTO> loggerList = new ArrayList<>();
            List<Logger> loggers = loggerDAO.findByStatusCodeContainingAndTimestampBetween(type, localDate.atStartOfDay(), localDate.plusDays(1).atStartOfDay());
            for (Logger logger : loggers) {
                LoggerDTO loggerDTO = loggerMapper.mapFromLoggertoLoggerDTO(logger);
                loggerList.add(loggerDTO);
            }
            return loggerList;
        }
    }
}
