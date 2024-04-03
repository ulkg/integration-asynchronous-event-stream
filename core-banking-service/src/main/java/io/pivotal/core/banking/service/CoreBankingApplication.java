package io.pivotal.core.banking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CoreBankingApplication {

    private static final Logger log = LoggerFactory.getLogger(CoreBankingApplication.class);
    private List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);

    public static void main(String[] args) {
        SpringApplication.run(CoreBankingApplication.class, args);
    }

    @Bean
    public ApplicationRunner generateTopics(KafkaTemplate<byte[], byte[]> template) {
        return args -> {
            while (true) {
                List<Customer> customers = createCustomers();
                createCustomerTopic(template, customers);
                Thread.sleep(1000);
                createTransactionTopic(template, customers);
            }
        };
    }

    private void createTransactionTopic(KafkaTemplate<byte[], byte[]> template, List<Customer> customers) throws JsonProcessingException {
        String recipientId = getRandomCustomer(customers).getId();
        String senderId = getRandomCustomer(customers).getId();
        Long amount = amounts.get(new Random().nextInt(amounts.size()));
        String transactionId = String.valueOf(new Random().nextInt(1000000));
        Transaction transaction = new Transaction(transactionId, recipientId, senderId, amount);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("created transaction: transaction id {}, recipient id {}, sender id {}, amount {} €", transaction.getTransactionId(), transaction.getRecipientId(), transaction.getSenderId(), transaction.getAmount());
        template.send("transactionOutput", objectMapper.writeValueAsBytes(transaction));

    }

    private void createCustomerTopic(KafkaTemplate<byte[], byte[]> template, List<Customer> customers) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer randomCustomer = getRandomCustomer(customers);
        log.info("created customer: {}, {}, {}", randomCustomer.getId(), randomCustomer.getName(), randomCustomer.getBirthdate());
        template.send("customerOutput", objectMapper.writeValueAsBytes(randomCustomer));
    }

    private Customer getRandomCustomer(List<Customer> customers) {
        return customers.get(new Random().nextInt(customers.size()));
    }

    private List<Customer> createCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(createCustomer("1", "Walter Schmidt", LocalDate.of(1960, Month.JANUARY, 1)));
        customers.add(createCustomer("2", "Anna Nas", LocalDate.of(1970, Month.AUGUST, 3)));
        customers.add(createCustomer("3", "Herbert Gruber", LocalDate.of(1990, Month.FEBRUARY, 18)));
        customers.add(createCustomer("4", "Thomas Berger", LocalDate.of(1980, Month.JANUARY, 4)));
        customers.add(createCustomer("5", "Sophie Schmidt", LocalDate.of(1965, Month.APRIL, 10)));

        return customers;
    }


    private Customer createCustomer(String id, String name, LocalDate birthDate) {
        return new Customer(id, name, birthDate);
    }

}