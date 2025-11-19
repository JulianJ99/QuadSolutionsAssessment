import axios from 'axios'

const baseUrl = 'http://localhost:8081'

export const getQuestions = ({sessionId, difficulty}) => {
  return axios
    .get(`${baseUrl}/quiz/questions`, {
      headers: {
        'X-Session-Id' : sessionId,
        'Difficulty' : difficulty
      }
    })
  .then((res) => res.data)  
}


export const categories = [
  { label: 'Entertainment: Video Games', value: 10 },
]

export const difficulty = ['easy', 'medium', 'hard']
