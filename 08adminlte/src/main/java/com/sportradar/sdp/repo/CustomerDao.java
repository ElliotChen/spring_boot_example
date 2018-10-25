package com.sportradar.sdp.repo;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import com.sportradar.sdp.model.Customer;

public interface CustomerDao extends DataTablesRepository<Customer, Long> {
}
