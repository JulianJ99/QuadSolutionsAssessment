# QuadSolutionsAssessment
Assessment project voor Quad Solutions

# Uitleg over het project
Het doel van het project was om een eigen Java Backend API te maken die contact maakt met de Open Trivia Database API; de bedoeling hiervan is dat de vragen en antwoorden worden opgehaald in de backend, zonder dat de gebruiker correct_answer kan inkijken in de Network tab met F12 zoals ze zouden kunnen doen als de OpenTDB API rechtstreeks gebruikt werdt.

Hiervoor heb ik een Java Backend gemaakt die de vragen ophaalt, de antwoorden door elkaar shufflet en meegeeft als gewoon "answers" en in de backend alsnog correct_answer opslaat om deze later te kunnen vergelijken met het gekozen antwoord van de gebruiker. Voor de frontend heb ik React gebruikt, om hiermee ook nog wat extra ervaring op te bouwen. Ik heb mijn best gedaan om React's UseState te gebruiken om de quiz van 10 rondes verder uit te breiden.

# API Gebruik
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

