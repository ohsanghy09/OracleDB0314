package kr.ac.kopo.oracledb0314;
import kr.ac.kopo.oracledb0314.entity.Memo;
import kr.ac.kopo.oracledb0314.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        //Optional로 반환함
        System.out.println("====================================================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        } // if
    } // testSelect

    //select2
    @Transactional
    @Test
    public void testSelcect2() {
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);
        System.out.println("====================================================");

        System.out.println(memo);
    } // testSelect2

    //Update
    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(95L).memoText("Update Dummy Data 95").build();

        Memo memo1 = memoRepository.save(memo);

        System.out.println(memo1);
    } // testUpdate


    //Delete
    @Test
    public void testDelete(){
        Long mno = 100L;
        memoRepository.deleteById(mno);

    }




} //main