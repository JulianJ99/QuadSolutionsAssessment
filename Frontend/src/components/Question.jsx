import { useContext, useEffect, useState } from 'react'
import QuizContext from '../../quizContext'
import axios from 'axios'
const baseUrl = 'http://localhost:8081'

const Question = ({ question, setIsAnswered, isAnswered }) => {
  const [selectedOption, setSelectedOption] = useState()
  //const [AnswerArray, setAnswerArray] = useState([])
  
  const [selectCount, setSelectCount] = useState(1) 
  const [score, scoreDispatch, options, optionsDispatch, AnswerArray, setAnswerArray] = useContext(QuizContext)
  const sessionId = localStorage.getItem('assessment_session_id');
  
  useEffect(() => {
    const Options = ([ 
      ...question.answers.map(answer => {return answer}),
    ])
    
    optionsDispatch({
      type: 'SET_OPTIONS',
      payload: Options,
    })
  }, [question]
)      


 const addAnswer = (answer) =>
  setAnswerArray([...AnswerArray, answer]) //Update alleen op rerender, vraag 10 wordt niet op tijd meegegeven
  

  const saveAnswer = (selectedOption) => {
    addAnswer( 
      { questionId: question.id, answerId: selectedOption.answerId, }
    )

    setSelectCount(selectCount+1)
    if(selectCount === 10){
      submitAnswers();
    }
  }

   const selectOption = (opt) => {
    setSelectedOption(opt)
    saveAnswer(opt)
    setIsAnswered(true)
  }

  async function submitAnswers() {
    const payload = {
      sessionId: sessionId,
      answers: AnswerArray
    }
      axios.post(`${baseUrl}/quiz/answers`, payload, {
        'Content-Type': 'application/json'
      })
      .then(response => { const results = response.data.Results;
        showResults(results);})
    }
    
    function showResults(results) {
      console.log(results);
      const correctCount = results.filter(r => r.Correct).length;
      const scoreTotal = correctCount + 1; //+1 vanwege de array die niet op tijd vraag 10 meegeeft
      console.log(scoreTotal);
       scoreDispatch({
         type: 'SET_SCORE',
         payload: score + scoreTotal,
       })
      
    }

  const displayedOptions = options
  
  return (
    
    <div>
      <div>
        <div className="fs-4 fw-bold lead">
          <div dangerouslySetInnerHTML={{ __html: question.question }} />
        </div>
        <div className="list-group my-3">
          {isAnswered
            ? displayedOptions.map((opt, i) => (
             
              <button
                key={i}
                type="button"
                className={`list-group-item list-group-item-action my-1 rounded-pill disabled ${
                  opt === selectedOption 
                    
                }`}
                disabled
                onClick={() => selectOption(opt)}
              >
                <div dangerouslySetInnerHTML={{ __html: opt.answer }} />
              </button>
            ))
            : displayedOptions.map((opt, i) => (
              <button
                key={i}
                type="button"
                className="list-group-item list-group-item-action my-1 rounded-pill"
                onClick={() => selectOption(opt)}
              >
                <div dangerouslySetInnerHTML={{ __html: opt.answer }} />
              </button>
            ))}
        </div>
      </div>
    </div>
    
  )
  
  
}

export default Question