package net.haji.services;

import net.haji.dtos.KeynoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keynote-service")
public interface KeynoteServiceClient {

    @GetMapping("/api/keynotes/{id}")
    KeynoteDto getKeynoteById(@PathVariable("id") Long id);
}