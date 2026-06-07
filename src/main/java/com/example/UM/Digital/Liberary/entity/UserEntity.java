package com.example.UM.Digital.Liberary.entity;

import com.example.UM.Digital.Liberary.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "subscription_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SubscriptionType subscriptionType = SubscriptionType.NOTSUBSCRIBED;
}
