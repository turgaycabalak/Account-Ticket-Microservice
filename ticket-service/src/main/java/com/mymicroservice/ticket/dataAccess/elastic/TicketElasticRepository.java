package com.mymicroservice.ticket.dataAccess.elastic;

import com.mymicroservice.ticket.entities.elastic.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel, String> {


}
