package ru.pkozlov.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pkozlov.app.dao.domain.EmailData;
import ru.pkozlov.app.dao.repository.EmailDataRepository;
import ru.pkozlov.app.service.dto.PatchEmailDto;
import ru.pkozlov.app.service.exception.ValidationException;

@Component
@RequiredArgsConstructor
public class EmailDataComponent {
    private final EmailDataRepository emailDataRepository;

    @Transactional
    public EmailData createEmail(PatchEmailDto patchEmail) {
        var emailData = emailDataRepository.findByEmail(patchEmail.getEmail());

        if (emailData.isPresent()) {
            throw new ValidationException("Email already exists");
        } else {
            return emailDataRepository.save(
                    EmailData.builder()
                            .email(patchEmail.getEmail())
                            .build()
            );
        }
    }

    @Transactional
    public EmailData updateEmail(EmailData emailData, PatchEmailDto patchEmail) {
        emailDataRepository
                .findByEmail(patchEmail.getEmail())
                .ifPresent(email -> { throw new ValidationException("Email already exists"); });

        emailData.setEmail(patchEmail.getEmail());
        emailDataRepository.save(emailData);
        return emailData;
    }

    @Transactional
    public void deleteEmail(EmailData emailData) {
        emailDataRepository.delete(emailData);
    }
}
