package com.sopra.cloud.erp.reception.repository;

import com.sopra.cloud.erp.reception.model.Reception;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionRepository extends JpaRepository<Reception, Long> {
}
