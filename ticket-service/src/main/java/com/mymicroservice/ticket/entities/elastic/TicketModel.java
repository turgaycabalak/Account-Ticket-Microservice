package com.mymicroservice.ticket.entities.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@Document(indexName = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {

    @Id
    private String id;
    private String description;
    private String notes;
    private String assigneeAccountNameSurname;
    private String priorityType;
    private String ticketStatus;
    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private LocalDate ticketDate;

}
