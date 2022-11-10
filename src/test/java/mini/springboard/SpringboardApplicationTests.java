package mini.springboard;

import mini.springboard.sbb.answer.Answer;
import mini.springboard.sbb.question.Question;
import mini.springboard.sbb.question.QuestionRepository;
import mini.springboard.sbb.answer.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringboardApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(3, all.size());
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
	}
	@Test
	void testJp1() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()){
			Question q3 = oq.get();
			assertEquals("sbb가 무엇인가요?", q3.getSubject());
		}
	}
	@Test
	void testJpa2(){
		List<Question> q4 = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(4,q4.size());
	}
	@Test
	void testJpa3() {
		List<Question> q5 = this.questionRepository.findBySubjectAndContent("Sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(4, q5.size() );
	}
	@Test
	void testJpa4() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q6 = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q6.getSubject());
	}
	@Test
	void testJpa5() {
		Optional<Question> oq1 = this.questionRepository.findById(1);
		assertTrue(oq1.isPresent());
		Question q7 = oq1.get();
		q7.setSubject("수정된 제목");
		this.questionRepository.save(q7);
	}
	@Test
	void testJpa6() {
		assertEquals(4, this.questionRepository.count());
		Optional<Question> oq2 = this.questionRepository.findById(1);
		assertTrue(oq2.isPresent());
		Question q8 = oq2.get();
		this.questionRepository.delete(q8);
		assertEquals(3,this.questionRepository.count());
	}
	@Test
	void testJpa7() {
		Optional<Question> oq3 = this.questionRepository.findById(2);
		assertTrue(oq3.isPresent());
		Question q9 = oq3.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q9);	//어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
		this.answerRepository.save(a);
	}
	@Test
	void testJpa8(){
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a1 = oa.get();
		assertEquals(2, a1.getQuestion().getId());
	}
	@Test
	@Transactional
	void testJpa9(){
		Optional<Question> oq4 = this.questionRepository.findById(2);
		assertTrue(oq4.isPresent());
		Question q10 = oq4.get();

		List<Answer> answerList = q10.getAnswerList();
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}
