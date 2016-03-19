package gk.potoo.documents;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Wow implements Serializable {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String text;

    @CreatedBy
    @Getter
    @Setter
    private String creator;

    @Version
    @Getter
    @Setter
    private Long version;

    @CreatedDate
    @Getter
    @Setter
    private DateTime createdDate;

}
