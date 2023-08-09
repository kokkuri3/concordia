document.addEventListener("DOMContentLoaded", function () {
    let form = document.querySelector("form");
    let submitButton = form.querySelector('[type="submit"]');
  
    form.addEventListener("submit", function (event) {
      event.preventDefault();
  
      // Get the selected pet radio button
      let catRadio = document.getElementById("cat");
      let dogRadio = document.getElementById("dog");
  
      // Get all input fields within the selected form
      let formInputs = [];
      let selectedForm = null;
  
      if (catRadio.checked) {
        formInputs = document
          .getElementById("catForm")
          .querySelectorAll("input, select");
        selectedForm = "cat";
      } else if (dogRadio.checked) {
        formInputs = document
          .getElementById("dogForm")
          .querySelectorAll("input, select");
        selectedForm = "dog";
      } else {
        alert("Please select a pet (cat or dog).");
        return;
      }
  
      let hasEmptyFields = false;
  
      formInputs.forEach((input) => {
        if (input.type === "checkbox" || input.type === "radio") {
          let groupName = input.getAttribute("name");
          let groupInputs = form.querySelectorAll(`[name="${groupName}"]`);
          let isChecked = Array.from(groupInputs).some((groupInput) => groupInput.checked);
  
          if (!isChecked) {
            hasEmptyFields = true;
          }
        } else if (input.value.trim() === "") {
          hasEmptyFields = true;
        }
      });
  
      if (hasEmptyFields) {
        alert("Please fill in all required fields for the " + selectedForm + " form correctly.");
      } else {
        if (selectedForm === "cat") {
          alert("Your form for a cat has been submitted.");
        } else if (selectedForm === "dog") {
          alert("Your form for a dog has been submitted.");
        }
        form.submit();
      }
    });
  });
  
