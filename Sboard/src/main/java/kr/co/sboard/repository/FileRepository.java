package kr.co.sboard.repository;

import kr.co.sboard.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {

}
