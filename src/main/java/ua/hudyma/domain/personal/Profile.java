package ua.hudyma.domain.personal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.domain.BaseEntity;
import ua.hudyma.enums.Sex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Data
public class Profile implements BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    private LocalDateTime registeredOn;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @ElementCollection
    @CollectionTable(
            name = "user_phones",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Phone> phoneList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_emails",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Email> emailList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Address> addressList = new ArrayList<>();
}




