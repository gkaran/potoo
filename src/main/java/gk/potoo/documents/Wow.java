package gk.potoo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Wow implements Serializable {

    @Id
    private String id;

    private String text;

    private String creator;

    @Version
    private Long version;

//    TODO: Fix this
//    @CreatedDate
//    private OffsetDateTime createdDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public OffsetDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(OffsetDateTime createdDate) {
//        this.createdDate = createdDate;
//    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
