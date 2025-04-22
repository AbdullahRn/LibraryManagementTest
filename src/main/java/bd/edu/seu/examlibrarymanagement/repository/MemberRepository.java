package bd.edu.seu.examlibrarymanagement.repository;

import bd.edu.seu.examlibrarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    List<Member> findByEmailContaining(String email);

//    void deleteMemberByEmailContaining(String email);
    
}
