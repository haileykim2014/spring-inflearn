package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("memberService2")
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();//배우가 직접 담당배우를 섭외하듯이..->환경 구성에 관한것은 config에서 하도록한다.
    private final MemberRepository memberRepository;//추상화에 의존하도록한다. 구체적인것은 밖에서(AppConfig)에서 생성해서 정해준다.

    @Autowired//ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;//생성자를 통해 MemberRepository에 무엇이 들어갈지 정한다.
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
