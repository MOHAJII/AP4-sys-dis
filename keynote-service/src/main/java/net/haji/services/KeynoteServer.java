package net.haji.services;

import net.haji.dtos.KeynoteCreateDto;
import net.haji.dtos.KeynoteDto;
import net.haji.dtos.KeynoteUpdateDto;
import net.haji.entities.Keynote;
import net.haji.repositories.KeynoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeynoteService {

    @Autowired
    private KeynoteRepository keynoteRepository;

    public KeynoteDto createKeynote(KeynoteCreateDto dto) {
        Keynote keynote = new Keynote();
        keynote.setNom(dto.getNom());
        keynote.setPrenom(dto.getPrenom());
        keynote.setEmail(dto.getEmail());
        keynote.setFonction(dto.getFonction());

        Keynote savedKeynote = keynoteRepository.save(keynote);
        return convertToDto(savedKeynote);
    }

    public KeynoteDto getKeynoteById(Long id) {
        Keynote keynote = keynoteRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Keynote not found"));
        return convertToDto(keynote);
    }

    public List<KeynoteDto> getAllKeynotes() {
        return keynoteRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public KeynoteDto updateKeynote(Long id, KeynoteUpdateDto dto) {
        Keynote keynote = keynoteRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Keynote not found"));

        if (dto.getNom() != null) keynote.setNom(dto.getNom());
        if (dto.getPrenom() != null) keynote.setPrenom(dto.getPrenom());
        if (dto.getEmail() != null) keynote.setEmail(dto.getEmail());
        if (dto.getFonction() != null) keynote.setFonction(dto.getFonction());

        Keynote updatedKeynote = keynoteRepository.save(keynote);
        return convertToDto(updatedKeynote);
    }

    public void deleteKeynote(Long id) {
        if (!keynoteRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Keynote not found");
        }
        keynoteRepository.deleteById(Math.toIntExact(id));
    }

    public Keynote getKeynoteEntityById(Long id) {
        return keynoteRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Keynote not found"));
    }

    private KeynoteDto convertToDto(Keynote keynote) {
        return new KeynoteDto(
                keynote.getId(),
                keynote.getNom(),
                keynote.getPrenom(),
                keynote.getEmail(),
                keynote.getFonction()
        );
    }
}
