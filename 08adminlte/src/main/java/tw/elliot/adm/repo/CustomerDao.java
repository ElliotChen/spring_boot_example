package tw.elliot.adm.repo;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.adm.model.Customer;

public interface CustomerDao extends DataTablesRepository<Customer, Long> {
}
