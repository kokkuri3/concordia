const express = require('express');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const app = express();
const port = 5058;

const { findSummation, uppercaseFirstandLast, findAverageAndMedian, find4Digits } = require('./Q1');

app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.static(__dirname));

// Q1 Functionality
app.get('/q1', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

app.get('/Q1F1', (req, res) => {
  const N = parseInt(req.query.number);
  if (!isNaN(N) && N > 0) {
    const summation = findSummation(N);
    res.send(`Summation of numbers from 1 to ${N} is ${summation}`);
  } else {
    res.send('Invalid input. Please provide a positive integer.');
  }
});

app.get('/Q1F2', (req, res) => {
  const inputString = req.query.str;
  const modifiedString = uppercaseFirstandLast(inputString);
  res.send(`Modified string: ${modifiedString}`);
});

app.get('/Q1F3', (req, res) => {
  const inputNumbers = req.query.nums.split(',').map(num => parseInt(num.trim()));
  const result = findAverageAndMedian(inputNumbers);

  if (result) {
    res.send(`Average: ${result.average}, Median: ${result.median}`);
  } else {
    res.send('Invalid input. Please provide an array of numbers.');
  }
});

app.get('/Q1F4', (req, res) => {
  const inputString = req.query.str;
  const number = find4Digits(inputString);

  if (number !== false) {
    res.send(`Found 4-digit number: ${number}`);
  } else {
    res.send('No 4-digit number found.');
  }
});

// numOfVisits Functionality
app.get('/readcookie', (req, res) => {
  let visits = req.cookies.visits || 0;
  visits++;
  let isFirstVisit = visits === 1;

  res.cookie('visits', visits);

  let response = '';
  if (isFirstVisit) {
    response = 'Welcome to my webpage! It is your first time that you are here.';
  } else {
    response = `Hello, this is the ${visits} time that you are visiting my webpage.`;
  }

  response += '<br><a href="/numOfVisits/delcookie">Delete Cookie</a>';

  res.send(response);
});

app.get('/numOfVisits/delcookie', (req, res) => {
  res.clearCookie('visits');
  res.send('Cookie deleted. <a href="/readcookie">Back to the webpage</a>');
});

// Q3 Functionality
app.get('/q3', (req, res) => {
  res.sendFile(__dirname + '/Q3.html');
});

app.post('/validatePhoneNumber', (req, res) => {
  let phoneNumber = req.body.phone;
  let validPattern = /^\d{3}-\d{3}-\d{4}$/;

  if (validPattern.test(phoneNumber)) {
    res.send(`Phone number (${phoneNumber}) is correct.`);
  } else {
    res.send('Phone number is not in the correct format (ddd-ddd-dddd). Please include the dash.');
  }
});

// Root Route
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

app.listen(port, () => {
  console.log(`Server is listening at http://localhost:${port}`);
});
