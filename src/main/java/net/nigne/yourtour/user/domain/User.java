package net.nigne.yourtour.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.common.domain.Address;
import net.nigne.yourtour.common.domain.Email;
import net.nigne.yourtour.common.domain.Name;
import net.nigne.yourtour.common.domain.PhoneNumber;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_LOGIN_ID", columnNames = {"loginId"}))
public class User {

    private String password;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Email email;

    @Embedded
    private Address address;

    @Builder
    public User(String name, String password, String phoneNumber, String email, String address) {
        this.password = password;
        this.name = new Name(name);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.email = new Email(email);
        this.address = new Address(address);
    }

    public void update(String name, String password, String phoneNumber, String email, String address) {
        this.name = new Name(name);
        this.password = password;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.email = new Email(email);
        this.address = new Address(address);
    }
}
