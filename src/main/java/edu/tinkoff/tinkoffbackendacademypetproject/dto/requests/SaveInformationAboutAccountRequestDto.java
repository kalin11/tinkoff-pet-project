package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import org.springframework.web.multipart.MultipartFile;

public record SaveInformationAboutAccountRequestDto(String nickname,
                                                    String description,
                                                    String firstName,
                                                    String lastName,
                                                    String middleName,
                                                    String birthDate,
                                                    MultipartFile photo) {
}
