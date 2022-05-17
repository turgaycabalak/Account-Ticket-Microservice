package com.mymicroservice.ticket.api.controller;

import com.mymicroservice.ticket.business.TicketService;
import com.mymicroservice.ticket.eDto.TicketCreateRequest;
import com.mymicroservice.ticket.eDto.TicketUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tickets")
public record TickerController(TicketService ticketService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketCreateRequest request) {
        ticketService.createTicket(request);
    }

    @PutMapping
    public ResponseEntity<?> updateTicket(@RequestParam("id") String tickerId,
                                          @RequestBody TicketUpdateRequest updateRequest) {
        return ResponseEntity.ok(ticketService.updateTicket(tickerId, updateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable("id") String ticketId) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllTickets(Pageable pageable) {
        return ResponseEntity.ok(ticketService.getAllTickets(pageable));
    }

}
