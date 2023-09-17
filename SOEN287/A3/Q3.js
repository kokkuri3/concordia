let express = require('express');
let bodyParser = require('body-parser');
let app = express();
app.use(bodyParser.urlencoded({ extended: false }));

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

app.post('/checkNumber', (req, res) => {
  let phoneNumber = req.body.phone;
  let validPattern = /^\d{3}-\d{3}-\d{4}$/;

  if (validPattern.test(phoneNumber)) {
    res.send(`Phone number (${phoneNumber}) is correct.`);
  } else {
    res.send('Phone number is not in the correct format (ddd-ddd-dddd).');
  }
});

app.listen(5058, () => {
  console.log('Server is listening on port 5058');
});
