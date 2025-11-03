package net.haji.controllers;

import net.haji.dtos.ConferenceCreateDto;
import net.haji.dtos.ConferenceDto;
import net.haji.dtos.ConferenceUpdateDto;
import net.haji.dtos.KeynoteDto;
import net.haji.services.ConferenceService;
import net.haji.services.KeynoteServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private KeynoteServiceClient keynoteServiceClient;

    @PostMapping
    public ResponseEntity<ConferenceDto> createConference(@RequestBody ConferenceCreateDto dto) {
        ConferenceDto conference = conferenceService.createConference(dto);
        return ResponseEntity.ok(conference);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDto> getConferenceById(@PathVariable Long id) {
        ConferenceDto conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    @GetMapping
    public ResponseEntity<List<ConferenceDto>> getAllConferences() {
        List<ConferenceDto> conferences = conferenceService.getAllConferences();
        return ResponseEntity.ok(conferences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceDto> updateConference(@PathVariable Long id, @RequestBody ConferenceUpdateDto dto) {
        ConferenceDto conference = conferenceService.updateConference(id, dto);
        return ResponseEntity.ok(conference);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{conferenceId}/keynote/{keynoteId}")
    public ResponseEntity<KeynoteDto> getKeynoteForConference(@PathVariable Long conferenceId, @PathVariable Long keynoteId) {
        // Verify conference exists
        conferenceService.getConferenceById(conferenceId);

        // Get keynote information from keynote service
        KeynoteDto keynote = keynoteServiceClient.getKeynoteById(keynoteId);
        return ResponseEntity.ok(keynote);
    }
}