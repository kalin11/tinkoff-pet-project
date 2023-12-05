package edu.tinkoff.tinkoffbackendacademypetproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile_picture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfilePictureEntity {
    @Id
    @Column(name = "account_id")
    private Long id;

    @Column(name = "photo_name_in_directory")
    private String photoNameInDirectory;

    @Column(name = "initial_photo_name")
    private String initialPhotoName;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
}
