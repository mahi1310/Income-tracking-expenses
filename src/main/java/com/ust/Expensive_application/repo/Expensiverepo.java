package com.ust.Expensive_application.repo;

import com.ust.Expensive_application.model.Expensive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Expensiverepo extends JpaRepository<Expensive,UUID> {
}
