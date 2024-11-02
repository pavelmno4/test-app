package ru.pkozlov.app.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pkozlov.app.dao.domain.PhoneData;
import ru.pkozlov.app.dao.repository.PhoneDataRepository;
import ru.pkozlov.app.service.user.dto.PatchPhoneDto;
import ru.pkozlov.app.service.exception.ValidationException;

@Component
@RequiredArgsConstructor
public class PhoneDataComponent {
    private final PhoneDataRepository phoneDataRepository;

    @Transactional
    public PhoneData createPhone(PatchPhoneDto patchPhone) {
        var phoneData = phoneDataRepository.findByPhone(patchPhone.getPhone());

        if (phoneData.isPresent()) {
            throw new ValidationException("Phone already exists");
        } else {
            return phoneDataRepository.save(
                    PhoneData.builder()
                            .phone(patchPhone.getPhone())
                            .build()
            );
        }
    }

    @Transactional
    public PhoneData updatePhone(PhoneData phoneData, PatchPhoneDto patchPhone) {
        phoneDataRepository
                .findByPhone(patchPhone.getPhone())
                .ifPresent(email -> { throw new ValidationException("Phone already exists"); });

        phoneData.setPhone(patchPhone.getPhone());
        phoneDataRepository.save(phoneData);
        return phoneData;
    }

    @Transactional
    public void deletePhone(PhoneData phoneData) {
        phoneDataRepository.delete(phoneData);
    }
}
