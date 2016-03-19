package gk.potoo.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

@Document
@NoArgsConstructor
public class Account implements Serializable {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Indexed
    @NotBlank
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String[] roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private String password;

    @Version
    @Getter
    @Setter
    private Long version;

    @CreatedDate
    @Getter
    @Setter
    private DateTime createdDate;

    @LastModifiedDate
    @Getter
    @Setter
    private DateTime lastModifiedDate;

    public Account(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

}
