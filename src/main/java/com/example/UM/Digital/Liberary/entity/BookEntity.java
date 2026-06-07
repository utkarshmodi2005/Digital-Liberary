package com.example.UM.Digital.Liberary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "Books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

}
