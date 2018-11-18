package com.sadsoft.communicator.dao;

import com.sadsoft.communicator.model.ContactsBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsBookRepository extends JpaRepository<ContactsBook, Long> {

}
