# QuadSolutionsAssessment

# Uitleg over het project
Het doel van het project was om een eigen Java Backend API te maken die contact maakt met de Open Trivia Database API; de bedoeling hiervan is dat de vragen en antwoorden worden opgehaald in de backend, zonder dat de gebruiker correct_answer kan inkijken in de Network tab met F12 zoals ze zouden kunnen doen als de OpenTDB API rechtstreeks gebruikt werdt.

Hiervoor heb ik een Java Backend gemaakt die de vragen ophaalt, de antwoorden door elkaar shufflet en meegeeft als gewoon "answers" en in de backend alsnog correct_answer opslaat om deze later te kunnen vergelijken met het gekozen antwoord van de gebruiker. Voor de frontend heb ik React gebruikt, om hiermee ook nog wat extra ervaring op te bouwen. Ik heb mijn best gedaan om React's UseState te gebruiken om de quiz van 10 rondes verder uit te breiden.

# Features
- getQuestions dat fetcht uit Open Trivia Database
- correct_answers beveiligd binnen de backend
- Gebruikers' antwoorden worden dus ook gevalideerd in de backend
- Frontend gebruikt React's UseState en LocalStorage om de vragen een na een te laten inladen, en slaat ook de gebruiker's huidige score op als dat hoger is dan de local storage zijn high score

# API Gebruik
# getQuestions
```
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
```
# submitAnswers
```
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
```

# Applicatie runnen
Om de applicatie te kunnen runnen is Java 25 nodig om de Gradle versie van het project te matchen.

Repository clonen:
```
git clone https://github.com/JulianJ99/QuadSolutionsAssessment.git
cd quadsolutionsassessment
```

Dan, in de backend folder:
```
./gradlew build
```

Om de backend te runnen doe ik simpelweg "Run Java" op AssessmentApplication.Java binnen Visual Studio Code, wat er zo uitziet in de terminal:
```
PS C:\..\..\..\..\..\QuadSolutionsAssessment\Backend>  & 'C:\Program Files\Java\jdk-25\bin\java.exe' '@C:\Users\..\AppData\Local\Temp\<argfile>' 'com.Assessment.AssessmentApplication' 
```

Om de frontend te runnen open je een terminal binnen in de frontend map en run je het volgende commando:
```
npm run dev
```
