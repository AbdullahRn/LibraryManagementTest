package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public  Member findById(int id){
        Optional<Member> optional = memberRepository.findById(id);
        return optional.orElse(null);
    }





//    @Transactional
//    public void deleteMemberByEmail(String email) {
//        memberRepository.deleteMemberByEmailContaining(email);
//    }

    public void deleteMember(String email){
        Optional<Member> optional = memberRepository.findByEmail(email);
        optional.ifPresent(memberRepository::delete);
    }


    public Optional<Member> searchMember(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Optional.empty();
        }

        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            String temp = "";
            temp = temp.concat(Objects.toString(member.getName(), ""));
            temp = temp.concat(Objects.toString(member.getEmail(), ""));
            temp = temp.concat(Objects.toString(member.getMobileNumber(), ""));

            if (temp.toLowerCase().contains(keyword.toLowerCase())) {
                return Optional.of(member);
            }
        }

        return Optional.empty();
    }



}

