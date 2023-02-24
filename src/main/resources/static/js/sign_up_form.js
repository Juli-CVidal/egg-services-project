const accountType = document.getElementById("account-type");
const form = document.querySelector(".needs-validation");
const divByType = document.getElementById("by-type");

function validatePassword() {
  const password = document.getElementById("password");
  const repeat = document.getElementById("repeat");

  if (password.value.lenght < 8) {
    password.setCustomValidity(
      "La contraseña debe tener al menos ocho caracteres"
    );
    return false;
  }
  if (password.value !== repeat.value) {
    repeat.setCustomValidity("Las contraseñas no coinciden");
    return false;
  }

  password.setCustomValidity("");
  repeat.setCustomValidity("");
  return true;
}

function addValidation() {
  (() => {
    "use strict";

    form.addEventListener(
      "submit",
      (event) => {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }
        if (!validatePassword()) {
          event.preventDefault();
        }

        form.classList.add("was-validated");
      },
      false
    );
  })();
}

function addInputs(selected) {
  let html;
  if (selected === "SUPPLIER") {
    form.setAttribute("action", "/supplier/save");

    html = `
    <label for="description" class="form-label">Cuéntenos sobre usted</label>
    <textarea name="description" id="description" class="form-control" required></textarea>
  `;
  } else if (selected === "CUSTOMER") {
    form.setAttribute("action", "/customer/save");
    html = `
      <div class="col mb-3">
        <label for="street" class="form-label">Ingrese su calle</label>
        <input type="text" id="street" name="street" class="form-control border border-black" autocomplete="off" required />
      </div>
      <div class="col mb-3">
        <label for="number" class="form-label">Y su altura</label>
        <input type="number" id="number" name="number" class="form-control border border-black" autocomplete="off" min="0" required />
      </div>
      <div class="d-flex justify-content-center">
        <select name="account-type" id="account-type" class="form-select w-50" required>
          <option value="" disabled selected>Barrio</option>
          <option value="BARRIO1">Barrio1</option>
          <option value="BARRIO2">Barrio2</option>
        </select>
      </div>
    `;
  }
  divByType.innerHTML = html;

}

function detectSelect() {
  accountType.addEventListener("change", () => {
    const selectedAccount = accountType.value;
    divByType.innerHTML = "";
    addInputs(selectedAccount);
    console.log(selectedAccount);
  });
}

function init() {
  detectSelect();
  addValidation();
}

window.addEventListener("load", () => init());