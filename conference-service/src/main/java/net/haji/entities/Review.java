package net.haji.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String text;

    @Column(nullable = false)
    private int stars; // 1 to 5

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    // Custom constructor for creating reviews
    public Review(LocalDateTime date, String text, int stars, Conference conference) {
        this.date = date;
        this.text = text;
        this.stars = stars;
        this.conference = conference;
    }
}