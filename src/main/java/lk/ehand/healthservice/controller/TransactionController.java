package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.DoctorScheduleGrid;
import lk.ehand.healthservice.domain.Transaction;
import lk.ehand.healthservice.repository.IDoctorScheduleGridRepository;
import lk.ehand.healthservice.repository.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
public class TransactionController {

    ITransactionRepository transactionRepository;
    IDoctorScheduleGridRepository doctorScheduleGridRepository;

    @Autowired
    public TransactionController(ITransactionRepository transactionRepository,
                                 IDoctorScheduleGridRepository doctorScheduleGridRepository){
        this.transactionRepository = transactionRepository;
        this.doctorScheduleGridRepository = doctorScheduleGridRepository;

    }

    @PostMapping(value = "/transaction/{doctorSeheduleGridId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> initTransaction(@PathVariable String doctorSeheduleGridId, @RequestBody Transaction transaction){
        Optional<DoctorScheduleGrid> doctorScheduleGrid= doctorScheduleGridRepository.findById(Long.parseLong(doctorSeheduleGridId));
        transaction.setDoctorScheduleGrid(doctorScheduleGrid.get());
        System.out.println(LocalDateTime.now().toString());
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz"
                + transaction.getMobileNo();
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        transaction.setRefNumber(sb.toString());

        return new ResponseEntity(transactionRepository.save(transaction),HttpStatus.CREATED);
    }

    @PutMapping(value ="/transaction/{transactionId}")
    public ResponseEntity<Transaction> updateTransction(@PathVariable String transactionId, @RequestBody Transaction transaction){
        log.info("PUT - Update Transaction {}",transaction);
        Transaction t = transactionRepository.findById(Long.parseLong(transactionId)).get();
        t.setStatus(transaction.getStatus());

        return ResponseEntity.ok().body(transactionRepository.save(t));
    }


}
