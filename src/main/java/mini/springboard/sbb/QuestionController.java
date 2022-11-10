package mini.springboard.sbb;

import lombok.RequiredArgsConstructor;
import mini.springboard.sbb.question.Question;
import mini.springboard.sbb.question.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;

    @RequestMapping("/question/list")

    public String list(Model model) {
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    public String list(){
        return "question_list";
    }
}
