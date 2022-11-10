package mini.springboard.sbb.answer;

import mini.springboard.sbb.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
