package net.haji.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceDto {
    private Long id;
    private String title;
    private String type;
    private LocalDateTime date;
    private int duration;
    private int registeredCount;
    private double score;
}