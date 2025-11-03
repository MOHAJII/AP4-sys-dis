package net.haji.services;

import net.haji.dtos.ConferenceCreateDto;
import net.haji.dtos.ConferenceDto;
import net.haji.dtos.ConferenceUpdateDto;
import net.haji.entities.Conference;
import net.haji.repositories.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public ConferenceDto createConference(ConferenceCreateDto dto) {
        Conference conference = new Conference();
        conference.setTitle(dto.getTitle());
        conference.setType(Conference.ConferenceType.valueOf(dto.getType().toUpperCase()));
        conference.setDate(dto.getDate());
        conference.setDuration(dto.getDuration());
        conference.setRegisteredCount(dto.getRegisteredCount());
        conference.setScore(dto.getScore());

        Conference savedConference = conferenceRepository.save(conference);
        return convertToDto(savedConference);
    }

    @Override
    public ConferenceDto getConferenceById(Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        return convertToDto(conference);
    }

    @Override
    public List<ConferenceDto> getAllConferences() {
        return conferenceRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConferenceDto updateConference(Long id, ConferenceUpdateDto dto) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        if (dto.getTitle() != null) conference.setTitle(dto.getTitle());
        if (dto.getType() != null) conference.setType(Conference.ConferenceType.valueOf(dto.getType().toUpperCase()));
        if (dto.getDate() != null) conference.setDate(dto.getDate());
        if (dto.getDuration() > 0) conference.setDuration(dto.getDuration());
        if (dto.getRegisteredCount() >= 0) conference.setRegisteredCount(dto.getRegisteredCount());
        if (dto.getScore() >= 0) conference.setScore(dto.getScore());

        Conference updatedConference = conferenceRepository.save(conference);
        return convertToDto(updatedConference);
    }

    @Override
    public void deleteConference(Long id) {
        if (!conferenceRepository.existsById(id)) {
            throw new RuntimeException("Conference not found");
        }
        conferenceRepository.deleteById(id);
    }

    @Override
    public Conference getConferenceEntityById(Long id) {
        return conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
    }

    private ConferenceDto convertToDto(Conference conference) {
        return new ConferenceDto(
                conference.getId(),
                conference.getTitle(),
                conference.getType().toString(),
                conference.getDate(),
                conference.getDuration(),
                conference.getRegisteredCount(),
                conference.getScore()
        );
    }
}