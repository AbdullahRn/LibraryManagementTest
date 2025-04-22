package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void save(Member member){
        memberRepository.save(member);
    }

    public Member getByEmail(String email){
        Optional<Member> optional = memberRepository.findByEmail(email);
        return optional.orElse(null);
    }

    public List<Member> getAll(){
        return memberRepository.findAll();
    }



//    @Transactional
//    public void deleteMemberByEmail(String email) {
//        memberRepository.deleteMemberByEmailContaining(email);
//    }

    public void deleteMember(String email){
        Optional<Member> optional = memberRepository.findByEmail(email);
        optional.ifPresent(memberRepository::delete);
    }


}

