const writeBox = document.getElementById("writeBox");

const writeRadio = document.getElementById("writein-radio");

const craigRadio = document.getElementById("canidate1");

const kushRadio = document.getElementById("canidate2");

const keithRadio = document.getElementById("canidate3");

const jakobRadio = document.getElementById("canidate4");

writeRadio.addEventListener("change", function () {
  if (this.checked) {
    writeBox.style.display = "block";
  }
});

craigRadio.addEventListener("change", function () {
  if (this.checked) {
    writeBox.style.display = "none";
  }
});

kushRadio.addEventListener("change", function () {
  if (this.checked) {
    writeBox.style.display = "none";
  }
});

keithRadio.addEventListener("change", function () {
  if (this.checked) {
    writeBox.style.display = "none";
  }
});

jakobRadio.addEventListener("change", function () {
  if (this.checked) {
    writeBox.style.display = "none";
  }
});
