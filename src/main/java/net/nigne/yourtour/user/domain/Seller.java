package net.nigne.yourtour.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {

    @Id
    private String id;

    @Embedded
    private User user;

    @Builder(builderMethodName = "createBuilder")
    private Seller(final String id, final String name, final String password, final String phoneNumber, final String email, final String address) {
        this.id = id;
        this.user = new User(name, password, phoneNumber, email, address);
    }

    public void update(String name, String password, String phoneNumber, String email, String address) {
        user.update(name, password, phoneNumber, email, address);
    }

}
