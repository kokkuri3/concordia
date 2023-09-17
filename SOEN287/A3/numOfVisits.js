let express = require("express");
let cookieParser = require("cookie-parser");
let app = express();
app.use(cookieParser());

app.get('/', (req, res) => {
  let visits = req.cookies.visits || 0;
  visits++;
  let isFirstVisit = visits === 1;
  let visitDate = new Date().toString();

  res.cookie('visits', visits);

  let response = '';
  if (isFirstVisit) {
    response = 'Welcome to my webpage! It is your first time that you are here.';
  } else {
    response = `Hello, this is the ${visits} time that you are visiting my webpage.<br>Last time you visited my webpage on: ${visitDate}`;
  }

  response += '<br><a href="/delcookie">Delete Cookie</a>';

  res.send(response);
});

app.get('/delcookie', (req, res) => {
  res.clearCookie('visits');
  res.send('Cookie deleted. <a href="/">Back to the webpage</a>');
});

app.listen(5058, () => {
  console.log("Server is listening on port 5058");
});
