package blusalt.challenge.customerservice.dao;


import blusalt.challenge.customerservice.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WalletDao extends JpaRepository<Wallet, Long>, JpaSpecificationExecutor<Wallet> {

}
