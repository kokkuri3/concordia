function addNumbers(numArray) {
  return numArray.reduce((sum, num) => sum + num, 0);
}

function findMaxNumber() {
  if (arguments.length === 0) {
    return undefined;
  }
  return Math.max(...arguments);
}

function addOnlyNumbers(mixedArray) {
  let sum = 0;
  for (const item of mixedArray) {
    const num = parseFloat(item);
    if (!isNaN(num)) {
      sum += num;
    }
  }
  return sum;
}

function getDigits(inputString) {
  const digitPattern = /\d/g;
  const digits = inputString.match(digitPattern);
  return digits ? digits.join("") : "";
}

function reverseString(inputString2) {
  return inputString2.split("").reverse().join("");
}

function getCurrentDate() {
  const days = [
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
  ];
  const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];

  const now = new Date();
  const dayOfWeek = days[now.getDay()];
  const month = months[now.getMonth()];
  const day = now.getDate();
  const year = now.getFullYear();

  return dayOfWeek + ", " + month + " " + day + " " + year;
}
