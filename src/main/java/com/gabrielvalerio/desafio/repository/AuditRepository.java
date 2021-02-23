package com.gabrielvalerio.desafio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gabrielvalerio.desafio.model.AuditLog;

public interface AuditRepository extends MongoRepository<AuditLog, String> {


}