//A
let findSummation = (N = 1) => {
  if (typeof N !== "number" || N <= 0) {
    return false;
  }

  let summation = 0;
  for (let i = 1; i <= N; i++) {
    summation += i;
  }
  return summation;
};

//B
let uppercaseFirstandLast = (inputString) => {
  if (typeof inputString !== "string") {
    return false;
  }

  let words = inputString.split(" ");
  let modifiedWords = words.map((word) => {
    if (word.length < 2) {
      return word;
    }
    let firstChar = word[0].toUpperCase();
    let lastChar = word[word.length - 1].toUpperCase();
    return firstChar + word.slice(1, -1) + lastChar;
  });

  return modifiedWords.join(" ");
};

//C
let findAverageAndMedian = (numbers) => {
  if (!Array.isArray(numbers) || numbers.length === 0) {
    return false;
  }

  let sortedNumbers = numbers.slice().sort((a, b) => a - b);

  let sum = sortedNumbers.reduce((acc, num) => acc + num, 0);
  let average = sum / sortedNumbers.length;

  let median;
  if (sortedNumbers.length % 2 === 0) {
    let midIndex = sortedNumbers.length / 2;
    median = (sortedNumbers[midIndex - 1] + sortedNumbers[midIndex]) / 2;
  } else {
    median = sortedNumbers[Math.floor(sortedNumbers.length / 2)];
  }

  return { average, median };
};

//D
let find4Digits = (numberString) => {
  let numbers = numberString.split(" ").filter((str) => /^[0-9]+$/.test(str));

  for (const num of numbers) {
    if (num.length === 4) {
      return parseInt(num);
    }
  }
  return false;
};

module.exports = {
  findSummation,
  uppercaseFirstandLast,
  findAverageAndMedian,
  find4Digits
};