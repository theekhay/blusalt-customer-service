package blusalt.challenge.customerservice.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data

@Entity
@Table(indexes = {
        @Index( columnList = "firstName"),
        @Index( columnList = "lastName"),
        @Index( columnList = "email"),
        @Index( columnList = "phoneNumber"),
}
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, columnDefinition="varchar(20)" )
    private String firstName;

    @Column(nullable = false, columnDefinition="varchar(20)" )
    private String lastName;

    @Column()
    private String address;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column( nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
