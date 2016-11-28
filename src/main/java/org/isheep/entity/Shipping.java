package org.isheep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.isheep.config.javax.validation.groups.JPAValidationGroup;
import org.isheep.entity.embeddable.Address;
import org.isheep.entity.embeddable.Name;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by raymo on 15/11/2016.
 */
@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer sender;

    @Valid
    @Embedded
    private Name recipientName;

    @Valid
    @Embedded
    private Address recipientAddress;

    @Valid
    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Parcel parcel;

    @NotNull(groups = JPAValidationGroup.class)
    @Min(0)
    private Float price;

    Shipping() {
    }

    public Shipping(final Customer sender, final Name recipientName, final Address recipientAddress, final Parcel parcel, final Float price) {
        this.sender = sender;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.parcel = parcel;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(final Customer sender) {
        this.sender = sender;
    }

    public Name getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(final Name recipientName) {
        this.recipientName = recipientName;
    }

    public Address getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(final Address recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public Parcel getParcel() {
        return this.parcel;
    }

    public void setParcel(final Parcel parcel) {
        this.parcel = parcel;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(final Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Shipping shipping = (Shipping) o;
        return Objects.equal(id, shipping.id) &&
                Objects.equal(sender, shipping.sender) &&
                Objects.equal(recipientName, shipping.recipientName) &&
                Objects.equal(recipientAddress, shipping.recipientAddress) &&
                Objects.equal(parcel, shipping.parcel) &&
                Objects.equal(price, shipping.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, sender, recipientName, recipientAddress, parcel, price);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("sender", sender)
                .add("recipientName", recipientName)
                .add("recipientAddress", recipientAddress)
                .add("parcel", parcel)
                .add("price", price)
                .toString();
    }
}
