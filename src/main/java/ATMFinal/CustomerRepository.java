package ATMFinal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Meeliana on 6/27/2017.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    //@Query(value = "select username from users where username=?0", nativeQuery = true)
    List<Customer> findByFullName(String uName);
}
