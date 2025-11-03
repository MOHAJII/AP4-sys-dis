package net.haji.controllers;

import net.haji.dtos.KeynoteCreateDto;
import net.haji.dtos.KeynoteDto;
import net.haji.dtos.KeynoteUpdateDto;
import net.haji.services.KeynoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
public class KeynoteController {

    @Autowired
    private KeynoteService keynoteService;

    @PostMapping
    public ResponseEntity<KeynoteDto> createKeynote(@RequestBody KeynoteCreateDto dto) {
        KeynoteDto keynote = keynoteService.createKeynote(dto);
        return ResponseEntity.ok(keynote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeynoteDto> getKeynoteById(@PathVariable Long id) {
        KeynoteDto keynote = keynoteService.getKeynoteById(id);
        return ResponseEntity.ok(keynote);
    }

    @GetMapping
    public ResponseEntity<List<KeynoteDto>> getAllKeynotes() {
        List<KeynoteDto> keynotes = keynoteService.getAllKeynotes();
        return ResponseEntity.ok(keynotes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeynoteDto> updateKeynote(@PathVariable Long id, @RequestBody KeynoteUpdateDto dto) {
        KeynoteDto keynote = keynoteService.updateKeynote(id, dto);
        return ResponseEntity.ok(keynote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeynote(@PathVariable Long id) {
        keynoteService.deleteKeynote(id);
        return ResponseEntity.noContent().build();
    }
}
