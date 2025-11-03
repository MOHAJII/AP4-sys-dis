package net.haji.services;

import net.haji.dtos.ConferenceCreateDto;
import net.haji.dtos.ConferenceDto;
import net.haji.dtos.ConferenceUpdateDto;
import net.haji.entities.Conference;

import java.util.List;

public interface ConferenceService {
    ConferenceDto createConference(ConferenceCreateDto dto);
    ConferenceDto getConferenceById(Long id);
    List<ConferenceDto> getAllConferences();
    ConferenceDto updateConference(Long id, ConferenceUpdateDto dto);
    void deleteConference(Long id);
    Conference getConferenceEntityById(Long id);
}