package com.backend.backend.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "inputs")
public class Text {

    @Id
    private String id;
    private ArrayList<String> input;
    private String output;
    private String time;
}
