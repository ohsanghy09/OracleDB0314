package kr.ac.kopo.oracledb0314.repository;

import kr.ac.kopo.oracledb0314.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//쿼리 메서드는 MemoRepository에서 설정
//내가 뭘하고 있는지 알고 있어야한다.

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to); // 데이터 검색의 범위 제한
    //Pageable pageable = PageRequest.of(0, 10) 원래는 이걸 만들어야하는데 아래 참조 변수로 넣어주면서 선언 안 해줘도 됨
    //페이지값 설정 메서드
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    //DELETE METHOD
    //특정 mno 값을 갖는 Memo Entity만 삭제할 때
    void deleteMemoByMno(Long mno);
    // mno의 값이 파라미터 값의 미만인 Memo Entity들을 삭제할 때
    void deleteMemoByMnoLessThan(Long mno);
    // mno의 값이 파라미터 값 이하인 Memo Entity들을 삭제할 때
    void deleteMemoByMnoLessThanEqual(Long mno);


    //@Query Native SQL문 실행방법(복잡한 sql작성을 할 때 사용)
    @Query(value = "select * from tbl_memo where mno >= 70", nativeQuery = true) // 70이상의 객체들을 찾아라
    List<Memo> getNativeResult(); //위 sql문을 list로 저장

    //@Query Native SQL문 실행방법(복잡한 sql작성을 할 때 사용) 2번째 방법
    @Query(value = "select * from tbl_memo where mno >= 80", nativeQuery = true) // 70이상의 객체들을 찾아라
    List<Object[]> getNativeResult2(); //위 sql문을 list로 저장



} //main}
