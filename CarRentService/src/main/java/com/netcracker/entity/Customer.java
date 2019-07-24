package com.netcracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers",
uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})}
)
@Data
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "area_of_living", nullable = false)
    private String areaOfLiving;

    @Column(name = "discount", nullable = true)
    private Integer discount;

    @Column(name = "passport_number", nullable = false, unique = true)
    private Integer passportNumber;

    @Column(name = "phone_number", nullable = true)
    private Integer phoneNumber;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, targetEntity = Order.class)
    private List<Order> orders;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, targetEntity = RepairOrder.class)
    private List<RepairOrder> repairOrders;

    private String username;
    private String password;
    private int roleId;
    @ElementCollection(targetClass = Role.class)
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Role.class)
    private List<GrantedAuthority> authorities;
    @Transient
    private boolean accountNonExpired = true;
    @Transient
    private boolean accountNonLocked = true;
    @Transient
    private boolean credentialsNonExpired = true;
    @Transient
    private boolean enabled = true;

}
