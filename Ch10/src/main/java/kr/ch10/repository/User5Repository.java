package kr.ch10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ch10.entity.User5Entity;

@Repository
public interface User5Repository extends JpaRepository<User5Entity, String>{

}