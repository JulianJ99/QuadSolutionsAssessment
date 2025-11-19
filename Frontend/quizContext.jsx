import { createContext, useReducer, useState } from 'react'

const optionsReducer = (state, action) => {
  switch (action.type) {
  case 'SET_OPTIONS':
    return action.payload
  default:
    return state
  }
}

const scoreReducer = (state, action) => {
  switch(action.type) {
  case 'SET_SCORE':
    return action.payload
  case 'RESET_SCORE':
    return null
  default:
    return state
  }
}

const QuizContext = createContext()

export const QuizContextProvider = (props) => {
  const [options, optionsDispatch] = useReducer(optionsReducer, [])
  
  const [score, scoreDispatch, selectCount, setSelectCount] = useReducer(scoreReducer, null)

  const [answerArray, setAnswerArray] = useState([])

  return (
    <QuizContext.Provider value={[score, scoreDispatch, options, optionsDispatch, answerArray, setAnswerArray, selectCount, setSelectCount]}>
      {props.children}
    </QuizContext.Provider>
  )
}


export default QuizContext