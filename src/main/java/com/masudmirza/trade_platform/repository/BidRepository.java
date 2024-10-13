package com.masudmirza.trade_platform.repository;

import com.masudmirza.trade_platform.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BidRepository extends JpaRepository<Bid, UUID> {
}
