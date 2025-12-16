package com.imarkov.payment.repo;

import com.imarkov.payment.model.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
