package de.telekom.sea.mystuff.frontend.android.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Item {

    private Long Id;
    private String name;
    private int amount;
    private String location;
    private String description;
    private Date lastUsed;
    private byte[] photo;

}

