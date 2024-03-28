package kr.ac.kopo.oracledb0314;
import kr.ac.kopo.oracledb0314.entity.Memo;
import kr.ac.kopo.oracledb0314.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;
@SpringBootTest
public class  MemoRepositorysTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println((memoRepository.getClass().getName()));
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Dummy Data Test" + i).build();
            memoRepository.save(memo);
        });
    }

    //select
    @Test
    public void testSelect() {
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        //findById()는 NullpointerException이 발생되지 않도록 Null 체크를 한다.
        //Optional로 반환함
        System.out.println("====================================================");
        if (result.isPresent()) {  //NullpointException이 발생하면 실행를 아예하지 않는다.
            Memo memo = result.get();
            System.out.println(memo);
        } // if
    } // testSelect}

    //select2
    @Transactional
    @Test                                           //이 함수만 실행할 수 있게 만드는 메서드
    public void testSelcect2() {
        Long mno = 100L;

        //getOne(변수) 변수의 라인 정보를 가져온다. 호출되면 바로 실행되지 않고 Memo Entity가 필요할 때 select를 실행한다.
        Memo memo = memoRepository.getOne(mno);
        System.out.println("====================================================");

        System.out.println(memo);
    } // testSelect2}

    //Update
    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(95L).memoText("Update Dummy Data 95").build();

        //95라인에 save(객체의 참조값)함으로써 데이터가 있으므로 update를 실행한다. 데이터가 존재하지 않으면 insert를 한다.
        //save 메서드는 호출되면 먼저 select를 하고 entity의 유무를 판단하고 update와 insert를 용도에 맞게 사용한다.
        Memo memo1 = memoRepository.save(memo);

        System.out.println(memo1);
    } // testUpdate}


    //Delete
    @Test
    public void testDelete(){
        Long mno = 100L; //mno 100번째가 사라지는 것임

        //memoRepository의 deleteById를 호출하는 것이다. MemoEntity의 mno의 값을 호출한다.
        //하나씩 조회할 때는 findById를 많이 사용하고, 삭제할 때는 deleteById를 사용할 것이다.
        memoRepository.deleteById(mno); //삭제 메서드

    } // Delete}

    @Test
    public void testPageDefault(){
        //1페이지당 10개의 Entity의 정보를 가져오는 것
        Pageable pageable = PageRequest.of(1, 10);
        //페이지 전체 정보를 result가 갖고 있음
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        for (Memo memo : result.getContent()){
            System.out.println((memo));
        }// for}

        System.out.println("===========================================================");

        //sout
        System.out.println("Total Pages : " + result.getTotalPages()); //총 페이지의 개수 확인
        System.out.println("Total Count : " + result.getTotalElements()); //Memo entity의 전체 개수 확인
        System.out.println("Page Number : " + result.getNumber()); //총 페이지의 개수 확인
        System.out.println("Total Size: " + result.getSize()); //현재 페이지의 개수확인
        System.out.println("Has next page? : " + result.hasNext()); //
        System.out.println("Is first page? : " + result.isFirst()); // 이 페이지가 첫 번째(0)번째 페이지냐?
        //
    } //testPageDefault}

    @Test
    public void testSort(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            //System.out.println(memo); //전체 값 반환
            System.out.println("number : " + memo.getMno() + ", content: " + memo.getMemoText()); //Mno 값만 반환

        });//result.get().forEach(memo ->}
    }//testSort}




} //main}