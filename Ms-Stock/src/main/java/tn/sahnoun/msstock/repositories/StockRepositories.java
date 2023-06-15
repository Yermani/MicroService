package tn.sahnoun.msstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.sahnoun.msstock.entities.Stock;

public interface StockRepositories extends JpaRepository<Stock, Long> {

}
