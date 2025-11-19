import { useState } from 'react'
import { Form } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
import { categories, difficulty } from '../requests'

const Menu = () => {
  const navigate = useNavigate()

  const [formData, setFormData] = useState({
    difficulty: '',
  })



  const selectDifficulty = (event) => {
    setFormData((prev) => ({
      ...prev,
      difficulty: event.target.value,
    }))
  }

  const startQuiz = (event) => {
    event.preventDefault()
    
    navigate(`/quiz/${formData.difficulty}/`)

  }

  return (
    <div className="container d-flex flex-column align-items-start justify-content-center w-100 py-5 text-white">
      <Form
        className="my-2 d-flex flex-column align-items-center justify-content-center"
        onSubmit={startQuiz}
      >


        <div>
          <div className="h3 my-3">Select Difficulty</div>
          {difficulty.map((option) => (
            <Form.Check
              key={option}
              type="radio"
              id={`${option}`}
              label={`${option}`}
              name="difficulty"
              className="text-capitalize"
              onChange={selectDifficulty}
              value={option}
            />
          ))}
        </div>

        <button
          className={`btn btn-primary w-100 my-3 ${
            formData.difficulty === ''
              ? 'disabled'
              : ''
          }`}
        >
          Submit
        </button>
      </Form>
    </div>
  )
}

export default Menu