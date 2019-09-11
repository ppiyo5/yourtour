package net.nigne.yourtour.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.exception.MaxOverReservationException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservations {

    private static final int LIMIT_RESERVATION_COUNT = 15;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            joinColumns = @JoinColumn(name = "shopping_basket_buyer_id")
    )
    private List<Reservation> reservations = new ArrayList<>();

    public Reservations(List<Reservation> reservations) {
        this.reservations = new ArrayList<>(reservations);
    }

    void remove(Long registeredBookId) {
        this.reservations = this.reservations.stream()
                .filter(reservation ->
                        !registeredBookId.equals(reservation.getRegisteredBookId())
                ).collect(Collectors.toList());
    }

    void add(Reservation reservation) {
        enableReservation();
        reservations.add(reservation);
    }

    public int size() {
        return reservations.size();
    }

    private void enableReservation() {
        if (reservations.size() >= LIMIT_RESERVATION_COUNT) {
            throw new MaxOverReservationException(String.format("최대 %s권을 찜할 수 있습니다.", LIMIT_RESERVATION_COUNT));
        }
    }

    public boolean exists(Long registeredBookId) {
        return this.reservations.stream()
                .anyMatch(reservation -> registeredBookId.equals(reservation.getRegisteredBookId()));
    }
}
